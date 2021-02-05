# "De Vacaciones" - Escape Room Game :video_game: :book:
Este es un motor de juego de aventura de texto hecho en Java. La definción de la aventura se encuentra escrita en un archivo .json, lo que permite que cualquier persona pueda redefinir la trama sin tener que retocar el programa.

En esta historia, una situación inesperada hará que debas hacer lo posible para salir del lugar. Los objetos de cada lugar te ayudarán a lograrlo y las decisiones que tomes serán importantes.

Trabajo presentado para el final de la materia "Paradigmas de Programación" del I.F.T.S. n° 16.

### Requerimientos previos:

Tener instalado Java JDK8.

### Cómo ejecutar el juego?

Se puede correr desde la consola de comandos de Windows de la siguiente manera:

1.  Clonar el repositorio
2.  Abrir la carpeta "jar".  
3.  Ejecutar cmd desde dicha ubicación.  
4.  Escribir el comando "java -jar Juego.jar".  
5.  Jugar!

Es posible también correrlo desde un IDE (como Intellij) a partir la clase main Juego.java, la cual se encuentra en la carpeta "src".

### Comandos para jugar: 

Para jugar debes escribir en la consola alguna de las acciones válidas, seguidas por una dirección (si te movés) o por un objeto.

Direcciones válidas: 
- "norte"
- "este"
- "sur" 
- "oeste"

Objetos válidos: son los que se muestran por consola.

Acciones válidas: 

- "ir" : con esta acción te vas a desplazar de una habitación a otra. Luego de la acción se debe indicar la dirección.
- "ob" (observar): con esta acción vas a obtener una descripción del objeto elegido.
- "in" (interactuar): con esta acción el personaje va a realizar algo (o no) con el objeto que elije el usuario.

### Librerías externas utilizadas:

json-simple-1.1

### Aclaraciones:

_ Dentro de la carpeta "data" se encontraran con dos archivos json. Ambos describen la misma aventura del juego, aunque uno de ellos (juegoCMD.json) no contiene caracteres especiales para que pueda ser interpretado por la consola de comando de Windows sin problema.

_ Dentro del paquete "objetos" existe un paquete denominado "deVacaciones". Dentro del mismo hay una clase que representa un objeto propio de esta historia. Si se quiere reescribir la aventura se puede prescindir de este paquete. 
