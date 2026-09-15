package com.controlstock.servlet;

// Servlet encargado de preparar la vista del inventario de productos.
import com.controlstock.dao.ProductoDAO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    // Consulta los productos y los envía a la vista JSP.
    protected void doGet(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{
        try{q.setAttribute("productos",new ProductoDAO().listar());q.getRequestDispatcher("/views/productos.jsp").forward(q,r);}
        catch(Exception e){throw new ServletException(e);}
    }
}
