# Calculo de depreciación de activos

Ests proyecto es creado con el fin de calcular la depreciación de los activos registrados en un sistema , permite hacer
las operaciones CRUD para una base de datos h2.

![## Diagrama de entidad-relación](C:\Users\danda\IdeaProjects\Technical-test\images\diagramaEntidadRelacion.jpg)

Aquí se muestra el diagrama de entidad-relación que representa la estructura de la base de datos utilizada en el proyecto.

    +---------------------+         
    |      Equipment      |         
    +---------------------+         
    | - id: Long          |         
    | - serialNumber: String |      
    | - name: String      |
    | - description: String |
    | - purchaseDate: Date |
    | - purchaseValue: Double|
    | - depreciationPurchaseValue: Double |
    +---------------------+

## Diagrama de clases

A continuación se presenta el diagrama de clases que ilustra la estructura y las relaciones entre las clases del proyecto.

       +----------------------+
       |      Equipment       |
       +----------------------+
       | - id: Long           |
       | - serialNumber: String |
       | - name: String       |
       | - description: String|
       | - purchaseDate: Date |
       | - purchaseValue: Double|
       | - depreciationPurchaseValue: Double |
       +----------------------+
       | + getAllEquipments(): List<Equipment> |
       | + getEquipmentForSerialNumber(serialNumber: String): Equipment |
       | + addEquipment(equipment: Equipment): Equipment |
       | + updateEquipment(serialNumber: String, equipment: Equipment): Equipment |
       | + deleteEquipment(serialNumber: String): boolean |
       +----------------------+

      EquipmentRepository                  ParameterRepository
      +-----------------+                  +------------------+
      | - findAll(): List<Equipment>        | ...              |
      | - findBySerialNumber(serialNumber: String): Optional<Equipment> |
      | - save(equipment: Equipment): Equipment |
      | - delete(equipment: Equipment): void |
      +-----------------+                  +------------------+

      CalculateDepreciation
      +-----------------+
      | - calculateDepreciation(purchaseValue: Double, purchaseDate: Date, parameterRepository: ParameterRepository): Double |
      +-----------------+

      EquipmentController
      +-----------------+
      | + getAllEquipments(): List<Equipment> |
      | + getEquipmentForSerialNumber(serialNumber: String): ResponseEntity<Object> |
      | + addEquipment(equipment: Equipment): ResponseEntity<Equipment> |
      | + updateEquipment(serialNumber: String, equipment: Equipment): ResponseEntity<Equipment> |
      | + deleteEquipment(serialNumber: String): ResponseEntity<String> |
      +-----------------+

      CustomResponseEntityExceptionHandler
      +-----------------+
      | + handleNoSuchElementException(ex: NoSuchElementException): ResponseEntity<Object> |
      +-----------------+

## Diagrama de componentes

El siguiente diagrama de componentes muestra la arquitectura general del sistema y los componentes principales involucrados.

+------------------------+                       +-------------------------+
|      Usuario           |                       |      Controlador         |
+------------------------+                       +-------------------------+
|                        |                       |                         |
|  agregarEquipo()       |                       |                         |
|----------------------->|                       |                         |
|                        |                       |                         |
|                        |                       |  addEquipment()         |
|                        |                       |------------------------>|
|                        |                       |                         |
|                        |                       |  createEquipment()      |
|                        |                       |------------------------>|
|                        |                       |                         |
|                        |                       |  saveEquipment()        |
|                        |                       |------------------------>|
|                        |                       |                         |
|                        |                       |                         |
|                        |                       |    Respuesta            |
|                        |                       |<------------------------|
|                        |                       |                         |
+------------------------+                       +-------------------------+

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

1.Use Swagger UI to test the endpoints. URL = http://localhost:8080/swagger-ui/#/equipment-controller <BR>
2.Use H2 Console to access the database. URL = http://localhost:8080/h2-console/login.do?jsessionid=bc40b0139981e906e3e95d2871e2457d <BR>
el ingreso a la bd es con el usuario:localhost y contraseña: localhost <BR>
3.Ejecuta la consulta <BR>
INSERT INTO parameters (name, value_parameter) VALUES('percentajeDepreciation', '0.04'); <BR>
4.Puedes Empezar a probar los enpoints del CRUD por acá la colección de postman con la información : <BR>
https://api.postman.com/collections/11985200-a20f58af-453d-4de1-8e46-ee7fff3d6ab3?access_key=PMAT-01H5B7DBPFEAR5BZRZ91EC04T0

## Contacto

ANdrés Benavides
Email: dandavi2310@hotmail.com