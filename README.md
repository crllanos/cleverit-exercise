# CleverIT Exercise

### Ejercicio
El ejercicio consiste en levantar una API para la gestión de tareas:

### Solución
La solución presentada consiste en lo siguiente:
* Una API RESTFUL con las operaciones básicas (Crete, read, Update, Delete).
* Manejo mediante Enums de los estados de las tareas (vía HTTP PATCH)
* Motor de BD: H2 (in memory).
* Autenticaciń mediante integración de JWT.
* Test en la capa de servicio.
* Manejo de excepciones mediante estados HTTP.

## Token de autenticación
El proyecto está segurizado mediante JWT. Para generar este token es necesario apuntar al endpoint de /login con las credenciales descritas en el apartado siguiente.

Una vez obtenido dicho token, debe ser ingresado como parte del request en el apartado "Authorization" como Bearer Token.

## Roles y credenciales
Al levantar el proyecto se generan automaticamente, vía CommandLineRunner, los siguientes perfiles administrativos:

### ROLE_ADMIN
* username: pparker
* password: spider
* grants: GET, POST (ver y crear tareas)

### ROLE_SUPERADMIN
* username: jjameson
* password: boss
* grants: GET, POST, PUT, PATCH, DELETE (ver, crear, modificar y borrar)

## Curls
La herramienta Postman no permite expoŕtar colecciones, se adjunta como alterantiva los Curl equivalentes para cada operación.


### Login


### Crear tarea

````bash
curl -XPOST -H 'Authorization: Bearer <TOKEN_JWT>' -H "Content-type: application/json" -d '{
"id" : "",
"title" : "task title",
"description" : "task description",
"dueDate" : "2023-09-30",
"status" : null
}' 'http://localhost:8080/api/v1/task/'
````

### Listar tareas

````bash
curl -XGET -H 'Authorization: Bearer <TOKEN_JWT>' 'http://localhost:8080/api/v1/task/'
````

### Ver una tarea
````bash
curl -XGET -H 'Authorization: Bearer <TOKEN_JWT>' 'http://localhost:8080/api/v1/task/<UUID>'
````


### Ediar una tarea

````bash
curl -XPUT -H 'Authorization: Bearer <TOKEN_JWT>' -H "Content-type: application/json" -d '{
"title" : "new task title",
"description" : "new task description",
"dueDate" : "2023-09-30"
}' 'http://localhost:8080/api/v1/task/<UUID>'
````

### Cambiar el estado

````bash
curl -XPATCH -H 'Authorization: Bearer <TOKEN_JWT>' -H "Content-type: application/json" -d '{
"status" : "COMPLETED"
}' 'http://localhost:8080/api/v1/task/<UUID>'
````

### Eliminar

````bash
curl -XDELETE -H 'Authorization: Bearer <TOKEN_JWT>'  'http://localhost:8080/api/v1/task/<UUID>'
````
