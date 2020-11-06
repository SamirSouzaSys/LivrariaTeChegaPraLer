<%@ page import="model.*" %>

<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.*" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Te Chega Pra Ler</title>
    </head>
    <body>
        <a href="AdminHome.jsp">Home Admin</a>
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
        <h3>gerenciamento de Usuários</h3>
        <p>*Gerenciar(Pesquisar, Adicionar, Atualizar e Excluir)</p>
        
        <hr>
        <form method="get" action="">
            <label>Pesquisar Usuario</label>
            <input type="text" placeholder="digite aqui o nome do usuário desejado" name="usuarioNome">
            <input type="submit" value="Pesquisar / Resetar" >
        </form>

        <hr>
        <%
        String mensagemAdicao = (String)request.getAttribute("resultAdicaoUsuario");
            if( mensagemAdicao != null ){
                %> <h3> <%= mensagemAdicao %> </h3> <%
            } %>
        <label><strong>Adicionar Usuario</strong></label><br>

        <form method="post" action="adminUsuariosAdicionar">
            <label>Nome</label>
            <input type="text" placeholder="digite aqui o Nome" name="nomeUsuario"> <br>

            <label>Login</label>
            <input type="text" placeholder="digite aqui o Login" name="loginUsuario"><br>

            <label>Senha</label>
            <input type="text" placeholder="digite aqui a senha" name="senhaUsuario"><br>

            <input type="submit" value="Adicionar" >
        </form>

        <hr>
        <%
        Usuario usuario;

        ArrayList colecao = (ArrayList)request.getAttribute("colecaoUsuarios");        
        
        if ( colecao.size() < 0 ) {
                out.println("<p>Nao há usuários cadastrados</p>");
        } else{        
            for ( int i=0; i < colecao.size(); i++ ) {
                usuario = (Usuario)colecao.get(i);
                %>
                <div style="background-color:#FF19; width:140px; float:left; margin: 1em;">
                    <label><strong>Usuario <%=i+1%> </strong></label> <br>
                    <label><strong>idUsuario</strong><%= usuario.getId()%>  </label> <br>
                    <label><strong>Nome</strong>  <%= usuario.getNome() %> </label> <br>
                    <label><strong>Login</strong> <%= usuario.getLogin() %> </label> <br>
                    <label><strong>Senha</strong> <%= usuario.getSenha() %> </label> <br>

                </div>
            <%

            }
        }
        %>
    <br>
        
    </body>
</html>