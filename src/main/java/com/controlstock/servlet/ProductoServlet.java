package com.controlstock.servlet;
import com.controlstock.dao.ProductoDAO; import javax.servlet.*; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.*;
@WebServlet("/productos") public class ProductoServlet extends HttpServlet {protected void doGet(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{try{q.setAttribute("productos",new ProductoDAO().listar());q.getRequestDispatcher("/views/productos.jsp").forward(q,r);}catch(Exception e){throw new ServletException(e);}}}
