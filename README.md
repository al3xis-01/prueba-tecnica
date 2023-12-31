## Prueba Técnica

Este repositorio contiene una aplicación web desarrollada con Angular para el frontend y Spring Boot para el backend, ambos integrados en un entorno de contenedores Docker mediante `docker-compose`.

## Requisitos del Sistema

Asegúrese de tener instalados los siguientes componentes en su sistema antes de ejecutar la aplicación:

    Docker:
        Instale Docker siguiendo las instrucciones oficiales.
        Asegúrese de que Docker esté configurado y en ejecución.

    Docker Compose:
        Instale Docker Compose siguiendo las instrucciones oficiales.

    Git:
        Instale Git siguiendo las instrucciones oficiales.


### Instrucciones de Ejecución

1. Clone el repositorio:

    ```bash
    git clone https://github.com/al3xis-01/prueba-tecnica.git
    ```

2. Navegue al directorio del proyecto:

    ```bash
    cd prueba-tecnica
    ```

3. Ejecute la aplicación con Docker Compose:

    ```bash
    docker-compose up --build
    ```

4. Acceda a la aplicación en su navegador:

    - Backend: [http://localhost:8080](http://localhost:8080)
    - Frontend: [http://localhost](http://localhost)

La aplicación estará disponible en los puertos especificados. 

### Estructura del Proyecto

```bash
.
├── README.md
├── backend-app
│   ├── Dockerfile
│   ├── README.md
│   ├── build.gradle.kts
│   ├── gradle
│   │   └── wrapper
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
│   ├── gradlew
│   ├── gradlew.bat
│   ├── settings.gradle.kts
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── servifacil
│       │   │           └── backendapp
│       │   │               ├── BackendAppApplication.java
│       │   │               └── library
│       │   │                   ├── configuration
│       │   │                   │   └── spring
│       │   │                   │       ├── ApplicationConfiguration.java
│       │   │                   │       ├── SecurityConfiguration.java
│       │   │                   │       └── WebConfiguration.java
│       │   │                   ├── controller
│       │   │                   │   └── api
│       │   │                   │       └── AuthenticationController.java
│       │   │                   ├── filter
│       │   │                   │   └── JWTAuthenticationFilter.java
│       │   │                   ├── security
│       │   │                   │   ├── AuthCredentials.java
│       │   │                   │   ├── AuthResponse.java
│       │   │                   │   ├── JWTProvider.java
│       │   │                   │   ├── UnauthorizedResponse.java
│       │   │                   │   ├── User.java
│       │   │                   │   ├── UserRepository.java
│       │   │                   │   └── UserResponse.java
│       │   │                   └── util
│       │   │                       ├── AuthenticationService.java
│       │   │                       └── JpaCustomRepository.java
│       │   └── resources
│       │       └── application.properties
│       └── test
│           └── java
│               └── com
│                   └── servifacil
│                       └── backendapp
│                           └── BackendAppApplicationTests.java
├── docker-compose.yml
├── frontend-app
│   ├── Dockerfile
│   ├── README.md
│   ├── angular.json
│   ├── nginx.conf
│   ├── package-lock.json
│   ├── package.json
│   ├── src
│   │   ├── app
│   │   │   ├── app.component.css
│   │   │   ├── app.component.html
│   │   │   ├── app.component.spec.ts
│   │   │   ├── app.component.ts
│   │   │   ├── app.config.ts
│   │   │   ├── app.routes.ts
│   │   │   ├── core
│   │   │   │   ├── components
│   │   │   │   │   ├── main
│   │   │   │   │   │   ├── main.component.css
│   │   │   │   │   │   ├── main.component.html
│   │   │   │   │   │   └── main.component.ts
│   │   │   │   │   ├── navbar
│   │   │   │   │   │   ├── navbar.component.css
│   │   │   │   │   │   ├── navbar.component.html
│   │   │   │   │   │   └── navbar.component.ts
│   │   │   │   │   └── sidebar
│   │   │   │   │       ├── sidebar.component.css
│   │   │   │   │       ├── sidebar.component.html
│   │   │   │   │       └── sidebar.component.ts
│   │   │   │   ├── guard
│   │   │   │   │   ├── admin.guard.ts
│   │   │   │   │   ├── auth-not.guard.ts
│   │   │   │   │   ├── auth.guard.ts
│   │   │   │   │   └── empleado.guard.ts
│   │   │   │   ├── interceptor
│   │   │   │   │   ├── auth.interceptor.spec.ts
│   │   │   │   │   └── auth.interceptor.ts
│   │   │   │   ├── interface
│   │   │   │   │   ├── auth-response.ts
│   │   │   │   │   ├── credentials.ts
│   │   │   │   │   └── user.ts
│   │   │   │   └── service
│   │   │   │       ├── app.service.ts
│   │   │   │       ├── auth.service.ts
│   │   │   │       └── comunication.service.ts
│   │   │   └── module
│   │   │       ├── admin
│   │   │       │   ├── admin-routing.module.ts
│   │   │       │   ├── admin.module.ts
│   │   │       │   └── components
│   │   │       │       └── admin-view
│   │   │       │           ├── admin-view.component.css
│   │   │       │           ├── admin-view.component.html
│   │   │       │           └── admin-view.component.ts
│   │   │       ├── authentication
│   │   │       │   ├── authentication-routing.module.ts
│   │   │       │   ├── authentication.module.ts
│   │   │       │   └── components
│   │   │       │       └── login-view
│   │   │       │           ├── login-view.component.css
│   │   │       │           ├── login-view.component.html
│   │  

 │       │           └── login-view.component.ts
│   │   │       ├── empleado
│   │   │       │   ├── components
│   │   │       │   │   └── empleado-view
│   │   │       │   │   │   ├── empleado-view.component.css
│   │   │       │   │   │   ├── empleado-view.component.html
│   │   │       │   │   │   └── empleado-view.component.ts
│   │   │       │   ├── empleado-routing.module.ts
│   │   │       │   └── empleado.module.ts
│   │   │       └── home
│   │   │           ├── components
│   │   │           │   └── home-view
│   │   │           │   │   ├── home-view.component.css
│   │   │           │   │   ├── home-view.component.html
│   │   │           │   │   └── home-view.component.ts
│   │   │           ├── home-routing.module.ts
│   │   │           └── home.module.ts
│   │   ├── assets
│   │   │   └── img
│   │   │       ├── calculadora.png
│   │   │       ├── icono_contribuyentes.png
│   │   │       ├── icono_isr.png
│   │   │       ├── icono_iva.png
│   │   │       ├── img.png
│   │   │       └── user-icon-login.png
│   │   ├── environments
│   │   │   ├── environment.development.ts
│   │   │   └── environment.ts
│   │   ├── favicon.ico
│   │   ├── index.html
│   │   ├── main.ts
│   │   └── styles.css
│   ├── tsconfig.app.json
│   ├── tsconfig.json
│   └── tsconfig.spec.json
└── resources
    └── db.sql
```
