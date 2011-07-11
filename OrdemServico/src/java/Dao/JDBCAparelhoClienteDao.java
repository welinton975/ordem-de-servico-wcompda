/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Model.AparelhoCliente;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class JDBCAparelhoClienteDao implements AparelhoClienteDao {
    
    private Connection conn;

    public JDBCAparelhoClienteDao() throws SQLException{
        conn = JDBCConnection.getConnection();
    }

    @Override
    public void inserirAparelho(AparelhoCliente aparelho) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "insert into aparelho_cliente values (null, "
                + "idCliente = " + aparelho.getCliente().getId() + ", "
                + "modelo = '" + aparelho.getModelo() + "', "
                + "codigo = '" + aparelho.getCodigo() + "'); ";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public void editarAparelho(AparelhoCliente aparelho) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "update aparelho_cliente set "
                + "idCliente = " + aparelho.getCliente().getId() + ", "
                + "modelo = '" + aparelho.getModelo() + "', "
                + "codigo = '" + aparelho.getCodigo() + "'; where idAparelhoCliente = " + aparelho.getIdAparelho() + ";";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public ArrayList<AparelhoCliente> listarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
