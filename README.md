# Calculo de depreciación de activos

Ests proyecto es creado con el fin de calcular la depreciación de los activos registrados en un sistema , permite hacer
las operaciones CRUD para una base de datos h2.

## Diagrama de entidad-relación

Aquí se muestra el diagrama de entidad-relación que representa la estructura de la base de datos utilizada en el proyecto.

![Diagrama de entidad-relación](/ruta/al/diagrama-entidad-relacion.png)

## Diagrama de clases

A continuación se presenta el diagrama de clases que ilustra la estructura y las relaciones entre las clases del proyecto.

![Diagrama de clases](/ruta/al/diagrama-clases.png)

## Diagrama de componentes

El siguiente diagrama de componentes muestra la arquitectura general del sistema y los componentes principales involucrados.

![Diagrama de componentes](/ruta/al/diagrama-componentes.png)

## Requisitos previos

- Debes tener instalado Java
- Debes tener algún IDE para descargar el proyecto y ejecutarlo
- Debes ingresar a la base de datos y ejecutar el siguien insert:
  INSERT INTO parameters (name, value_parameter) VALUES('percentajeDepreciation', '0.04');

## Instalación

- Descargar el repositorio a tu maquina local.
- Debes ejecutar el build con maven y esperar qué se descarguen las dependencias
- Debes ejecutar el proyecto en tu local ejecutando el Run , necesitas la version 19 en adelante de java para ejecutarlo.

## Uso

Una vez tengas ejecutando el proyecto puedes ver la documentación y la base de datos.

1.Use Swagger UI to test the endpoints. URL = http://localhost:8080/swagger-ui/#/equipment-controller
2.Use H2 Console to access the database. URL = http://localhost:8080/h2-console/login.do?jsessionid=bc40b0139981e906e3e95d2871e2457d
el ingreso a la bd es con el usuario:localhost y contraseña: localhost
3.Ejecuta la consulta
INSERT INTO parameters (name, value_parameter) VALUES('percentajeDepreciation', '0.04');
4.Puedes Empezar a probar los enpoints del CRUD por acá la colección de postman con la información :
https://api.postman.com/collections/11985200-a20f58af-453d-4de1-8e46-ee7fff3d6ab3?access_key=PMAT-01H5B7DBPFEAR5BZRZ91EC04T0

## Contacto

Amdrés Benavides