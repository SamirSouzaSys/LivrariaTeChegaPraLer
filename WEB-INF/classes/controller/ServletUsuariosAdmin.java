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
    }

    // Adicionar Usuarios - Administrador
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Adicionar Usuarios

		ConexaoBd conexao = new ConexaoBd();
		conexao.conectar();

		Usuario usuario = new Usuario();
		usuario.setConexao(conexao.getConexao());

		String nomeUsuario  = req.getParameter("nomeUsuario");
        String loginUsuario = req.getParameter("loginUsuario");
        String senhaUsuario = req.getParameter("senhaUsuario");

        String resultAdicaoUsuario = null;
        
		if (usuario.adicionarUsuario(nomeUsuario, loginUsuario, senhaUsuario) ) {
            resultAdicaoUsuario = "Usuario adicionado com sucesso!";
        } else {
            resultAdicaoUsuario = "Falha ao adicionar o usuario!";
        }
        
		req.setAttribute("resultAdicaoUsuario", resultAdicaoUsuario);
        
        ArrayList colecao;
        
        colecao = usuario.listarUsuarios(null);
        req.setAttribute("colecaoUsuarios", colecao);

        RequestDispatcher view = req.getRequestDispatcher("AdminUsuarios.jsp");


        conexao.fechar();
        view.forward(req, resp);
    }
}