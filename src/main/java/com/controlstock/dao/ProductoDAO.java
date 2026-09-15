package com.controlstock.dao;

// Capa de acceso a datos para la entidad Producto.
import com.controlstock.model.Producto;
import java.sql.*;
import java.util.*;

public class ProductoDAO {
    // Lista los productos disponibles en el inventario.
    public List<Producto> listar() throws SQLException {
        List<Producto> r = new ArrayList<>();
        try (Connection c = Database.getConnection(); Statement s = c.createStatement(); ResultSet x = s.executeQuery("SELECT * FROM productos ORDER BY id")) {
            while (x.next()) r.add(new Producto(x.getInt("id"), x.getString("nombre"), x.getDouble("precio"), x.getInt("stock")));
        }
        return r;
    }

    // Busca un producto por su identificador.
    public Producto buscarPorId(int id) throws SQLException {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("SELECT * FROM productos WHERE id=?")) {
            p.setInt(1, id);
            try (ResultSet x = p.executeQuery()) { return x.next() ? new Producto(x.getInt("id"), x.getString("nombre"), x.getDouble("precio"), x.getInt("stock")) : null; }
        }
    }

    // Registra un producto y obtiene el ID generado.
    public int guardar(Producto v) throws SQLException {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("INSERT INTO productos(nombre,precio,stock) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            p.setString(1, v.getNombre()); p.setDouble(2, v.getPrecio()); p.setInt(3, v.getStock()); p.executeUpdate();
            try (ResultSet x = p.getGeneratedKeys()) { x.next(); return x.getInt(1); }
        }
    }

    // Descuenta unidades del inventario y evita dejar stock negativo.
    public void descontarStock(int id, int cantidad) throws SQLException {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("UPDATE productos SET stock=stock-? WHERE id=? AND stock>=?")) {
            p.setInt(1, cantidad); p.setInt(2, id); p.setInt(3, cantidad);
            if (p.executeUpdate() == 0) throw new SQLException("Stock insuficiente");
        }
    }

    // Elimina un producto por su ID.
    public void eliminar(int id) throws SQLException {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("DELETE FROM productos WHERE id=?")) { p.setInt(1, id); p.executeUpdate(); }
    }
}
