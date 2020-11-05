package controller;

import model.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletUsuariosAdmin extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usuarioNome = req.getParameter("usuarioNome");

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Usuario usuario = new Usuario();
        usuario.setConexao(conexao.getConexao());
        
        ArrayList colecao;

        colecao = usuario.listarUsuarios(usuarioNome);

        req.setAttribute("colecaoUsuarios", colecao);

        RequestDispatcher view = req.getRequestDispatcher("AdminUsuarios.jsp");
        
        view.forward(req, resp);

		// if (usuario.autenticar(loginUsuario, senhaUsuario) == 1) {
		// 	HttpSession sessao = req.getSession();
		// 	sessao.setAttribute("idUsuario", String.valueOf(usuario.getId()));
        //     sessao.setAttribute("nomeUsuario", usuario.getNome());
        //     sessao.setAttribute("loginUsuario", usuario.getLogin());

		// 	view = req.getRequestDispatcher("AdminHome.jsp");
		// } else{
		// 	view = req.getRequestDispatcher("AdminLogout.jsp");
		// }
		
        // conexao.fechar();
        // view.forward(req, resp);
    }
}