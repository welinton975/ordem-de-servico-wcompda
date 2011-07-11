/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Model.AparelhoCliente;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public interface AparelhoClienteDao {

    public int inserirAparelho(AparelhoCliente aparelho) throws SQLException;
    public void editarAparelho(AparelhoCliente aparelho) throws SQLException;
    public void excluirAparelho(int idAparelho) throws SQLException;
    public ArrayList<AparelhoCliente> listarTodos() throws SQLException;

}
