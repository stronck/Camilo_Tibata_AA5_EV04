package com.controlstock.dao;

// Clase responsable de abrir la conexión y preparar la base de datos H2.
import java.sql.*;

public final class Database {
    // Base de datos local H2 utilizada por la aplicación.
    private static final String URL = "jdbc:h2:~/controlstock;AUTO_SERVER=TRUE";

    // Al iniciar la aplicación se cargan H2 y se crean las tablas necesarias.
    static {
        try {
            Class.forName("org.h2.Driver");
            try (Connection c = getConnection(); Statement s = c.createStatement()) {
                // Tabla principal de clientes.
                s.execute("CREATE TABLE IF NOT EXISTS clientes(id IDENTITY PRIMARY KEY,nombre VARCHAR(120) NOT NULL,correo VARCHAR(120),telefono VARCHAR(40))");
                // Tabla de productos y existencias.
                s.execute("CREATE TABLE IF NOT EXISTS productos(id IDENTITY PRIMARY KEY,nombre VARCHAR(120) NOT NULL,precio DECIMAL(12,2) NOT NULL,stock INT NOT NULL)");
                // Tabla que registra las ventas realizadas.
                s.execute("CREATE TABLE IF NOT EXISTS ventas(id IDENTITY PRIMARY KEY,cliente_id BIGINT NOT NULL,producto_id BIGINT NOT NULL,cantidad INT NOT NULL,total DECIMAL(12,2) NOT NULL,fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
            }
        } catch (Exception e) {
            // Si la base de datos no puede inicializarse, se detiene la inicialización.
            throw new ExceptionInInitializerError(e);
        }
    }

    // Evita crear instancias de esta clase utilitaria.
    private Database() {}

    // Obtiene una conexión nueva hacia la base de datos H2.
    public static Connection getConnection() throws SQLException { return DriverManager.getConnection(URL, "sa", ""); }
}
