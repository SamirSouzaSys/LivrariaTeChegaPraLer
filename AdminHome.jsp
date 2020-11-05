<%@ page import="javax.servlet.http.*" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Te Chega Pra Ler</title>
    </head>
    <body>
        <a href="index.jsp">Home</a>
        <br>
        <a href="AdminLogout.jsp">Sair</a>
        <hr>
        <h2>Te chega pra ler</h2>
        <h3>Administração</h3>
        <hr>
        <p>
            <%
                HttpSession sessao = request.getSession();
                String idUsuario = (String)sessao.getAttribute("idUsuario");
                String nomeUsuario = (String)sessao.getAttribute("nomeUsuario");
                String loginUsuario = (String)sessao.getAttribute("loginUsuario");

                
                out.println("Seja bem-vindo(a), ");
                out.println(nomeUsuario + "!");
            %>
        </p>
        <hr>
        <h3>Você deseja gerenciar qual destes?</h3>
        <p>*Gerenciar(Pesquisar, Adicionar, Atualizar e Excluir)</p>
        <a href="AdminLivros.jsp">Livros</a>
        <br>
        <a href="AdminEditoras.jsp">Editoras</a>
        <br>
        <a href="AdminUsuarios.jsp">Usuários</a>
    </body>
</html>