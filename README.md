# Conversor Multiple en Java


- Descripcion:

El siguiente software fue realizado en el lenguaje de programacion JAVA en el IDE Eclipse con la version JAVA 8 en donde se empleo el patron de diseñor MVC, interfaces y POO. El mismo trata de un conversor de monedas, temperatura y pesos. A continuacion un desglose del software.

- Conversor de monedas:

Para llevar a cabo este conversor utilizamos programacion orientada a objecto, herencias y polimorfismo y varias API gratuita pero limitadas, vamos a presentarlas a continuacion:

1- https://v6.exchangerate-api.com, en esta consumimos los recursos de las tasas de cambio, solo tuve que pasarle los parametros de su documentacion para recibir el cambio de la moneda que deseo, estos parametros se los pasa los JCOMBOBOX atravez de una peticion GET.

2- https://flagcdn.com, en esta pude obtener las banderas de los diferentes paises formateada con el tamaño de mi conveniencia, por otro lado, a medida que vamos cambian los valores de los JCOMBOBOX se van cambiando las imagenes correspondiente al pais que el JCOMBOBOX pase como parametro a la API.

Este conversor fue inspirado en el de google en cuanto a funcionamiento.

- Conversor de temperatura:

Esta conversor fue desarrollado con programacion orientada objecto, simpletemente cree varias funciones con las formulas de conversion y el resto lo hizo la interfaz grafica, en donde veremos en las imagenes de abajo que mientras cambiamos su valor cambian sus iconos y tambien podemos ver el resultado de su conversion en un JLABEL para mayor entendimiento.

- Conversor de pesos:

Para este conversro al igual que el anterior me soporte de la programacion orientada a objectos y de las formulas matematicas de cada peso, en las imagenes a continuacion veremos el resultado.

# Conversor multiproposito.

![]([ezgif.com-gif-maker.gif](https://i.postimg.cc/LX5XRtd3/ezgif-com-gif-maker.gif))

# Diseño.
- Java Swing

