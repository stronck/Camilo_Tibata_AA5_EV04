# Camilo_Tibata_AA5_EV04

Aplicación web **ControlStock** desarrollada en Java Servlet/JSP tomando como referencia la estructura visible en las capturas suministradas.

## Incluye
- Login y dashboard.
- Gestión y consulta de clientes, productos y ventas.
- API REST para clientes, productos y ventas.
- Estructura separada en `api`, `dao`, `model` y `servlet`.
- Vistas JSP y estilos CSS.
- Base de datos H2 embebida que crea sus tablas automáticamente.

## Requisitos
- Java 11.
- Maven 3.8 o superior.
- Apache Tomcat 9 o servidor compatible con Servlet 4 / `javax.servlet`.

## Compilar
```bash
mvn clean package
```
Se genera `target/ControlStock.war`.

## Desplegar
Copiar `target/ControlStock.war` en la carpeta `webapps` de Tomcat y abrir:

`http://localhost:8080/ControlStock/`

## Usuario de prueba
- Usuario: `admin`
- Contraseña: `1234`

## Endpoints
- `GET /ControlStock/api/clientes`
- `POST /ControlStock/api/clientes`
- `DELETE /ControlStock/api/clientes?id=1`
- `GET /ControlStock/api/productos`
- `POST /ControlStock/api/productos`
- `DELETE /ControlStock/api/productos?id=1`
- `GET /ControlStock/api/ventas`
- `POST /ControlStock/api/ventas`
- `DELETE /ControlStock/api/ventas?id=1`

### Ejemplo cliente
```json
{"nombre":"Camilo","correo":"camilo@example.com","telefono":"3000000000"}
```

### Ejemplo producto
```json
{"nombre":"Teclado","precio":85000,"stock":10}
```

### Ejemplo venta
```json
{"clienteId":1,"productoId":1,"cantidad":2}
```

> Las capturas no muestran todo el proyecto original, por lo que las partes faltantes fueron completadas para dejar una aplicación coherente y desplegable.