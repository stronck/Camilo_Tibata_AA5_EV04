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
