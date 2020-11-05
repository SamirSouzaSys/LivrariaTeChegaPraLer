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
    
    public void setConexao(Connection con) {
        this.con = con;
    }

    public ArrayList listarLivros()  {
        ArrayList colecao = new ArrayList();

        try {
            ps = con.prepareStatement("SELECT  acevo.id,"
                        + " titulo, "
                        + " autor, "
                        + " ano, "
                        + " preco, "
                        + " quantidade, "
                        + " tipo, "
                        + " idEditora, "
                        + " editora.nome as editoraNome"
                    + " FROM acevo "
                    + " LEFT JOIN editora "
                    + " ON acevo.idEditora = editora.id ");
            
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

                colecao.add(l);
            }
            return colecao;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
}