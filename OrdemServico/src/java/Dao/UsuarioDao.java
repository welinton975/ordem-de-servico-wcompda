/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public interface UsuarioDao {

    public void inserirUsuario(Usuario usuario) throws SQLException;
    public void editarUsuario(Usuario usuario) throws SQLException;
    public Usuario buscarUsuario(int id) throws SQLException;
    public Usuario buscarPorEmail(String email) throws SQLException;
    public ArrayList<Usuario> buscarTodos() throws SQLException;

}
