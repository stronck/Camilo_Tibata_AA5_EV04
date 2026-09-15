package com.controlstock.servlet;

// Servlet encargado de cargar el listado de ventas para la vista.
import com.controlstock.dao.VentaDAO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/ventas")
public class VentaServlet extends HttpServlet {
    // Consulta las ventas y las envía a la página JSP.
    protected void doGet(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{
        try{q.setAttribute("ventas",new VentaDAO().listar());q.getRequestDispatcher("/views/ventas.jsp").forward(q,r);}
        catch(Exception e){throw new ServletException(e);}
    }
}
