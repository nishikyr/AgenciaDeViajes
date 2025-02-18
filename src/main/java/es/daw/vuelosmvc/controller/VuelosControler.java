package es.daw.vuelosmvc.controller;

import es.daw.vuelosmvc.dto.VueloDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
@RequestMapping("/vuelos")
public class VuelosControler {

    @Value("${daw.api.url}")
    private String BASE_URL;

    private final WebClient webClient;


    public VuelosControler(WebClient webClient) {
        this.webClient = webClient;
    }

    //---------------------------------------------------------------------------------------
    //------------------------- I WAS BORN TO SUCCEED BY ALEJO NISHIKY------------------------
    //----------------------------------------------------------------------------------------
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/listar")
    public String getVuelos(Model model) {
        System.out.println("*********** ESTO AQUI MARICO************");
        List<VueloDTO> vuelos = webClient.get()
                .uri("/vuelos")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<VueloDTO>>() {})
                .block();
        System.out.println("***********************************");
        System.out.println(vuelos);
        System.out.println("************************************");
        model.addAttribute("vuelos", vuelos);

        return "list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nuevo")
    public String nuevoVuelo(Model model) {
        VueloDTO vueloDTO = new VueloDTO();
        model.addAttribute("vuelo", vueloDTO);

        return "formulario";
    }


    //-----------------AÑADIENDO VUELOS -------------------------
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public String guardarVuelo(@ModelAttribute("vuelo") VueloDTO vueloDTO, Model model, BindingResult result) {
        System.out.println("Campos de errores: " + result.getFieldErrors());
        if (result.hasErrors()) {
            System.out.println("*********** AQUI HAY ERRORES ***************");
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));

            return "formulario";
        }
        webClient.post()
                .uri(BASE_URL + "/vuelos")
                .bodyValue(vueloDTO)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return "redirect:/vuelos/listar";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{codigo}")
    public String editarVuelo(@PathVariable("codigo") String codigo, Model model) {
        System.out.println("***** codigo del vuelo: " + codigo);
        VueloDTO vueloDTO = webClient.get()
                .uri("/vuelos/find/" + codigo)
                .retrieve().bodyToMono(VueloDTO.class)
                .block();
        model.addAttribute("vuelo", vueloDTO);
        return "formulario";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public String actualizarVuelo(@ModelAttribute("vuelo") VueloDTO vueloDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Hay errores en la actualización.");
            return "formulario";
        }

        webClient.put().uri(BASE_URL + "/vuelos/update-codigo")
                .bodyValue(vueloDTO)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return "redirect:/vuelos/listar";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/eliminar/{codigo}")
    public String eliminarVuelo(@PathVariable("codigo") String codigo) {
        System.out.println("******** MAJO ESTOY ELIMINANDO ***********");
        webClient.delete()
                .uri("/vuelos/codigo/" + codigo)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return "redirect:/vuelos/listar";

    }
}
