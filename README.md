# Tiendas 3B – CatList (Prueba técnica)

Kotlin Multiplatform + Compose Multiplatform. App de ejemplo con **login simulado**, **sesión local en Room**, **lista de gatos**, **favoritos** y **navegación** (auth → listado → detalle → favoritos). Proyecto de prueba para **desarrollador** en **Tiendas 3B**.

## TL;DR
- **UI:** Compose Multiplatform (Material 3), animaciones simples, `ListItem`.
- **Auth falso:** sign up / login / logout, “Olvidé mi contraseña” (borra pass o cuentas).
- **Sesión:** persistida en `Room` (tabla `session`), arranque dinamo según sesión.
- **Favoritos:** tabla `favorite_cats` autocontenida (id, name, imageUrl, createdAt).
- **Navegación:** `NavHost` con **subgrafo AUTH** (login/signup/forgot) y **CAT** (list/detalle/favoritos).
- **Estado/Domain:** Use Cases + Repository pattern, coroutines + Flow/StateFlow.
- **Offline chip:** `FilterChip` con ícono `WifiOff` cuando no hay red.

## Tech Stack
- Kotlin (KMP), Coroutines/Flow  
- Compose Multiplatform (Material3)  
- Room (DAO/Entities/Migrations)  
- Koin (DI)  
- Navigation (compose)  

## Arquitectura (resumen)
- `data`: DAOs (Room), Entities, Repos (`AuthRepositoryImpl`, `FavoriteCatRepoImpl`, etc.)
- `domain`: UseCases (`AuthUsesCase`, `FavoritesUseCases`, `CatsUsesCase`)
- `ui`: ViewModels (StateFlow), Composables (screens y componentes)

## Características clave
- **Auth simulado:** hashing simple, creación de sesión en `login()`, limpieza en `logout()`/`resetPassword()`/`deleteAllAccounts()`.
- **Forgot:** pantalla que borra cuentas/contraseña y regresa a login.
- **Favoritos:** `FavoriteCatEntity(name, imageUrl)` ⇒ la screen de favoritos lee solo esa tabla.
- **Navegación robusta:** 
  - `AUTH_GRAPH` → `ROUTE_LOGIN|ROUTE_SIGNUP|ROUTE_FORGOT`
  - `LIST_CAT_GRAPH` → `LIST_CAT_ROUTE|DETAIL_CAT_ROUTE|FAV_LIST_ROUTE`
  - Redirección a lista si hay sesión activa.

## Ejecutar
- **Android:** abrir en Android Studio (JDK 17), compilar y correr en emulador/dispositivo.  
- **Desktop:** tarea Gradle de desktop (Compose Desktop) si está habilitada en el proyecto.

## Notas
- Credenciales: puedes **registrarte** y luego **iniciar sesión**.  
- El login es **simulado** y **no** usa backend real.  
- Si cambias el schema de Room, recuerda subir `version` y agregar `Migration` (o `fallbackToDestructiveMigration()` para desarrollo).

## Licencia
MIT. Proyecto de **prueba técnica** para Tiendas 3B.
