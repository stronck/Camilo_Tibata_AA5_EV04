package com.controlstock.servlet;

// Servlet que carga la información principal del panel de control.
import com.controlstock.dao.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    // Verifica la sesión y prepara clientes, productos y ventas para el dashboard.
    protected void doGet(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{
        if(q.getSession().getAttribute("usuario")==null){r.sendRedirect(q.getContextPath()+"/login.jsp");return;}
        try{q.setAttribute("clientes",new ClienteDAO().listar());q.setAttribute("productos",new ProductoDAO().listar());q.setAttribute("ventas",new VentaDAO().listar());q.getRequestDispatcher("/views/dashboard.jsp").forward(q,r);}
        catch(Exception e){throw new ServletException(e);}
    }
}
