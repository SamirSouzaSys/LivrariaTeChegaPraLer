package controller;

import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletLivrosAdminAdicionar extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String livroTitulo = req.getParameter("livroTitulo");

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Livro livro = new Livro();
        livro.setConexao(conexao.getConexao());
        
        ArrayList colecao;

        colecao = livro.listarLivros(livroTitulo);

        req.setAttribute("colecaoLivros", colecao);

        RequestDispatcher view = req.getRequestDispatcher("AdminLivros.jsp");
        
        view.forward(req, resp);

    }

    
}