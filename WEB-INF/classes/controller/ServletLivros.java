package controller;

import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletLivros extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Livro livro = new Livro();
        livro.setConexao(conexao.getConexao());
        
        ArrayList colecao;

        colecao = livro.listarLivros();

        req.setAttribute("colecaoLivros", colecao);

        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        
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