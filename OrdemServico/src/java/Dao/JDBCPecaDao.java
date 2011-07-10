/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Peca;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author arthur
 */
public class JDBCPecaDao {
    
    protected Connection conn;
    
    public JDBCPecaDao() throws SQLException{
        conn = JDBCConnection.getConnection();
        
    }
    
    public Peca buscarPeca(int id) throws SQLException{
        Statement st = conn.createStatement();
        String sql ="select idPeca, nome, quantidade, preco"
                + "from peca where idPeca = " + id + ";";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        Peca p = new Peca();
        preencherPeca(p, rs);
        conn.commit();
        return p;
    }
        //pedrao vai tira um bug
    public void inserirPeca(Peca peca) throws SQLException{
        
        System.out.println("Entreou no inserir");
        Statement st = conn.createStatement();
        String sql = ("insert into peca values (null, '" + peca.getNome() + "'," +
        peca.getQuantidade() + "," + peca.getPreco() + ");");
        System.out.println(sql);
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
    
    public void preencherPeca(Peca peca, ResultSet rs) throws SQLException{
        peca.setId(rs.getInt("id"));
        peca.setNome(rs.getString("nome"));
        peca.setPreco(rs.getFloat("preco"));
        peca.setQuantidade(rs.getInt("quantidade"));
        
    }
}
