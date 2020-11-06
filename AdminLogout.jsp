<html>
    <head>
        <meta charset="UTF-8">
        <title>Te Chega Pra Ler</title>
    </head>
    <body>

        <%
            HttpSession sessao = request.getSession();
            sessao.invalidate();
            // response.sendRedirect("index.html");
        %>

        <a href="/teChegaPraLer/">Home</a>
        <hr>
        <h2>Te chega pra ler</h2>
        <h3>Sua Sessão foi finalizada ou o seu Login/Senha está(ão) incorretos</h3>
    </body>
</html>