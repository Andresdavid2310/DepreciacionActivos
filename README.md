# Calculo de depreciación de activos

Ests proyecto es creado con el fin de calcular la depreciación de los activos registrados en un sistema , permite hacer
las operaciones CRUD para una base de datos h2.

Aquí se muestra el diagrama de entidad-relación que representa la estructura de la base de datos utilizada en el proyecto.
![## Diagrama de entidad-relación](https://github.com/Andresdavid2310/TechnicalTest/blob/master/images/diagramaEntidadRelacion.jpg)

## Diagrama de clases

A continuación se presenta el diagrama de clases que ilustra la estructura y las relaciones entre las clases del proyecto.
+-----------------------+
|   DepreciationDto     |
+-----------------------+
| - serialNumber: String|
| - yearOfCalculateDepreciation: String|
+-----------------------+

               +-------------------|-----------------------|------------------+
               |                   |                       |                  |
+--------------v----+     +--------v----+       +----------v-----+     +----v-------------+
|   Equipment      |     |   Depreciation |       | RulesDepreciation|     | CustomResponseEntity |
+------------------+     +---------------+       +-----------------+     |       ExceptionHandler |
| - serialNumber: String| | - serialNumber: String| | - yearDepreciation: String | +-----------------+
| - name: String       | | - yearDepreciation: int | | - percentageDepreciation:  | | +handleNoSuchElementException(NoSuchElementException): ResponseEntity<Object>
| - description: String| | - depreciationPurchaseValue: double |    String| +-----------------+
| - purchaseDate: Date  | | - yearDepreciation: int | +-----------------+
| - purchaseValue: double | | - equipment: Equipment |
|                      | | - equipment: Equipment  |
|                      | +-----------------------+
+----------------------+


                                  +-------------------------+
                                  |   DepreciationService   |
                                  +-------------------------+
                                  | - depreciationRepository: DepreciationRepository|
                                  | - equipmentRepository: EquipmentRepository    |
                                  | - ruleRepository: RuleRepository                |
                                  +-------------------------+
                                  | +calculateDepreciationPerYear(DepreciationDto): Depreciation|
                                  | +findEquipmentBySerialNumberAndDepreciation(String): Optional<Equipment> |
                                  | +getAllDepreciation(): List<Depreciation>    |
                                  +-------------------------+

                 +-------------------+           +--------------------+
                 |   EquipmentService |           |    RuleServices    |
                 +-------------------+           +--------------------+
                 | - equipmentRepository: EquipmentRepository|
                 +-------------------+           | - ruleRepository: RuleRepository |
                 | +getAllEquipments(): List<Equipment>| +getAllRules(): List<RulesDepreciation> |
                 | +getEquipmentForSerialNumber(String): Equipment | +addRule(RulesDepreciation): RulesDepreciation |
                 | +addEquipment(Equipment): Equipment     +----------------+
                 | +updateEquipment(String, Equipment): Equipment     | +addEquipment(Equipment): Equipment |
                 | +deleteEquipment(String): boolean    +----------------+
                 +-------------------+

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
- Debes tener algún IDE  para descargar el proyecto y ejecutarlo usando maven

## Instalación

- Descargar el repositorio a tu maquina local.
- Debes ejecutar el build con maven y esperar qué se descarguen las dependencias
- Debes ejecutar el proyecto en tu local ejecutando el Run , necesitas la version 19 en adelante de java para ejecutarlo.

## Uso

Una vez tengas ejecutando el proyecto puedes ver la documentación y la base de datos.

1.Use Swagger UI to test the endpoints. URL = http://localhost:8080/swagger-ui/#/equipment-controller <BR>

2.Use H2 Console to access the database. URL = http://localhost:8080/h2-console/login.do?jsessionid=bc40b0139981e906e3e95d2871e2457d <BR>

el ingreso a la bd es con el usuario:localhost y contraseña: localhost <BR>

3.Debes crear un equipo haciendo una petición a la ruta /equipments usando el modelo de datos definido en la documentacion del swagger

4.Debes crear una regla de depreciacion para los activos para el año que quieres calcular 
usando la ruta /rules , puedes guiarte de la documentacion de swagger

5.Creas la depreciacion del activo para el año qué quieras haciendo una petición a la ruta
/depreciation con eso se calculará la depreciacion del equipo qué podrás ver despúes en las consultas.

6.Puedes Empezar a probar los enpoints del CRUD por acá la colección de postman con la información : <BR>
https://api.postman.com/collections/11985200-a20f58af-453d-4de1-8e46-ee7fff3d6ab3?access_key=PMAT-01H5B7DBPFEAR5BZRZ91EC04T0

## Contacto

Andrés Benavides
Email: dandavi2310@hotmail.com