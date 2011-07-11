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

    public void inserirAparelho(AparelhoCliente aparelho) throws SQLException;
    public void editarAparelho(AparelhoCliente aparelho) throws SQLException;
    public ArrayList<AparelhoCliente> listarTodos() throws SQLException;

}
