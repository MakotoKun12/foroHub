<div style="text-align: center;">
<h1 align="left"> ForoHub </h1>

<p>Proyecto realizado en la formación ONE en la especialización de Backend. El proyecto trata sobre un foro de cursos, en los que en ellos pueden crearse topicos (temas debate).
Y a partir de ello ir subiendo respuestas al respecto.</p>

![Badge-Spring](https://github.com/MakotoKun12/foroHub/assets/121250940/a9fdca32-5c25-4a24-b2eb-1fd342878cc4)

<h2 align="left">Requisitos</h2>

- ✅ [Java 17]
- ✅ [Maven 3.3.1+]
- ✅ [Git]


<h2 align="left">Instalacion</h2>
1. Clona el repositorio

``` git clone git@github.com:MakotoKun12/literalura.git ```
    
2. Navega al directorio del proyecto
``` cd foroHub ```

4. Configura la base de datos en el archivo
`aplication,properties`

![Screenshot from 2024-07-10 09-29-21](https://github.com/MakotoKun12/foroHub/assets/121250940/8f57f6b3-d5c1-4d7a-afa2-6aa0d60c117e)

5. Ejecuta la aplicación con el IDE de tu preferencia

<h2 align="left">Arquitectura y tecnologias utilizadas</h2>

- Spring Boot DevTools:
Facilita el desarrollo de aplicaciones mediante la recarga automática y otras herramientas útiles.

- Spring Data JPA:
Abstracción de persistencia para trabajar con bases de datos de una manera más sencilla y con menos código boilerplate.

- Spring Boot Starter Validation:
Herramientas de validación de datos para asegurar la integridad y corrección de los datos en las solicitudes.

- Spring Boot Starter Web:
Herramientas esenciales para construir aplicaciones web y RESTful.

- Flyway:
Control de versiones de la base de datos para migraciones seguras y eficientes.

- MySQL Connector/J:
Conector JDBC para la base de datos MySQL.

- Lombok:
Biblioteca para reducir el código boilerplate, generando automáticamente getters, setters, constructores, etc.

- Spring Boot Starter Security:
Proporciona autenticación y autorización a la aplicación.

- JWT (JSON Web Tokens):
Biblioteca para trabajar con tokens JWT para la autenticación y autorización.

- Springdoc OpenAPI:
Genera documentación interactiva para la API utilizando Swagger UI.

<h2 align="left">Endpoints</h2>

![Screenshot from 2024-07-10 09-42-24](https://github.com/MakotoKun12/foroHub/assets/121250940/09845b5f-2605-4599-b764-744e5d3aca80)

<h2 align="left">Prueba la API</h2>

<p>Ejectuando el proyecto de manera local y habiendo conectado a una respectiva base de datos MySQL se puede probar por medio de los siguientes links:</p>
 
``` http://localhost:8080/swagger-ui/index.html#/ ```

``` http://localhost:8080/v3/api-docs ```
</div>





