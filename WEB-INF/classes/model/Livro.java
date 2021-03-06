package model;

import java.sql.*;
import java.util.*;

public class Livro {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    private int idLivro;
    private String tituloLivro;
    private String autorLivro;
    private int anoLivro;
    private Double precoLivro;
    private int quantidadeLivro;
    private String tipoLivro;
    private int editoraIdLivro;
    private String editoraNomeLivro;
    private String imagemLivro;

    public void setConexao(Connection con) {
        this.con = con;
    }

    public ArrayList listarLivros(String buscarLivroTitulo)  {
        ArrayList colecao = new ArrayList();
        
        String sqlPesquisa = " ";
        if(buscarLivroTitulo != null)
            sqlPesquisa = " where  titulo like '%" + buscarLivroTitulo + "%' ";

        try {
            ps = con.prepareStatement("SELECT  acevo.id,"
                        + " titulo, "
                        + " autor, "
                        + " ano, "
                        + " preco, "
                        + " quantidade, "
                        + " tipo, "
                        + " idEditora, "
                        + " editora.nome as editoraNome,"
                        + " imagem"
                    + " FROM acevo "
                    + " LEFT JOIN editora "
                    + " ON acevo.idEditora = editora.id "
                    + sqlPesquisa);
            
            rs = ps.executeQuery();
            
            Livro l;

            while( rs.next()){
                l = new Livro();

                l.idLivro          = rs.getInt("id");
                l.tituloLivro      = rs.getString("titulo");
                l.autorLivro       = rs.getString("autor");
                l.anoLivro         = rs.getInt("ano");
                l.precoLivro       = rs.getDouble("preco");
                l.quantidadeLivro  = rs.getInt("quantidade");
                l.tipoLivro        = rs.getString("tipo");
                l.editoraIdLivro   = rs.getInt("idEditora");
                l.editoraNomeLivro = rs.getString("editoraNome") ;
                l.imagemLivro      = rs.getString("imagem") ;

                colecao.add(l);
            }
            return colecao;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList buscarLivro(String idLivro)  {
        ArrayList colecao = new ArrayList();
        
        String sqlPesquisa = " ";
        if(idLivro != null){
            sqlPesquisa = " where  acevo.id =" + idLivro + " ";
        }else{
            return null;
        }

        try {
            ps = con.prepareStatement("SELECT  acevo.id,"
                        + " titulo, "
                        + " autor, "
                        + " ano, "
                        + " preco, "
                        + " quantidade, "
                        + " tipo, "
                        + " idEditora, "
                        + " editora.nome as editoraNome,"
                        + " imagem"
                    + " FROM acevo "
                    + " LEFT JOIN editora "
                    + " ON acevo.idEditora = editora.id "
                    + sqlPesquisa);
            
            rs = ps.executeQuery();
            
            Livro l;

            while( rs.next()){
                l = new Livro();

                l.idLivro          = rs.getInt("id");
                l.tituloLivro      = rs.getString("titulo");
                l.autorLivro       = rs.getString("autor");
                l.anoLivro         = rs.getInt("ano");
                l.precoLivro       = rs.getDouble("preco");
                l.quantidadeLivro  = rs.getInt("quantidade");
                l.tipoLivro        = rs.getString("tipo");
                l.editoraIdLivro   = rs.getInt("idEditora");
                l.editoraNomeLivro = rs.getString("editoraNome") ;
                l.imagemLivro      = rs.getString("imagem") ;

                colecao.add(l);
            }
            return colecao;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean editarLivro(int idLivro, String tituloLivro, String autorLivro, int anoLivro, Double precoLivro, int quantidadeLivro, String tipoLivro, int editoraIdLivro, String linkImagem){
        try {
            ps = con.prepareStatement("UPDATE acevo SET titulo = ?,autor = ?,ano= ? ,preco= ? ,quantidade= ?,tipo= ?,idEditora= ?,imagem= ? WHERE acevo.id=?");
            
            ps.setString(1, tituloLivro);
            ps.setString(2, autorLivro);
            ps.setInt(3, anoLivro);
            ps.setDouble(4, precoLivro);
            ps.setInt(5, quantidadeLivro);
            ps.setString(6, tipoLivro);
            ps.setInt(7, editoraIdLivro);
            ps.setString(8, linkImagem);
            ps.setInt(9, idLivro);

            ps.executeUpdate();
            
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean adicionarLivro(String tituloLivro, String autorLivro, int anoLivro, Double precoLivro, int quantidadeLivro, String tipoLivro, String editoraIdLivro, String linkImagem){
        try {
            int id = gerarId();

            ps = con.prepareStatement("INSERT INTO acevo(id,titulo,autor,ano,preco,quantidade,tipo,idEditora,imagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            ps.setInt(1, id);
            ps.setString(2, tituloLivro);
            ps.setString(3, autorLivro);
            ps.setInt(4, anoLivro);
            ps.setDouble(5, precoLivro);
            ps.setInt(6, quantidadeLivro);
            ps.setString(7, tipoLivro);
            ps.setString(8, editoraIdLivro);
            ps.setString(9, linkImagem);

            ps.executeUpdate();
            
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public int gerarId() {
        String novoId;

        try {
            ps = con.prepareStatement("SELECT MAX(id) as maiorId FROM acevo");
            rs = ps.executeQuery();
            rs.next();
            
            novoId = rs.getString("maiorId");

            if (novoId == null)
                return 1;
            else
                return Integer.parseInt(novoId) + 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int getId() {
        return idLivro;}
    public String getTitulo() {
        return tituloLivro;}
    public String getAutor() {
        return autorLivro;}
    public int getAno() {
        return anoLivro;}
    public Double getPreco() {
        return precoLivro;}
    public int getQuantidade() {
        return quantidadeLivro;}
    public String getTipo() {
        return tipoLivro;}
    public int getIdEditora() {
        return editoraIdLivro;}
    public String getNomeEditora() {
        return editoraNomeLivro;}
    public String getImagemLivro() {
        return imagemLivro;}
}