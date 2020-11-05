package model;

import java.sql.*;
import java.util.*;

public class Editora {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    private int idEditora;
    private String nomeEditora;

    public void setConexao(Connection con) {
        this.con = con;
    }

    public ArrayList listarEditoras(String buscarEditorasNome)  {
        ArrayList colecao = new ArrayList();
        
        String sqlPesquisa = " ";
        if(buscarEditorasNome != null)
            sqlPesquisa = " where nome like '%" + buscarEditorasNome + "%' ";

        try {
            ps = con.prepareStatement("SELECT  id,"
                        + " nome "
                    + " FROM editora "
                    + sqlPesquisa);

            rs = ps.executeQuery();
            
            Editora e;

            while( rs.next()){
                e = new Editora();

                e.idEditora    = rs.getInt("id");
                e.nomeEditora  = rs.getString("nome");
                
                colecao.add(e);
            }
            return colecao;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getId() {
        return idEditora;
    }

    public String getNome() {
        return nomeEditora;
    }

    public void setId(int id) {
        idEditora = id;
    }

    public void setNome(String nome) {
        nomeEditora = nome;
    }
}