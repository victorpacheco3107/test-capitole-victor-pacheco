Arquitectura de la aplicación: Hexagonal

Submodulos:
* domain
* infrastrcture
* application
* laucher

Para tener en cuenta:
* El cliente http que se usó fue feign, los llamados que se hacen tiene un timeout de 2 segundos. Se puede cambiar este tiempo estableciendo la variable de entorno APP_CLIENT_READ_TIMEOUT (en milesegundos).
* Si al consultar el detalle de un producto se supera el timeout, este producto es ignorado.
* La aplicación guarda en caché la respuesta del cliente http. El TTL de la caché es de 10 minutos, este tiempo se puede cambiar estableciendo la variable de entorno APP_CACHE_DURATION (en milesegundos).
* Si un producto tiene 3 productos similares, y si al consultar el detalle de uno de estos, falla el servicio, este producto se ignora y solo se retorna el detalle de los otros dos productos.
* Si desea cambiar la url del cliente http, puede hacerlo estableciendo la variable de entorno APP_CLIENT_URL.
* Por cuestión de tiempo solo se le agregó javadoc y pruebas unitarias a la capa de dominio.
* Los detalles de los productos se consultan en parelelo, haciendo uso de un parallelStream, así:

En las imagenes se puede ver que la consulta de los detalles de los productos se hace en paralelo, en diferentes hilos de ejecución. Esto se logra fácilmente haciendo uso de la programación funcional que nos ofrece java.