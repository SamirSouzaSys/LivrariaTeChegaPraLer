package controller;

import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletAutenticacao extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Usuario usuario = new Usuario();
		usuario.setConexao(conexao.getConexao());

		String loginUsuario = req.getParameter("loginUsuario");
		String senhaUsuario = req.getParameter("senhaUsuario");

        RequestDispatcher view;
        
		if (usuario.autenticar(loginUsuario, senhaUsuario) == 1) {
			HttpSession sessao = req.getSession();
			sessao.setAttribute("idUsuario", String.valueOf(usuario.getId()));
            sessao.setAttribute("nomeUsuario", usuario.getNome());
            sessao.setAttribute("loginUsuario", usuario.getLogin());

			view = req.getRequestDispatcher("AdminHome.jsp");
		} else{
			view = req.getRequestDispatcher("AdminLogout.jsp");
		}
		
        conexao.fechar();
        view.forward(req, resp);
    }
}