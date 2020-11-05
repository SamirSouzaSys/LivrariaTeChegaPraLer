<%@ page import="model.*" %>

<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.*" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Te Chega Pra Ler</title>
    </head>
    <body>
        <a href="login.html">Login</a>
        <hr>
        <h2>Te chega pra ler</h2>
        <form method="get" action="">
            <label>Pesquisar Livro</label>
            <input type="text" placeholder="digite aqui o título do livro desejado" name="livroTitulo">
            <input type="submit" value="Pesquisar / Resetar" >
        </form>
        <hr>

        <hr>
        <%
        Livro livro;

        ArrayList colecao = (ArrayList)request.getAttribute("colecaoLivros");        
        
        if ( colecao.size() < 0 ) {
                out.println("<p>Nao ha livros cadastrados</p>");
        } else{        
            for ( int i=0; i < colecao.size(); i++ ) {
                livro = (Livro)colecao.get(i);
                %>
                <div style="background-color:#FF19; width:140px; float:left; margin: 1em;">
                    <img src="<%=livro.getImagemLivro()%>" width='80px'> <br>
                    <label><strong>Livro <%=i+1%> </strong></label> <br>
                    <label><strong>Título</strong><%= livro.getTitulo()%>  </label> <br>
                    <label><strong>Autor</strong>  <%= livro.getAutor() %> </label> <br>
                    <label><strong>Editora</strong> <%= livro.getNomeEditora() %> </label> <br>
                    <label><strong>Id do Livro</strong> <%= livro.getId() %> </label> <br>
                    <label><strong>Ano</strong> <%= livro.getAno() %> </label> <br>
                    <label><strong>Preço</strong> <%= livro.getPreco() %> </label> <br>
                    <label><strong>Editora</strong> <%= livro.getQuantidade() %> </label> <br>
                    <label><strong>Tipo</strong> <%= livro.getTipo() %> </label> <br>
                    <label><strong>id da Editora</strong> <%= livro.getIdEditora() %> </label>

                </div>
            <%

            }
        }
        %>
    <br>

        <hr>
        
    </body>
</html>