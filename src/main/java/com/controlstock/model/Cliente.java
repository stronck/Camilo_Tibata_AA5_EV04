package com.controlstock.model;
public class Cliente {
  private int id; private String nombre; private String correo; private String telefono;
  public Cliente(){}
  public Cliente(int id,String nombre,String correo,String telefono){this.id=id;this.nombre=nombre;this.correo=correo;this.telefono=telefono;}
  public int getId(){return id;} public void setId(int id){this.id=id;}
  public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
  public String getCorreo(){return correo;} public void setCorreo(String v){correo=v;}
  public String getTelefono(){return telefono;} public void setTelefono(String v){telefono=v;}
}