package com.controlstock.servlet;
import com.controlstock.dao.ClienteDAO; import javax.servlet.*; import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.*;
@WebServlet("/clientes") public class ClienteServlet extends HttpServlet {protected void doGet(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{try{q.setAttribute("clientes",new ClienteDAO().listar());q.getRequestDispatcher("/views/clientes.jsp").forward(q,r);}catch(Exception e){throw new ServletException(e);}}}
