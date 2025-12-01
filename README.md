
1. Nombre del Proyecto

AppHollow – Tienda de Ropa Dark

Aplicación móvil desarrollada en Android Studio utilizando Kotlin, Jetpack Compose, ViewModel, Retrofit y navegación.
Incluye autenticación, administración de usuarios y consumo de API externa e interna.

2. Integrantes del Equipo

Sofía Huichulef

3. Funcionalidades Principales
 Autenticación

Registro de usuarios.

Inicio de sesión con validación contra backend.

Persistencia del estado del usuario mediante DataStore.

 Gestión de Usuario

Perfil de usuario.

Edición de información personal.

Lista de usuarios (solo administrador).

Edición de usuario (solo administrador).

Listado de posts/productos obtenidos desde un servicio externo.

Vista detallada básica de cada ítem.

Navegación

Drawer lateral y navegación con Navigation Compose.

Pantallas: Home, Login, Registro, Perfil, Resumen, Admin.

Arquitectura

MVVM (Model - ViewModel - View)

Repository Pattern

StateFlow + Compose para manejo reactivo del estado.

4. Endpoints Utilizados
5.  A) API Externa (Posts)

Base URL:
http://10.0.2.2:8080/ (o la URL externa real si aplica)

Endpoints utilizados:

Método	Endpoint	Descripción
GET	/api/posts	Obtiene el listado de posts/productos
GET	/api/posts/{id}	Obtiene post por ID
POST	/api/posts	Crear un post nuevo

B) Microservicio Backend (Spring Boot – Usuarios)

Base URL:
http://10.0.2.2:8080/

Endpoints utilizados:

 Autenticación
Método	Endpoint	Descripción
POST	/api/users/register	Registrar usuario
POST	/api/users/login	Iniciar sesión
Usuarios
Método	Endpoint	Descripción
GET	/api/users	Obtiene todos los usuarios
GET	/api/users/{id}	Obtiene usuario por ID
PUT	/api/users/{id}	Edita un usuario

Tabla respaldada por la entidad User en tu backend (id, name, email, passwordHash, address, isAdmin, isActive).

Campos de la tabla users en MySQL
Campo	Tipo	Descripción
id	int	Identificador
name	varchar	Nombre del usuario
email	varchar	Email único
password_hash	varchar	Contraseña encriptada
address	varchar	Dirección
is_admin	bit	Rol administrador
is_active	bit	Estado activo/inactivo

5. Pasos para Ejecutar el Proyecto
A) Levantar el Microservicio Backend

Abrir el proyecto Spring Boot (hollow-backend-master) en IntelliJ.

Verificar conexión a base de datos MySQL en application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/hollowstore
spring.datasource.username=root
spring.datasource.password=tu_contraseña


Ejecutar la clase principal:

HollowBackendApplication.java

Backend se levanta en:

http://localhost:8080

B) Configurar URL del Backend en la App

En RetrofitInstance.kt:

private const val BASE_URL = "http://10.0.2.2:8080/"


C) Ejecutar la App Móvil

Abrir el proyecto AppHollow_Grupo11 en Android Studio.

Conectar un dispositivo o emulador.

Build → Clean Project.

Build → Rebuild Project.

Run → Run 'app'.
6. Generacion de la apk 
<img width="526" height="606" alt="Captura de pantalla 2025-12-01 180329" src="https://github.com/user-attachments/assets/3f1eaccb-3031-46b5-afbc-5de14564a6ab" />
codigo fuente 
<img width="912" height="622" alt="image" src="https://github.com/user-attachments/assets/d917019a-2d22-44bf-b000-7007f6729fdd" />



