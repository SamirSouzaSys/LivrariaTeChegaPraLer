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
        <h3>gerenciamento de Livros</h3>
        <p>*Gerenciar(Pesquisar, Adicionar, Atualizar e Excluir)</p>
        
        <hr>
            <label><strong>Adicionar Livro</strong></label><br>


        <hr>
        <%
        Livro livro;

        ArrayList colecao = (ArrayList)request.getAttribute("colecaoLivro");        
            
        for ( int i=0; i < colecao.size(); i++ ) {
            livro = (Livro)colecao.get(i);
            %>
                <form method="post" action="adminLivrosEditar">
                    <label>Id do Livro</label>
                    <input value="<%= livro.getId() %>" type="text" placeholder="digite aqui o Id" name="idLivro" > <br>

                    <label>Titulo</label>
                    <input value="<%= livro.getTitulo()%>" type="text" placeholder="digite aqui o Titulo" name="tituloLivro"> <br>
    
                    <label>Autor</label>
                    <input value="<%= livro.getAutor() %>" type="text" placeholder="digite aqui o autor" name="autorLivro"><br>
    
                    <label>Ano</label>
                    <input value="<%= livro.getAno() %>" type="text" placeholder="digite aqui o ano" name="anoLivro"><br>
    
                    <label>Preço</label>
                    <input value="<%= livro.getPreco() %>" type="text" placeholder="digite aqui o Preço" name="precoLivro"><br>
    
                    <label>Quantidade</label>
                    <input value="<%= livro.getQuantidade() %>" type="text" placeholder="digite aqui a Quantidade de exemplares" name="quantidadeLivro"><br>
    
                    <label>Tipo</label>
                    <input value="<%= livro.getTipo() %>" type="text" placeholder="digite aqui o Tipo" name="tipoLivro"><br>
    
                    <label>EditoraId</label>
                    <input value="<%= livro.getIdEditora() %>" type="text" placeholder="digite aqui o id da editora" name="editoraIdLivro"><br>

                    <label>Link para imagem</label>
                    <input value="<%=livro.getImagemLivro()%>" type="text" placeholder="digite aqui o link para a imagem" name="linkImagem"><br>
    
                    <input type="submit" value="Editar" >
                </form>
        <%

        }
        %>
    <br>
        
    </body>
</html>