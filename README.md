# Libreria LLave USB  y interfaz de prueba
Mini proyecto Libreria LLave USB, Convierte una usb en una llave de acceso rapido usando esta libreria en tu aplicacion java.
en el codigo de la interfaz de prueba se muestra su correcto uso. 

>Funcionamiento
Se extrae el Numero Identificador Unico de la memoria USB, se genera un archivo oculto en la usb que contiene un codigo encriptado generado apartir del numero Indentificador de la USB y una palabra secreta, luego le cambia el nombre a la USB por "HELLO!" para identificarla mas facilmente cuando se intente usar como llave. 
para detectar la llave usb es el proceso inverso se identifica la USB y se lee el archivo oculto generado anteriormente luego se descifra con la palabra secreta y se valida si corresponde a la usb en insertada.

![Capture](https://github.com/YilmerTapias/LLaveUSB_LIB_-_LOGIN/blob/main/Captures/Captura.JPG)
![Capture](https://github.com/YilmerTapias/LLaveUSB_LIB_-_LOGIN/blob/main/Captures/Captura1.JPG)
![Capture](https://github.com/YilmerTapias/LLaveUSB_LIB_-_LOGIN/blob/main/Captures/Captura2.JPG)
![Capture](https://github.com/YilmerTapias/LLaveUSB_LIB_-_LOGIN/blob/main/Captures/Captura3.JPG)
![Capture](https://github.com/YilmerTapias/LLaveUSB_LIB_-_LOGIN/blob/main/Captures/Captura4.JPG)
