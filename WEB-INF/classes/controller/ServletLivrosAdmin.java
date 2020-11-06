package controller;

import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletLivrosAdmin extends HttpServlet{
    // Listar Livros - Administrador
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String livroTitulo = req.getParameter("livroTitulo");

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Livro livro = new Livro();
        livro.setConexao(conexao.getConexao());
        
        ArrayList colecao;

        colecao = livro.listarLivros(livroTitulo);

        req.setAttribute("colecaoLivros", colecao);
        
        // RequestDispatcher view = req.getRequestDispatcher("adminLivros");
        RequestDispatcher view = req.getRequestDispatcher("AdminLivros.jsp");
        
        view.forward(req, resp);
    }

    // Adicionar Livros - Administrador
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Adicionar Livros

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Livro livro = new Livro();
		livro.setConexao(conexao.getConexao());

		String tituloLivro     = req.getParameter("tituloLivro");
        String autorLivro      = req.getParameter("autorLivro");
        int anoLivro           = Integer.parseInt( req.getParameter("anoLivro") );
        Double precoLivro      = Double.parseDouble( req.getParameter("precoLivro") );
        int quantidadeLivro    = Integer.parseInt( req.getParameter("quantidadeLivro") );
        String tipoLivro       = req.getParameter("tipoLivro");
        String editoraIdLivro  = req.getParameter("editoraIdLivro");
        String linkImagem      = req.getParameter("linkImagem");

        String resultAdicaoLivro = null;
        
		if (livro.adicionarLivro(tituloLivro, autorLivro, anoLivro, precoLivro, quantidadeLivro, tipoLivro, editoraIdLivro, linkImagem) ) {
            resultAdicaoLivro = "Livro adicionado com sucesso!";
        } else {
            resultAdicaoLivro = "Falha ao adicionar o livro!";
        }
        
		req.setAttribute("resultAdicaoLivro", resultAdicaoLivro);
        
        ArrayList colecao;
        colecao = livro.listarLivros(null);
        req.setAttribute("colecaoLivros", colecao);
        
        RequestDispatcher view;
        // RequestDispatcher view = req.getRequestDispatcher("adminLivros.jsp");
        view = req.getRequestDispatcher("AdminLivros.jsp");


        conexao.fechar();
        view.forward(req, resp);
    }
}