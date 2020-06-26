package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class C14SessionLogin extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter out;
        resp.setContentType("text/html");
        out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>LER FAZ BEM Livraria</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Até logo!</p>");
        out.println("</body>");
        out.println("</html>");
        HttpSession sessao = req.getSession();
        sessao.invalidate();
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter out;
        resp.setContentType("text/html");
        out = resp.getWriter();
        HttpSession sessao = req.getSession();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>LER FAZ BEM Livraria</title>");
        out.println("</head>");
        out.println("<body>");
        
        if (req.getParameter("username").equals("user") && req.getParameter("password").equals("123456"))
        {
            sessao.setAttribute("logado", "true");
            sessao.setAttribute("usuario", req.getParameter("username"));
            out.println("<a href=\"C13Vitrine.jsp\">Navegar pela loja</a>");
        }
        else
        {
            out.println("<p>Username e/ou password incorretos</p>");
            out.println("<a href=\"C12Login.jsp\">Tentar novamente</a>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}