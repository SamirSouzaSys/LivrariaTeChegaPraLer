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

    public boolean adicionarUsuario(String nomeUsuario, String loginUsuario, String senhaUsuario){
        try {
            int id = gerarId();

            ps = con.prepareStatement("INSERT INTO Usuario(idUsuario,nome,login,senha) VALUES (?, ?, ?, ?)");
            
            ps.setInt(1, id);
            ps.setString(2, nomeUsuario);
            ps.setString(3, loginUsuario);
            ps.setString(4, senhaUsuario);

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
            ps = con.prepareStatement("SELECT MAX(id) as maiorId FROM usuario");
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