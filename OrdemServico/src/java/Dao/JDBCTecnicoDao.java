/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Tecnico;
import Model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class JDBCTecnicoDao extends JDBCUsuarioDao {

    public JDBCTecnicoDao() throws SQLException {
        super();
    }

    @Override
    public Usuario buscarUsuario(int id) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "select cpf, rg "
                + "from usuario, tecnico "
                + "where tecnico.idTecnico = " + id + " and tecnico.idTecnico = usuario.idUsuario";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        Tecnico t = new Tecnico();
        preencherUsuario(t, rs);
        conn.commit();
        return t;
    }

    @Override
    public ArrayList<Usuario> buscarTodos() throws SQLException {
        Statement st = conn.createStatement();
        String sql = "select cpf, rg "
                + "from usuario, tecnico "
                + "where tecnico.idTecnico = usuario.idUsuario";
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        while (rs.next()) {
            Tecnico t = new Tecnico();
            preencherUsuario(t, rs);
            usuarios.add(t);
        }
        conn.commit();
        return usuarios;
    }

    @Override
    public void editarUsuario(Usuario usuario) throws SQLException {
        super.editarUsuario(usuario);
        Statement st = conn.createStatement();
        Tecnico t = (Tecnico) usuario;
        String sql = "update tecnico set "                
                + "cpf ='" + t.getCpf()
                + "rg ='" + t.getRg() + ";";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public void inserirUsuario(Usuario usuario) throws SQLException {
        super.inserirUsuario(usuario);
        Statement st = conn.createStatement();
        Tecnico t = (Tecnico) usuario;
        String sql = "insert into tecnico values(" + proximoId + ", "
                + "'" + t.getCpf() + "'); "
                + "'" + t.getRg() + "'); ";
        System.out.println(sql);
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public void preencherUsuario(Usuario u, ResultSet rs) throws SQLException {
        super.preencherUsuario(u, rs);
        Tecnico t = (Tecnico) u;
        t.setCpf("cpf");
        t.setRg("rg");
    }

}
