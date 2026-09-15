package com.controlstock.dao;
import java.sql.*;
public final class Database {
  private static final String URL="jdbc:h2:~/controlstock;AUTO_SERVER=TRUE";
  static { try { Class.forName("org.h2.Driver"); try(Connection c=getConnection(); Statement s=c.createStatement()) {
    s.execute("CREATE TABLE IF NOT EXISTS clientes(id IDENTITY PRIMARY KEY,nombre VARCHAR(120) NOT NULL,correo VARCHAR(120),telefono VARCHAR(40))");
    s.execute("CREATE TABLE IF NOT EXISTS productos(id IDENTITY PRIMARY KEY,nombre VARCHAR(120) NOT NULL,precio DECIMAL(12,2) NOT NULL,stock INT NOT NULL)");
    s.execute("CREATE TABLE IF NOT EXISTS ventas(id IDENTITY PRIMARY KEY,cliente_id BIGINT NOT NULL,producto_id BIGINT NOT NULL,cantidad INT NOT NULL,total DECIMAL(12,2) NOT NULL,fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
  }} catch(Exception e){ throw new ExceptionInInitializerError(e); } }
  private Database(){} public static Connection getConnection() throws SQLException { return DriverManager.getConnection(URL,"sa",""); }
}