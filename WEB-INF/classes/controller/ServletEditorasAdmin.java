package controller;

import model.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ServletEditorasAdmin extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editoraNome = req.getParameter("editoraNome");

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Editora editora = new Editora();
        editora.setConexao(conexao.getConexao());
        
        ArrayList colecao;

        colecao = editora.listarEditoras(editoraNome);

        req.setAttribute("colecaoEditoras", colecao);

        RequestDispatcher view = req.getRequestDispatcher("AdminEditoras.jsp");
        
        view.forward(req, resp);

    }

    // Adicionar Editoras - Administrador
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Adicionar Editoras

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Editora editora = new Editora();
		editora.setConexao(conexao.getConexao());

		String nomeEditora  = req.getParameter("nomeEditora");
        

        String resultAdicaoEditora = null;
        
		if (editora.adicionarEditora(nomeEditora) ) {
            resultAdicaoEditora = "Editora adicionado com sucesso!";
        } else {
            resultAdicaoEditora = "Falha ao adicionar o editora!";
        }
        
		req.setAttribute("resultAdicaoEditora", resultAdicaoEditora);
        
        ArrayList colecao;

        colecao = editora.listarEditoras(null);

        req.setAttribute("colecaoEditoras", colecao);

        RequestDispatcher view = req.getRequestDispatcher("AdminEditoras.jsp");
        
        view.forward(req, resp);
    }
}

