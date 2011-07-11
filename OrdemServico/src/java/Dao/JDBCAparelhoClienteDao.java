/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Model.AparelhoCliente;
import java.sql.Connection;
import java.sql.ResultSet;
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
    public int inserirAparelho(AparelhoCliente aparelho) throws SQLException {
        Statement st = conn.createStatement();
        int proximoId;
        Statement stAutoIncrement = conn.createStatement();
        ResultSet rsAutoIncrement = stAutoIncrement.executeQuery("show table status like 'aparelho_cliente'");
        rsAutoIncrement.next();
        proximoId = rsAutoIncrement.getInt("Auto_increment");
        String sql = "insert into aparelho_cliente values (null, "
                + "idCliente = " + aparelho.getCliente().getId() + ", "
                + "modelo = '" + aparelho.getModelo() + "', "
                + "codigo = '" + aparelho.getCodigo() + "'); ";
        st.executeUpdate(sql);
        conn.commit();
        return proximoId;
    }

    @Override
    public void editarAparelho(AparelhoCliente aparelho) throws SQLException {
        Statement st = conn.createStatement();
        
        String sql = "update aparelho_cliente set "
                + "idCliente = " + aparelho.getCliente().getId() + ", "
                + "modelo = '" + aparelho.getModelo() + "', "
                + "codigo = '" + aparelho.getCodigo() + "' where idAparelhoCliente = " + aparelho.getIdAparelho() + ";";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public ArrayList<AparelhoCliente> listarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void excluirAparelho(int idAparelho) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "delete from servico where idAparelhoCliente = " + idAparelho + "; ";
        st.executeUpdate(sql);
        conn.commit();
    }

}
