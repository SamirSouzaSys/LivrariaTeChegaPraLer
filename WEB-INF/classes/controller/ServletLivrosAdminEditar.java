package controller;

import model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletLivrosAdminEditar extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String livroId = req.getParameter("livroId");

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Livro livro = new Livro();
        livro.setConexao(conexao.getConexao());
        
        ArrayList colecao;

        colecao = livro.buscarLivro(livroId);

        req.setAttribute("colecaoLivro", colecao);

        RequestDispatcher view = req.getRequestDispatcher("AdminLivrosEditar.jsp");
        
        view.forward(req, resp);

    }

    // Confirmar Edição Livro - Administrador
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Editar Livro

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Livro livro = new Livro();
		livro.setConexao(conexao.getConexao());

        int idLivro             = Integer.parseInt( req.getParameter("idLivro") );
		String tituloLivro      = req.getParameter("tituloLivro");
        String autorLivro       = req.getParameter("autorLivro");
        int anoLivro            = Integer.parseInt( req.getParameter("anoLivro") );
        Double precoLivro       = Double.parseDouble( req.getParameter("precoLivro") );
        int quantidadeLivro     = Integer.parseInt( req.getParameter("quantidadeLivro") );
        String tipoLivro        = req.getParameter("tipoLivro");
        int editoraIdLivro      = Integer.parseInt( req.getParameter("editoraIdLivro"));
        String linkImagem       = req.getParameter("linkImagem");

        String resultEdicaoLivro = null;
        
		if (livro.editarLivro(idLivro,tituloLivro, autorLivro, anoLivro, precoLivro, quantidadeLivro, tipoLivro, editoraIdLivro, linkImagem) ) {
            resultEdicaoLivro = "Livro editado com sucesso!";
        } else {
            resultEdicaoLivro = "Falha ao editar o livro!";
        }
        
		req.setAttribute("resultEdicaoLivro", resultEdicaoLivro);
        
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