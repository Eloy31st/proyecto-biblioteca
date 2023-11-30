# ProyectoBiblioteca

# Sistema de Gestión de Bibliotecas (LMS) con Hibernate y JPA

## Contexto del Problema

En una biblioteca pública grande, con miles de libros y cientos de lectores, surge la necesidad de gestionar eficientemente operaciones como préstamos, devoluciones, renovaciones y el mantenimiento del catálogo. Para abordar estos desafíos, hemos decidido desarrollar un Sistema de Gestión de Bibliotecas (LMS) que utilice Hibernate y JPA para interactuar con una base de datos SQL, garantizando la eficiencia y seguridad del sistema incluso en situaciones de concurrencia.

## Requisitos Técnicos

1. **Modelo de Datos:**
   - Diseñar e implementar un modelo de datos que abarque entidades como libros, lectores, préstamos, y otras necesarias para la biblioteca.

2. **Hibernate y JPA:**
   - Utilizar Hibernate y JPA para mapear las clases de dominio a las tablas de la base de datos.

3. **API Rest:**
   - Proporcionar una API robusta que permita a los bibliotecarios y lectores realizar operaciones esenciales, como buscar libros, gestionar préstamos, agregar nuevos libros y eliminar los obsoletos. (Consulta [Crear API REST con Spring](https://www.nigmacode.com/java/crear-api-rest-con-spring/))

4. **Control de Concurrencia:**
   - Implementar mecanismos de control de concurrencia para evitar condiciones de carrera, asegurando operaciones seguras y coherentes.

5. **Auditoría y Control de Versiones:**
   - Integrar auditoría y control de versiones para rastrear las acciones realizadas en el sistema, identificando quién realiza cada operación y cuándo.

6. **Caché para Rendimiento:**
   - Utilizar técnicas de caché para mejorar el rendimiento de operaciones comunes, como la búsqueda de libros.

7. **Pruebas:**
   - Implementar pruebas unitarias e integración para garantizar el correcto funcionamiento de la aplicación bajo diversas circunstancias.

Este proyecto se propone como una solución integral para la gestión eficiente y segura de una biblioteca pública, abordando los desafíos específicos del entorno y proporcionando una experiencia fluida tanto para los bibliotecarios como para los lectores.


This app was created with Bootify.io - tips on working with the code [can be found here](https://bootify.io/next-steps/).
Feel free to contact us for further questions.

## Development

When starting the application `docker compose up` is called and the app will connect to the contained services.
[Docker](https://www.docker.com/get-started/) must be available on the current system.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own
`application-local.yml` file to override settings for development.

Lombok must be supported by your IDE. For IntelliJ install the Lombok plugin and enable annotation processing -
[learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

In addition to the Spring Boot application, the DevServer must also be started. [Node.js](https://nodejs.org/) has to be
available on the system - the latest LTS version is recommended. On first usage and after updates the dependencies have to be installed:

```
npm install
```

The DevServer can now be started as follows:

```
npm run devserver
```

Using a proxy the whole application is now accessible under `localhost:8081`. All changes to the templates and JS/CSS
files are immediately visible in the browser.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Node.js is automatically downloaded using the `frontend-maven-plugin` and the final JS/CSS files are integrated into the jar.

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/proyecto-biblioteca-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=io.bootify/proyecto-biblioteca
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
* [Thymeleaf docs](https://www.thymeleaf.org/documentation.html)  
* [Webpack concepts](https://webpack.js.org/concepts/)  
* [npm docs](https://docs.npmjs.com/)  
* [Bootstrap docs](https://getbootstrap.com/docs/5.3/getting-started/introduction/)  
* [Htmx in a nutshell](https://htmx.org/docs/)  
* [Learn Spring Boot with Thymeleaf](https://www.wimdeblauwe.com/books/taming-thymeleaf/)  
