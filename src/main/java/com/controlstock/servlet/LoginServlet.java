package com.controlstock.servlet;
import javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import java.io.*;
@WebServlet("/login") public class LoginServlet extends HttpServlet {protected void doPost(HttpServletRequest q,HttpServletResponse r)throws IOException{String u=q.getParameter("usuario"),p=q.getParameter("clave");if("admin".equals(u)&&"1234".equals(p)){q.getSession().setAttribute("usuario",u);r.sendRedirect(q.getContextPath()+"/dashboard");}else r.sendRedirect(q.getContextPath()+"/login.jsp?error=1");}}
