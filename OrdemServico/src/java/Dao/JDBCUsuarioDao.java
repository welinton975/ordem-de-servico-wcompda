/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author pedro
 */
public abstract class JDBCUsuarioDao implements UsuarioDao {

    protected Connection conn;
    protected int proximoId;

    public JDBCUsuarioDao() throws SQLException{
        conn = JDBCConnection.getConnection();
    }

    @Override
    public void inserirUsuario(Usuario usuario) throws SQLException{
        Statement st = conn.createStatement();
        Statement stAutoIncrement = conn.createStatement();
        ResultSet rsAutoIncrement = stAutoIncrement.executeQuery("show table status like 'usuario'");
        rsAutoIncrement.next();
        proximoId = rsAutoIncrement.getInt("Auto_increment");
        String data;
        SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd");
        data = form.format(usuario.getDataCadastro());
        String sql = "insert into usuario values(null, '" + usuario.getNome() + "', '"
                + usuario.getEmail() + "', '" + usuario.getSenha() + "', '"
                + data + "');";
        st.executeUpdate(sql);
    }

    @Override
    public void editarUsuario(Usuario usuario) throws SQLException{
        Statement st = conn.createStatement();
        String sql = "update usuario set nome = '" + usuario.getNome() + "', "
                + "email = '" + usuario.getEmail() + "', senha = '" + usuario.getSenha() + "', "
                +"data_cadastro = '" + usuario.getDataCadastro() + "' where idUsuario = " + usuario.getId();
        st.executeUpdate(sql);
    }

    public void preencherUsuario(Usuario usuario, ResultSet rs) throws SQLException{
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setDataCadastro(rs.getDate("data_cadastro"));
    }

}
