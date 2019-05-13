# demorappi

Descripción de la arquitectura y el diseño de la aplicación.

## Acerca de la aplicación

Esta aplicación esta desarrollada con Java android nativo, actualmente se esta incluyendo una arquitetura en 3 capas app, core y networkcontroller

### NetworkController
 Este paquete fue escrito con el fin de manejar los POJOS y el cliente de retrofit para el request y response de los servicios de vídeo

### Core
 Este paquete fue escrito para tener toda la lógica de negocios, comunicadores para el UI, controladores (Abstractos que igualmente si se tiene un negocio con los mismos parámetros se puede configurar para ello, middleware para los videos, archivo de constantes y cosas para el UI del paquete de la **App**
### App
 Este paquete fue escrito para tener toda la parte UI, la cuál consume cosas de **Core** y **networkcontroller** para solicitar información para pintar

Actualmente se han incluido proyectos de código abierto como **Retrofit**, **GSON**, **Picasso** y se han realizado mejoras para adaptarlos a la demo.
