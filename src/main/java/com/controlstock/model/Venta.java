package com.controlstock.model;
public class Venta {
  private int id; private int clienteId; private int productoId; private int cantidad; private double total; private String fecha;
  public Venta(){}
  public Venta(int id,int clienteId,int productoId,int cantidad,double total,String fecha){this.id=id;this.clienteId=clienteId;this.productoId=productoId;this.cantidad=cantidad;this.total=total;this.fecha=fecha;}
  public int getId(){return id;} public void setId(int v){id=v;} public int getClienteId(){return clienteId;} public void setClienteId(int v){clienteId=v;}
  public int getProductoId(){return productoId;} public void setProductoId(int v){productoId=v;} public int getCantidad(){return cantidad;} public void setCantidad(int v){cantidad=v;}
  public double getTotal(){return total;} public void setTotal(double v){total=v;} public String getFecha(){return fecha;} public void setFecha(String v){fecha=v;}
}