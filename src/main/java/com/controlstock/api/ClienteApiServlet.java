package com.controlstock.api;

// API REST encargada de las operaciones sobre clientes.
import com.controlstock.dao.ClienteDAO;
import com.controlstock.model.Cliente;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/api/clientes")
public class ClienteApiServlet extends HttpServlet {
    // DAO para acceder a la tabla de clientes y Gson para manejar JSON.
    private final ClienteDAO dao = new ClienteDAO();
    private final Gson gson = new Gson();

    // Consulta todos los clientes o uno específico cuando se recibe ?id=N.
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        json(resp);
        try { String id = req.getParameter("id"); resp.getWriter().print(gson.toJson(id == null ? dao.listar() : dao.buscarPorId(Integer.parseInt(id)))); }
        catch (Exception e) { error(resp, e, 500); }
    }

    // Recibe un cliente en JSON, lo guarda y devuelve el registro creado.
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        json(resp);
        try { Cliente c = gson.fromJson(req.getReader(), Cliente.class); c.setId(dao.guardar(c)); resp.setStatus(201); resp.getWriter().print(gson.toJson(c)); }
        catch (Exception e) { error(resp, e, 400); }
    }

    // Elimina un cliente usando el parámetro id de la solicitud.
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        json(resp);
        try {
            String idParam = req.getParameter("id");
            if (idParam == null) { error(resp, new Exception("Debe indicar el parámetro id"), 400); return; }
            Cliente existente = dao.buscarPorId(Integer.parseInt(idParam));
            if (existente == null) { error(resp, new Exception("Cliente no encontrado"), 404); return; }
            dao.eliminar(existente.getId()); resp.getWriter().print("{\"ok\":true}");
        } catch (Exception e) { error(resp, e, 400); }
    }

    // Define que las respuestas se entregan como JSON en UTF-8.
    private void json(HttpServletResponse r) { r.setContentType("application/json;charset=UTF-8"); }

    // Devuelve los errores con su código HTTP y mensaje en JSON.
    private void error(HttpServletResponse r, Exception e, int s) throws IOException { r.setStatus(s); r.getWriter().print(gson.toJson(java.util.Collections.singletonMap("error", e.getMessage()))); }
}
