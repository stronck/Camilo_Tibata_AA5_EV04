package com.controlstock.servlet;

// Servlet encargado de preparar la vista de clientes.
import com.controlstock.dao.ClienteDAO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {
    // Consulta los clientes y los envía a la página JSP correspondiente.
    protected void doGet(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{
        try { q.setAttribute("clientes",new ClienteDAO().listar()); q.getRequestDispatcher("/views/clientes.jsp").forward(q,r); }
        catch(Exception e){throw new ServletException(e);}
    }
}
