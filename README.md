#Travis 
[![Build Status](https://travis-ci.com/emitolaba95/TP-DDS.svg?token=WZANzTsTpqeJqzz5zjW8&branch=master)](https://travis-ci.com/emitolaba95/TP-DDS)


# Mails


rodrigocampassi@gmail.com

espositolucas95@gmail.com

lucasdattoli96@gmail.com

juanpadilla.jip@gmail.com

emi.tolaba95@gmail.com

# Instalación mysql
1. Instalar mysql y mysql-workbench
2. sudo mysql_install_db --user=mysql --basedir=/usr --datadir=/var/lib/mysql
3. Si es necesario, activar el servicio de mysql.
4. Completar con usuario y contraseña en persistence.xml:                                                                                                                     
            property name="hibernate.connection.username" value="root"                                                                                                        
            property name="hibernate.connection.password" value=""
5. En mysql-workbench -> create a new schema in the connected server -> llamarlo 'pois'
6. En el directorio del proyecto correr: mvn clean install && mvn eclipse:clean eclipse:eclipse

# Comandos copados sobre mysql:
## Bash:
mysql -h localhost -u root -p: iniciar mysql

## Mysql:
show databases: muestra las bases de datos

use <database>: cambia a la base de datos seleccionada

insert into <table> (<param1>,…,<paramn>) values (<value1>,…,<valuen>)

# Waffle
[![Issues en waffle](https://waffle.io/dds-utn/2016-jm-group-07)](https://waffle.io/dds-utn/2016-jm-group-07)

# Manejo de archivos
[![Libreria externa para manejar archivos](https://commons.apache.org/proper/commons-io/javadocs/api-2.4/org/apache/commons/io/FileUtils.html)](https://commons.apache.org/proper/commons-io/javadocs/api-2.4/org/apache/commons/io/FileUtils.html)

## Ver paquetes en forma comoda:
En eclipse, estando con Package explore, ir a la flechita hacia abajo que aparece arriba de la lista de proyectos -> Package presentation -> Hieranchical

De esa forma las cosas, segun el orden que les puse, se ven como corresponde y quedan en subcarpetas, en vez de verse en forma rara.





# Tercera Entrega
### Diagrama de clases 3ra entrega y solucion alternativa con decorator
https://1drv.ms/f/s!Av0vPKTcwzssgTbfFJSb1x_Q5v76

Solucion 1 (Elegida): Activador booleano
![alt tag](http://i.imgur.com/yldpNMD.jpg)

Solucion 2: Decorator
![alt tag](http://i.imgur.com/oVeHxGi.jpg)


### Justificación de la solucón elegida
Tomamos la solución 1 porque es mucho mayor la simpleza de mantener un estado con dos booleanos que la de decorar un objeto y tener que decorarlo y "des"decorarlo dinámicamente.
A costa de esto, perdemos la flexibilidad que podría darnos el uso de decorators, si en un futuro se requieren mas acciones. Al ser la clase Terminal la que tenga que encargarse no sólo de lo propio de una Terminal, sino tambien de llamar a las clases que se encargan de enviar el mail o registrar busquedas, y de determinar si debe o no llamarlas, se pierde cohesividad, aunque consideramos que el impacto de esto en el sistema no es sustancial en este caso ya que la cohesividad perdida es baja, mientras que usando un decorator haríamos Terminal más cohesiva, pero a costa de complejizar el sistema. 

De acuerdo al dominio del problema, usar decorator para 2 acciones podria ser considerado un sobrediseño, asi que valoramos la simplicidad por sobre algo de cohesividad y una flexibilidad que podria nunca llegar a ser necesaria, aunque consideramos muy probable el uso de decorator en un futuro si efectivamente nos piden agregar nuevas funcionalidades.
Hicimos más cohesivo al BuscadorTexto, usando un singleton que contiene los lugares donde tiene que buscar el Buscador, y trasladando a la Terminal, al los reportes y al EmailSender las responsabilidades que a ellos les correspondian, y anteriormente eran realizadas por el buscador. Antes, el Buscador de texto conocía directamente a los medios, teniendo la responsabilidad de saber como tenia que llamar a cada uno. Ahora es mas fácil agregar nuevos medios de búsqueda, ya que en vez de tener que modificar una clase poco cohesiva, con mucho comportamiento y agregarle no solo el medio nuevo sino tambien el llamado, ahora con agregar un medio a la lista del repositorio todo funciona correctamente.
Tambien separamos la responsabilidad de buscar en los pois locales en una nueva clase BuscadorLocal, quien conoce a Mapa y le pide la lista de los poi.

Tambien convertimos la clase Mapa en un singleton para asegurar la unicidad de los datos





#Cuarta Entrega
###Diagrama de clases cuarta entrega

Solucion 1 (Elegida)
https://onedrive.live.com/?cid=2c3bc3dca43c2ffd&id=2C3BC3DCA43C2FFD%21182&authkey=!ALsbFENhYlbBuOM
![alt tag]()



#Cuarta Entrega - Correcciones del 14/07
## Planificador
![alt tag](http://i.imgur.com/9u9HqJu.jpg)

## Manejo de errores
#### Lo que era el manejador de errores ahora ya no esta más como clase, aunque sus funcionalidades se mantienen en otras clases. El try-catch del error se pasó a la clase abstracta Proceso, el loggeo de eventos a Logger y lo de volver a ejecutar los procesos hasta que alcancen el limite de fallos lo hace ReLanzador.
![alt tag](http://i.imgur.com/vKW7HSo.jpg)

## Diagrama de clases de cada proceso
#### Dejo link de mi drive, completar diagrama cada uno con sus procesos (usar StarUml)
#### https://1drv.ms/u/s!Av0vPKTcwzssgT-et3_4OMyiVnC5

#### Diagrama Proceso 1: Actualizacion Locales Comerciales
![alt tag](http://puu.sh/q10VL/66bd877992.png)

#### Diagrama Proceso 2: Baja Poi:
![alt tag](http://i.imgur.com/ybVwg7n.jpg)

#### Diagrama Proceso 3: Activacion Terminales
![alt tag](http://i.imgur.com/hNn2fi5.jpg)

## Solucion alternativa 
#### Manejador de errores:
En esta solución alternativa, el proceso hace el try-catch, al igual que en la solucón original, pero delega el manejo de errores en el manejador de errores, al cual informa que termino de ejecutarse. El manejador, se encarga de relanzarlo si es necesario, y llama a todos los observers interesados en el evento, como pudieran ser el Logger, el EmailSender o cualquier otro que se pueda agregar en un futuro. Al ser un manejador por proceso, cada manejador tiene un estado acorde al proceso cuyos errores maneja, en vez de la solucón que planteamos la primera vez, donde el manejador era uno solo y era llamado por el lanzador, cumpliendo asi, al igual que la nueva solución elegida en base a las correcciones hechas el día de la entrega, el requisito de que cada proceso tenga su configuración propia respecto a si debe relanzarse o no en caso de fallo, cuantos fallos tolera, si envia mail o no, etc.
Hace mas cohesiva la clase proceso, ya que no se encarga de manejar sus fallos sino que delega eso y se encarga solo de hacer lo que sea propio de un proceso, pero a su vez la clase manejadorDeErrores queda con poco comporamiento. A nivel ventajas, ambas tienen la flexibilidad de que se agreguen nuevos observadores en el futuro de forma sumamente sencilla, ya que bastaria con agregarlos a la lista de observadores y que estos implementen la interface Accion. Se pierde algo de simplicidad, ya que el manejador complejiza la ejecucion de las acciones respecto de la solución elegida. La instanciacion es similar, ya que un proceso al instanciarse, en su constructor, instancia un nuevo manejador para él, por lo que el agregar una clase más no complegiza la instanciación. Dado que proceso expone publicamente los mismos metodos en ambas soluciones, para quien use los procesos (desde otra parte del sistema o al momento de hacer testing), es indistinto cual sea la solución que esté implementada, por lo que podemos decir que la dificultad de testeo es similar en ambos.
![alt tag](http://i.imgur.com/RpvT5QA.jpg)

#### Proceso 3: Activacion de terminales
En vez de tener clases que se ocupan de las activaciones/desactivaciones correspondientes, podrian ser metodos, y ser interpretadas como strings para luego parsearlas en un Map. 
La desventaja de esta solucion es que el proceso que activa deberia conocer todos los metodos que le podrian llegar (para instanciarlos en el Map). 
En la solucion elegida no pasa esto y hace mucho mas cohesivo y extensible el proceso. Agregar nuevas acciones no repercute en otras clases.




# Entrega 5
## Pre-Entrega

#### DER POIs
![alt tag](http://i.imgur.com/y71A0Og.png)

#### DER Búsquedas
![alt tag](http://i.imgur.com/EICqbzw.png)

#### DER Acciones
![alt tag](http://i.imgur.com/xUnV6Rh.png)

### Justificación de decisiones de diseño
En primer lugar, para lograr cumplir los requerimientos de la entrega se necesitó adaptar el modelo de objetos. Estos cambios consistieron principalmente en adaptar los Pois, los cuales constaban de una clase Poi, que tenía un atributo del tipo TipoPOI (strategy), del cual heredaban los tipos de POI sin servicios, como las estaciones de colectivo, y una clase abstracta TipoPOIConServicio, de la que heredaban los tipos de pois con servicios. De ese modelo se pasó a usar herencia en lugar del strategy, lo cual facilitó la relación entre el modelo de objetos y el relacional.
Por otra parte, fue necesario persistir los puntos y polígonos, para lo cual hicimos wrappers de estas clases y refactorizamos todo para que se usen los wrappers y no las originales. De esta forma, fue posible usar annotations en los wrappers para persistirlos.
Al momento de persistir las terminales no fueron necesarios grandes cambios más que la creación de las id (cosa que se repite a lo largo de las distintas clases persistidas explicadas anteriormente).

El modelo relacional no se encuentra totalmente normalizado, ya que la clase PointWrapper está embebida dentro de Poi, y la herencia de pois se persistió mediante single table, debido a que las subclases compartían muchos de los atributos de la clase Poi. Esto nos brinda un mejor rendimiento y velocidad al momento de realizar búsquedas, ya que por ejemplo, evita el hecho de realizar JOINS al no generar varias tablas. Como desventaja, perdemos integridad al tener campos con valor nulo y los atributos podrían usarse para cosas distintas. Esta misma estrategia de mapeo de herencia utilizamos para las subclases de Proceso, y para las de Acción de Terminal, ya que sus subclases no poseen atributos. 

Ante no poder mapear los points, surgió la necesidad de usar un converter, mapearlos vía un archivo .xml, o usar wrappers, entre otras. El wrapper era posible dado que los point eran value objects. No había contras para no hacerlo, y tenía sentido que el punto esté siempre presente ya que es un atributo muy consultado de un punto de interés (y esto pudimos verificarlo al ver el gran impacto de cambiar el Point por PointWrapper), por lo que tendría sentido que sea parte del poi o que se traiga a memoria siempre que se traiga un poi, siendo la segunda alternativa más lenta. En cuanto a espacio, se reduce el espacio al no haber una FK en poi ni una PK en una tabla PointWrapper, y al no repetirse los Points no hay una pérdida de espacio por embeberlos. El tema del espacio, de todas formas, no es significativo, ya que el espacio ahorrado es relativamente pequeño en comparación al tamaño que se esperaría que tenga la base de datos completa, y también en comparación a los espacios de almacenamiento que se acostumbra usar hoy en día. Sin embargo, el cambio en rendimiento si vale la pena considerarlo. Todo lo anterior es igualmente válido para los Polígonos.

###Entrega 6
Diagrama de clases ![alt tag](https://s4.postimg.org/j3szqxj6l/pipoi.png)

