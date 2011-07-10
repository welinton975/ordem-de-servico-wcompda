/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Fornecedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author arthur
 */
public class JDBCFornecedorDao {
    
    protected Connection conn;
    
    public JDBCFornecedorDao() throws SQLException {
        conn = JDBCConnection.getConnection();
    }
    
    public void inserirFornecedor(Fornecedor fornecedor) throws SQLException{
        Statement st = conn.createStatement();
        Fornecedor f = fornecedor;
        String sql = "insert into Fornecedor values (null, '" + f.getEmail() + "', '"
                + f.getNome() + "' , '"
                + f.getLocalizacao() + "');";
        System.out.println(sql);
        st.executeUpdate(sql);
        conn.commit();                
    }
    
    public void buscarFornecedor(int id) throws SQLException{
        Statement st = conn.createStatement();
        String sql = "select contato, nome, localizacao"
                + "from Fornecedor"
                + "where Fornecedor.idFornecedor = " + id + ";";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        Fornecedor f = new Fornecedor();
        preencherFornecedor(f, rs);
        conn.commit();
    }
    
    public void preencherFornecedor(Fornecedor fornecedor, ResultSet rs) throws SQLException{
        fornecedor.setId(rs.getInt("id"));
        fornecedor.setNome(rs.getString("nome"));
        fornecedor.setTelefone(rs.getString("telefone"));
        fornecedor.setEmail(rs.getString("email"));
        fornecedor.setLocalizacao(rs.getString("localizacao"));
    } 
}
