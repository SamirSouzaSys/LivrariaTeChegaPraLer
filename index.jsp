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
        <form action=">
            <label>Pesquisar Livro</label>
            <input type="text" placeholder="digite aqui o título do livro desejado">
            <input type="submit" value="Pesquisar">
        </form>
        <hr>
        <%

        Livro livro;

        ArrayList colecao = (ArrayList)request.getAttribute("colecaoLivros");

        if ( colecao.size() < 0 ) {
                out.println("<p>Nao ha livros cadastrados</p>");
        } else{
            out.println("<table>");
            out.println("<tr>");
                out.println("<th>id</th>");
                out.println("<th>titulo</th>");
                out.println("<th>autor</th>");
                out.println("<th>ano</th>");
                out.println("<th>preco</th>");
                out.println("<th>quantidade</th>");
                out.println("<th>tipo</th>");
                out.println("<th>idEditora</th>");
                out.println("<th>NomeEditora</th>");
            out.println("</tr>");

            for ( int i=0; i < colecao.size(); i++ ) {
                livro = (Livro)colecao.get(i);
        %>
                <tr>
                    <td><%= livro.getId() %> </td>
                    <td><%= livro.getTitulo() %> </td>
                    <td><%= livro.getAutor() %> </td>
                    <td><%= livro.getAno() %> </td>
                    <td><%= livro.getPreco() %> </td>
                    <td><%= livro.getQuantidade() %> </td>
                    <td><%= livro.getTipo() %> </td>
                    <td><%= livro.getIdEditora() %> </td>
                    <td><%= livro.getNomeEditora() %> </td>
                </tr>
                
                <%
            }
        }
                %>
                <hr>
        <%
            for(int i = 1; i < 5 ; i++){
                out.println("<div style='background-color:#FF19; width:140px' >" +
                    " <label><strong>Livro " + i + "</strong></label> <br>" +
                    " <img src='https://encrypted-tbn0.gstatic.com/images?" +
                    "q=tbn%3AANd9GcQm8bm5rH5xMyeGWkrqi9Fd7YjV0SkN3coI8A&usqp=CAU' width='80px'> <br>" +
                    " <label><strong>Título</strong>  asdasd </label> <br>" +
                    " <label><strong>Editora</strong> asdsad </label>" +
                " </div>");
                out.println("<br>");
            }
        %>
    </body>
</html>