package controller;

import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletLivros extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Home - Listar Livros

        String livroTitulo = req.getParameter("livroTitulo");

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Livro livro = new Livro();
        livro.setConexao(conexao.getConexao());
        
        ArrayList colecao;

        colecao = livro.listarLivros(livroTitulo);

        req.setAttribute("colecaoLivros", colecao);

        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        
        view.forward(req, resp);
    }
}