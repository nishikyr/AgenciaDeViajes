# Agencia de viaje usando Spring!

**Agencia de Viajes con Spring MVC**  
Un sistema web desarrollado con Spring MVC que permite gestionar reservas, destinos y clientes de una agencia de viajes de manera eficiente.
**Consideraciones previas** 
- Este proyecto esta configurado por el puerto /8081
- Se esta trabajando en conjunto con un API REST en otro proyecto llamado VuelosCRUD (lo subiré en el transcurso del mes) para obtener los endPoints cumpliendo con el CRUD. Create, Read, Update and Delete. Ese proyecto esta configurado por el puerto 8080
- Se esta trabajando con una base de datos en memoria H2. Que ya esta previamente configurada en el application.properties y una vez iniciada la aplicación puede ser accedida desde el localhost:8081/h2-console.
Para una mejor visualización de información o alguna otra configuración que desee realizar.


# Archivos y Dependencias

**Dependencias**  
Este proyecto contiene archivos Java utilizando Dependencias como:
- Lombok
- Spring Web
- Spring Reactive Web
- Spring Security
- JDBC API
- Spring Data JPA
- Spring Data JDBC
- H2 Database
- Validation
Para el lado del cliente, usamos HTML con Thymeleaf y Bootstrap
- Template Engines -> Thymeleaf

**Archivos**  
El proyecto sigue una arquitectura modular organizada en diferentes paquetes: 
- **config/**: Contiene configuraciones del proyecto, como `AppConfig` y `MvcConfig`, que establecen configuraciones generales de la aplicación y MVC. 
- **controller/**: Contiene los controladores REST, como `AuthController` (para autenticación) y `VuelosController` (para la gestión de vuelos). 
- **dto/**: Define los objetos de transferencia de datos (DTOs) utilizados para estructurar la información en las peticiones y respuestas, como `VueloDTO`. 
- **entity/**: Contiene las clases que representan las entidades del dominio, como `Product`, `Role` y `User`, que son mapeadas a tablas en la base de datos. 
- **repository/**: Define las interfaces de acceso a datos con `JpaRepository`, como `RoleRepository` y `UserRepository`, encargadas de interactuar con la base de datos.
- **security/**: Contiene la configuración de seguridad de la aplicación con Spring Security, incluyendo `CustomUserDetailsService` (gestión de usuarios autenticados) y `SecurityConfig` (configuración de seguridad). 
- **service/**: Implementa la lógica de negocio, como `UserService`, que maneja la gestión de usuarios y roles en la aplicación. Cada carpeta organiza su responsabilidad de manera modular para mantener el código limpio y escalable.
**Carpeta Resources**
- **templates/**: Contiene las plantillas **Thymeleaf** utilizadas para renderizar las vistas en la aplicación, incluyendo: - `error-acceso.html`: Página de error para accesos no autorizados. - `formulario.html`: Formulario genérico para la aplicación. - `index.html`: Página principal de la aplicación. - `list.html`: Página de listado de elementos. - `login.html`: Formulario de inicio de sesión. - `register.html`: Formulario de registro de usuarios. 
- **application.properties**: Archivo de configuración principal de Spring Boot, donde se definen propiedades como la conexión a la base de datos, el puerto del servidor, entre otros ajustes como la configuración de una Base de datos H2 en memoria.  
- **import.sql**: Script SQL para importar datos iniciales en la base de datos al iniciar la aplicación. Esta estructura permite manejar la configuración, vistas y datos de la aplicación de forma organizada.

## Recursos e imágenes
![Home](https://github.com/nishikyr/AgenciaDeViajes/blob/master/imagenes/home.png)
![FlightList](https://github.com/nishikyr/AgenciaDeViajes/blob/master/imagenes/flightsList.png)
![Login](https://github.com/nishikyr/AgenciaDeViajes/blob/master/imagenes/login.png)
![SignUp](https://github.com/nishikyr/AgenciaDeViajes/blob/master/imagenes/register.png)

## Sugerencias

En una segunda versión se puede implentar 
- La mejora de algunos accesos con Spring Security en algunas páginas, restringuilas por roles o por el usuario en si. 
- El uso de JWT para la autenticación también es indispensable.
- Aplicar filtros, para obtener viajes de un solo destino.
- Agregar la hora de salida y llegada (tendriamos que evaluarlo tambien en nuestro Vuelos APIREST (otro proyecto))
- Colocar un página estática siguiendo el diseño con Bootstrap.
