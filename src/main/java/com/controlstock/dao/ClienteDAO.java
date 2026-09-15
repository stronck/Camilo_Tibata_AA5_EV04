package com.controlstock.dao;

// Capa de acceso a datos para la entidad Cliente.
import com.controlstock.model.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDAO {
    // Consulta todos los clientes ordenados por identificador.
    public List<Cliente> listar() throws SQLException {
        List<Cliente> r = new ArrayList<>();
        try (Connection c = Database.getConnection(); Statement s = c.createStatement(); ResultSet x = s.executeQuery("SELECT * FROM clientes ORDER BY id")) {
            while (x.next()) r.add(new Cliente(x.getInt("id"), x.getString("nombre"), x.getString("correo"), x.getString("telefono")));
        }
        return r;
    }

    // Busca un cliente específico utilizando su ID.
    public Cliente buscarPorId(int id) throws SQLException {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("SELECT * FROM clientes WHERE id=?")) {
            p.setInt(1, id);
            try (ResultSet x = p.executeQuery()) { return x.next() ? new Cliente(x.getInt("id"), x.getString("nombre"), x.getString("correo"), x.getString("telefono")) : null; }
        }
    }

    // Inserta un cliente y devuelve el ID generado por la base de datos.
    public int guardar(Cliente v) throws SQLException {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("INSERT INTO clientes(nombre,correo,telefono) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            p.setString(1, v.getNombre()); p.setString(2, v.getCorreo()); p.setString(3, v.getTelefono()); p.executeUpdate();
            try (ResultSet x = p.getGeneratedKeys()) { x.next(); return x.getInt(1); }
        }
    }

    // Elimina un cliente por su ID.
    public void eliminar(int id) throws SQLException {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("DELETE FROM clientes WHERE id=?")) { p.setInt(1, id); p.executeUpdate(); }
    }
}
