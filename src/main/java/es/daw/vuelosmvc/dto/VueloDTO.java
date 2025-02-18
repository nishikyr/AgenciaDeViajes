package es.daw.vuelosmvc.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
public class VueloDTO {


    @NotBlank(message = "El origen es obligatorio")
    private String origen;
    @NotBlank(message = "El origen es obligatorio")
    private String destino;

    @Min(value = 100, message = "El precio debe superior a 99")
    private double precio;
    private int escalas;
    @NotBlank(message = "Debes indicar la compañia con la que viajas")
    private String compania;

    private String codigo;
}
