# API Rest con Spring boot y cucumber
## 1. descripción de la aplicación de ejemplo
En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

PRICES
-------


| BRAND_ID | START_DATE | END_DATE | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|---|:-------------------:|:-------------------:|---|---|---|---|---|
| 1 | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1 | 35455 | 0 | 35.50 | EUR |
| 1 | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2 | 35455 | 1 | 25.45 | EUR |
| 1 | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3 | 35455 | 1 | 30.50 | EUR |
| 1 | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4 | 35455 | 1 | 38.95 | EUR |


Campos:

BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
PRICE_LIST: Identificador de la tarifa de precios aplicable.
PRODUCT_ID: Identificador código de producto.
PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
PRICE: precio final de venta.
CURR: iso de la moneda.

Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).

Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:

-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)


Se valorará:

Diseño y construcción del servicio.
Calidad de Código.
Resultados correctos en los test.

## 2. docker
El motor de base de datos que vamos a utilizar para nuestro ejemplo es MySQL. Para utilizarlo vamos a usar docker, que nos va a permitir levantar una instancia tanto del motor de búsqueda como de una aplicación ([adminer](https://hub.docker.com/_/adminer/)) con la que podremos gestionar y consultar los datos que vayamos almacenando.

Para la instalación de docker se recomienda leer la [documentación oficial](https://docs.docker.com/install/).

En este otro [link](https://hub.docker.com/_/mysql) podemos ver las opciones que tenemos para usar MySQL sobre docker. En este [artículo](https://medium.com/@chrischuck35/how-to-create-a-mysql-instance-with-docker-compose-1598f3cc1bee) también podemos encontrar información útil.

Para arrancar las aplicaciones debemos abrir un terminal e irnos al raíz de nuestro proyecto, donde se encuentra el fichero `docker-compose.yml` y escribir:
```bash
$ docker-compose up
```
La primera vez que lo ejecutemos tardará más, ya que normalmente no tendremos las imágenes descargadas en nuestra máquina. A partir de la siguiente ejecución arrancará mucho más rápido.

Tal y como está configurado el fichero `docker-compose.yml`, la aplicación adminer arrancará en http://localhost:8000/
La configuración que tendremos que usar es:
* **servidor**: mysql (es el nombre con el que arranca el servicio de MySQL en nuestro docker-compose.yml, línea 13)
* **usuario**: root
* **password**: example
* **base de datos**: demodb

## 3. spring initializer
Spring Initializer ([link](https://start.spring.io/)) es la herramienta oficial de pivotal para la generación de proyectos con Spring. Nos permite elegir qué gestor de dependencias queremos usar (maven o gradle), el lenguaje que vamos a usar (java, kotlin o groovy), la versión de spring boot que vamos a usar (está seleccionada por defecto la última release) y otra serie de datos que se usarán para la generación del esqueleto de la aplicación que nos bajaramos al finalizar. En nuestro caso hemos elegido maven como gestor de dependencias y java como lenguaje.

La parte más importante es la de las dependencias. Para nuestro proyecto hemos seleccionado:
* Spring web
* Spring Data JPA
* MySQL Driver

## 4. configuración del proyecto
En las siguientes secciones vamos a describir cómo está organizada la aplicación.

* `controller` - Clases controladoras de nuestro API Rest, aquí se define la estructura de las APIs que expondremos hacia el exterior.
  * `dto` - contiene todos los beans tanto de entrada como de salida de nuestra aplicación (se corresponden con los especificados en la documentación del API Rest)
    * `request` - beans de entrada de información, son los que se mapean cuando recibimos una petición a nuestra API.
    * `response` - beans de salida de información, son los datos con los que respondemos a las peticiones que llegan a nuestra API.
* `repository` - repositorios que nos permiten interactuar con la base de datos.
  * `entities` - descripción de las entidades que se corresponden a las tablas de la base de datos.
* `exceptionhandler` - Contiene la clase que gestiona todos los errores que se puedan producir en la aplicación.
    * `exception` - excepciones que hemos creado en nuestro proyecto.
* `mapper` - Clases de conversión entre tipos. A veces tenemos que utilizar información que tenemos en una clase para generar una instancia de otra, en este paquete es donde tendríamos todas esas clases de utilidad.
* `service` - aquí están los servicios que se usan desde los controladores y que son los que realizan realmente las operaciones que exponemos.
  * `service` - aquí van las implementaciones de los servicios del paquete padre
* `utils` - aqui pondremos utilidades globales tal como un customLocalDateTimeDeserializer

### 4.1. Propiedades de la aplicación
En src>main>resources encontraremos el application.yaml
### 4.2. Liquibase (configuración de la base de datos)
[Liquibase](https://www.liquibase.org/) es una librería que nos permite gestionar nuetra base de datos desde código. Podemos definir una serie de scripts que queremos que se ejecuten en el arranque de nuestras aplicaciones y la propia librería se ocupa de crear en la base de datos un par de tablas que se usan para controlar qué scripts han sido ejecutados de forma que no se repitan.

Algunos artículos para profundizar:
* https://www.liquibase.org/
* https://www.baeldung.com/liquibase-refactor-schema-of-java-app
* https://javadeveloperzone.com/spring-boot/spring-boot-liquibase-example/

Alternativas:
* https://flywaydb.org/
* https://github.com/mongobee/mongobee (para mongodb, descontinuada)
* https://github.com/cloudyrock/mongock (para mongb, fork de mongobee)

## 5.Cucumber
TODO
Contexto is failing
