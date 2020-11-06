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
        <h3>gerenciamento de Editoras</h3>
        <p>*Gerenciar(Pesquisar, Adicionar, Atualizar e Excluir)</p>
        
        <hr>
        <form method="get" action="">
            <label>Pesquisar Editora</label>
            <input type="text" placeholder="digite aqui o nome da editora desejada" name="editoraNome">
            <input type="submit" value="Pesquisar / Resetar" >
        </form>
        <hr>
        <%
        String mensagemAdicao = (String)request.getAttribute("resultAdicaoEditora");
            if( mensagemAdicao != null ){
                %> <h3> <%= mensagemAdicao %> </h3> <%
            } %>
        <label><strong>Adicionar Editora</strong></label><br>

        <form method="post" action="adminEditorasAdicionar">
            <label>Nome</label>
            <input type="text" placeholder="digite aqui o Nome" name="nomeEditora"> <br>

            <input type="submit" value="Adicionar" >
        </form>
        <hr>
        <%
        Editora editora;

        ArrayList colecao = (ArrayList)request.getAttribute("colecaoEditoras");        
        
        if ( colecao.size() < 0 ) {
                out.println("<p>Nao há editoras cadastrados</p>");
        } else{        
            for ( int i=0; i < colecao.size(); i++ ) {
                editora = (Editora)colecao.get(i);
                %>
                <div style="background-color:#FF19; width:140px; float:left; margin: 1em;">
                    <label><strong>Editora <%=i+1%> </strong></label> <br>
                    <label><strong>idEditora</strong><%= editora.getId()%>  </label> <br>
                    <label><strong>Nome</strong>  <%= editora.getNome() %> </label> <br>

                </div>
            <%

            }
        }
        %>
    <br>
        
    </body>
</html>