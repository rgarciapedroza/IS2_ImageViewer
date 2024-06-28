# IS2_ImageViewer

Estamos usando el modelo mvc (Modelo Vista Controlador) 

En el ImageModel se encapsulan los datos, pero no tiene información ni de la vista (ImagePanel) ni del controlador (MainFrame)

En el ImagePanel es donde podemos representar la interfaz de usuario, recibe y muestra las actualizaciones de los datos al usuario

En el MainFrame actualizamos el modelo en funcion de las entradas del usuario. Se comunica con el ImagePanel