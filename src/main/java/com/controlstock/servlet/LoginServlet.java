package com.controlstock.servlet;

// Servlet que valida las credenciales e inicia la sesión del usuario.
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // Comprueba las credenciales y redirige al dashboard o devuelve el error.
    protected void doPost(HttpServletRequest q,HttpServletResponse r)throws IOException{
        String u=q.getParameter("usuario"),p=q.getParameter("clave");
        // Credenciales de demostración definidas para la aplicación.
        if("admin".equals(u)&&"1234".equals(p)){q.getSession().setAttribute("usuario",u);r.sendRedirect(q.getContextPath()+"/dashboard");}
        else r.sendRedirect(q.getContextPath()+"/login.jsp?error=1");
    }
}
