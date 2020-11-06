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

    public boolean adicionarEditora(String nomeEditora){
        try {
            int id = gerarId();

            ps = con.prepareStatement("INSERT INTO editora(id,nome) VALUES (?, ?)");
            
            ps.setInt(1, id);
            ps.setString(2, nomeEditora);

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
            ps = con.prepareStatement("SELECT MAX(id) as maiorId FROM editora");
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