<html>
    <head>
        <meta charset="UTF-8">
        <title>Te Chega Pra Ler</title>
    </head>
    <body>
        <a href="login.html">Login</a>
        <hr>
        <h2>Te chega pra ler</h2>
        <form action="">
            <label>Pesquisar Livro</label>
            <input type="text" placeholder="digite aqui o título do livro desejado">
            <input type="submit" value="Pesquisar">
        </form>
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