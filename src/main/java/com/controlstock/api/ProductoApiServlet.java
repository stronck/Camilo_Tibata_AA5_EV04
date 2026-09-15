package com.controlstock.api;

// API REST para consultar, registrar y eliminar productos.
import com.controlstock.dao.ProductoDAO;
import com.controlstock.model.Producto;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/api/productos")
public class ProductoApiServlet extends HttpServlet {
    // DAO de productos y conversor JSON.
    private final ProductoDAO dao = new ProductoDAO();
    private final Gson gson = new Gson();

    // Devuelve todos los productos o un producto por su identificador.
    protected void doGet(HttpServletRequest q, HttpServletResponse r) throws IOException {
        r.setContentType("application/json;charset=UTF-8");
        try { String id = q.getParameter("id"); r.getWriter().print(gson.toJson(id == null ? dao.listar() : dao.buscarPorId(Integer.parseInt(id)))); }
        catch (Exception e) { r.setStatus(500); r.getWriter().print(gson.toJson(java.util.Collections.singletonMap("error", e.getMessage()))); }
    }

    // Recibe un producto en JSON, lo guarda y retorna el producto creado.
    protected void doPost(HttpServletRequest q, HttpServletResponse r) throws IOException {
        r.setContentType("application/json;charset=UTF-8");
        try { Producto p = gson.fromJson(q.getReader(), Producto.class); p.setId(dao.guardar(p)); r.setStatus(201); r.getWriter().print(gson.toJson(p)); }
        catch (Exception e) { r.setStatus(400); r.getWriter().print(gson.toJson(java.util.Collections.singletonMap("error", e.getMessage()))); }
    }

    // Elimina el producto indicado mediante el parámetro id.
    protected void doDelete(HttpServletRequest q, HttpServletResponse r) throws IOException {
        r.setContentType("application/json;charset=UTF-8");
        try { dao.eliminar(Integer.parseInt(q.getParameter("id"))); r.getWriter().print("{\"ok\":true}"); }
        catch (Exception e) { r.setStatus(400); r.getWriter().print(gson.toJson(java.util.Collections.singletonMap("error", e.getMessage()))); }
    }
}
