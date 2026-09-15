package com.controlstock.api;

// API REST para consultar, registrar y eliminar ventas.
import com.controlstock.dao.VentaDAO;
import com.controlstock.model.Venta;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/api/ventas")
public class VentaApiServlet extends HttpServlet {
    // DAO de ventas y conversor JSON.
    private final VentaDAO dao = new VentaDAO();
    private final Gson gson = new Gson();

    // Consulta y devuelve las ventas registradas en formato JSON.
    protected void doGet(HttpServletRequest q, HttpServletResponse r) throws IOException {
        r.setContentType("application/json;charset=UTF-8");
        try { r.getWriter().print(gson.toJson(dao.listar())); }
        catch (Exception e) { r.setStatus(500); r.getWriter().print(gson.toJson(java.util.Collections.singletonMap("error", e.getMessage()))); }
    }

    // Recibe una venta en JSON, calcula su total mediante el DAO y la registra.
    protected void doPost(HttpServletRequest q, HttpServletResponse r) throws IOException {
        r.setContentType("application/json;charset=UTF-8");
        try { Venta v = gson.fromJson(q.getReader(), Venta.class); v.setId(dao.guardar(v)); r.setStatus(201); r.getWriter().print(gson.toJson(v)); }
        catch (Exception e) { r.setStatus(400); r.getWriter().print(gson.toJson(java.util.Collections.singletonMap("error", e.getMessage()))); }
    }

    // Elimina una venta usando el identificador recibido en la petición.
    protected void doDelete(HttpServletRequest q, HttpServletResponse r) throws IOException {
        r.setContentType("application/json;charset=UTF-8");
        try { dao.eliminar(Integer.parseInt(q.getParameter("id"))); r.getWriter().print("{\"ok\":true}"); }
        catch (Exception e) { r.setStatus(400); r.getWriter().print(gson.toJson(java.util.Collections.singletonMap("error", e.getMessage()))); }
    }
}
