package model;

import java.sql.*;
import java.util.*;

public class Usuario {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    private int idUsuario;
    private String nomeUsuario;
    private String loginUsuario;
    private String senhaUsuario;

    public void setConexao(Connection con) {
        this.con = con;
    }

    public int autenticar(String loginUsuario, String senhaUsuario)  {
        int totalUsers;

        try {
            ps = con.prepareStatement("SELECT "
                        + " idUsuario, nome, login, COUNT(*) as totalUsers "
                    + " FROM "
                        + " SisWebLivraria.Usuario "
                    + " WHERE "
                        + " login = ? "
                        + " AND senha = ? ");

            ps.setString(1, loginUsuario);
            ps.setString(2, senhaUsuario);
            
            rs = ps.executeQuery();
            
            rs.next();

            totalUsers = rs.getInt("totalUsers");
            
            if (totalUsers == 1) {
                this.idUsuario = rs.getInt("idUsuario");
                this.nomeUsuario = rs.getString("nome");
                this.loginUsuario = rs.getString("login");
            }

            // 0 - usuário não encontrado
            // 1 - usuário encontrado e autenticado
            return totalUsers;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList listarUsuarios(String buscarUsuarioNome)  {
        ArrayList colecao = new ArrayList();
        
        String sqlPesquisa = " ";
        if(buscarUsuarioNome != null)
            sqlPesquisa = " where nome like '%" + buscarUsuarioNome + "%' ";

        try {
            ps = con.prepareStatement("SELECT  idUsuario,"
                        + " nome, "
                        + " login, "
                        + " senha "
                    + " FROM Usuario "
                    + sqlPesquisa);

            rs = ps.executeQuery();
            
            Usuario u;

            while( rs.next()){
                u = new Usuario();

                u.idUsuario    = rs.getInt("idUsuario");
                u.nomeUsuario  = rs.getString("nome");
                u.loginUsuario = rs.getString("login");
                u.senhaUsuario = rs.getString("senha");

                colecao.add(u);
            }
            return colecao;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getId() {
        return idUsuario;
    }

    public String getNome() {
        return nomeUsuario;
    }

    public String getLogin() {
        return loginUsuario;
    }

    public String getSenha() {
        return senhaUsuario;
    }
}