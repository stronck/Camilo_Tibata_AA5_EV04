package com.controlstock.model;

// Modelo que representa un producto del inventario.
public class Producto {
    private int id; private String nombre; private double precio; private int stock;

    // Constructor vacío utilizado por Gson al recibir JSON.
    public Producto() {}

    // Constructor utilizado para crear objetos desde la base de datos.
    public Producto(int id, String nombre, double precio, int stock) { this.id=id; this.nombre=nombre; this.precio=precio; this.stock=stock; }

    // Getters y setters para manejar los datos del producto.
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
    public double getPrecio(){return precio;} public void setPrecio(double v){precio=v;}
    public int getStock(){return stock;} public void setStock(int v){stock=v;}
}
