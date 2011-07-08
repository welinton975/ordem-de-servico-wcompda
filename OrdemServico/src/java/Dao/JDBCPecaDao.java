/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Peca;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author arthur
 */
public class JDBCPecaDao {
    
    protected Connection conn;
    protected int proximoId;
    
    public void inserirPeca(Peca peca) throws SQLException{
        Statement st = conn.createStatement();
        Peca p = peca;
        String sql = ("insert into peca values ('" + p.getNome() + "'," +
                p.getQuantidade() + "," + p.getPreco() + ");");
        System.out.println(sql);
        st.executeUpdate(sql);
        conn.commit();
    }
    
    public void buscarPeca(int id) throws SQLException{
        Statement st = conn.createStatement();
        String sql = "select nome, quantidade, preco "
                + "from peca "
                + "where peca.idPeca = " + id + ";";
        st.executeUpdate(sql);
        conn.commit();
    }
    
    public void excluirPeca(int id) throws SQLException{
        Statement st = conn.createStatement();
        String sql = "delete from peca "
                + "where peca.idPeca = " + id + ";";
        st.executeUpdate(sql);
        conn.commit();
    }
    
    public void editarPeca(Peca peca) throws SQLException{
        Statement st = conn.createStatement();
        Peca p = peca;
        String sql = "update peca set nome = '" + p.getNome() + "', "
                + "quantidade = " + p.getQuantidade()
                + ", preco = " + p.getPreco() + ";";
        st.executeUpdate(sql);
        conn.commit();
    }
}
