## Elaborado por: 

					Edgar Ricardo Hernandez Fonseca.
					Noviembre 2024.
					PRUEBA PRÁCTICA – BACKEND,  NTT Data. 



## TECNOLOGIAS

- **1:**	Spring Boot: 
				
					Framework principal utilizado para crear el servicio REST.
					Ofrece integración con componentes web, lógica de negocio y configuración simplificada.


- **2:**	Spring Web:

					Incluido como parte de Spring Boot para crear y exponer endpoints RESTful.
					Permite manejar solicitudes HTTP, validación de rutas y controladores REST.


- **3:**	SLF4J (Simple Logging Facade for Java):

					Utilizado para manejar los logs dentro del servicio.
					Permite registrar información en diferentes niveles: DEBUG, INFO, WARN, ERROR.


- **4:**	JUnit 5:

					Framework utilizado para realizar pruebas unitarias.
					Asegura la calidad del servicio al verificar la lógica de negocio y las respuestas de los endpoints.


- **5:**	Spring Boot Test:

					Biblioteca de pruebas de Spring Boot que incluye herramientas como MockMvc.
					Facilita la simulación y prueba de controladores REST sin necesidad de un servidor real.
				

- **6:**	MockMvc:

					Herramienta de prueba utilizada para simular solicitudes HTTP a los endpoints REST.
					Permite probar el comportamiento de las APIs de manera controlada y sin un entorno externo.


- **7:**	Maven:
				
					Herramienta de gestión de dependencias y automatización de la construcción del proyecto.


- **8:**	Jackson:

					Incluido de manera predeterminada en Spring Boot para convertir objetos Java en JSON y viceversa.
					Utilizado para serializar y deserializar las respuestas del servicio.


- **9:**	Swagger:

					Sirve para documentar y visualizar las APIs REST fácilmente.



## PRUEBA PRÁCTICA – BACKEND

- **1:**	Se requiere crear un servicio REST que me permita consultar la información básica de un cliente. 

- **2:**	Condiciones del servicio: 
	- **a:**	Es indispensable el manejo de códigos http en la respuesta (dentro del código es necesario evidenciar el manejo de los siguientes 400, 404, 500 y 200).
	
	- **b:**	Los únicos datos de entrada del servicio son tipo y número de documento, ambos son obligatorios y para tipo de documento son únicamente válidos los tipos C (cédula de ciudadanía) y P (Pasaporte).
	
	- **C:**	Los datos que debe retornar el servicio son: (Deben estar “quemados”).
	
				 Primer nombre
				 Segundo nombre
				 Primer apellido
				 Segundo apellido
				 Teléfono
				 Dirección
				 Ciudad de residencia
	
- **3:**	El puerto por el cual debe iniciar la aplicación es 8090.

- **4:**	Indispensable usar Spring y Maven.

- **5:**	Son entregables el JAR de la aplicación y el código fuente.


Notas:
 Los datos de respuesta son mockeados, es decir solo se retornara información para el cliente identificado con Cedula de ciudadanía 23445322.
 Son valores agregados pero no obligatorios manejo de log de la aplicación, pruebas unitarias y calidad de código.



## SWAGGER

	 http://localhost:8090/swagger-ui/index.html
	 http://localhost:8090/swagger-ui/index.html#/Gesti%C3%B3n%20de%20Clientes/obtenerClienteUsingGET



## servicio-cliente /api/v1/clientes endpoint get /{tipoDocumento}/{numeroDocumento}  

	 http://localhost:8090/api/v1/clientes/C/23445322
	


## Logs  /servicio-cliente/logs/cliente-service.log

		Los logs se guardarán en el archivo logs/cliente-service.log.
		Se mostrarán logs con nivel DEBUG o superior para el paquete com.cliente.rest.

	
## JAR servicio-cliente-0.0.1-SNAPSHOT

	 El jar "servicio-cliente-0.0.1-SNAPSHOT.jar" se encuentra en /servicio-cliente/src/main/resources/servicio-cliente-0.0.1-SNAPSHOT.jar
