/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Fornecedor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author arthur
 */
public class JDBCFornecedorDao {
    
    protected Connection conn;
    
    public void inserirFornecedor(Fornecedor fornecedor) throws SQLException{
        Statement st = conn.createStatement();
        Fornecedor f = fornecedor;
        String sql = "insert into Fornecedor values ('" + f.getEmail() + "', '"
                + f.getNome() + "' , '"
                + f.getLocalizacao() + "', '"
                + f.getTelefone() + ";";
        st.executeUpdate(sql);
        conn.commit();                
    }
    
    public void buscarFornecedor(int id) throws SQLException{
        Statement st = conn.createStatement();
        String sql = "select contato, nome, localizacao, telefone"
                + "from Fornecedor"
                + "where Fornecedor.idFornecedor = " + id + ";";
        st.executeUpdate(sql);
        conn.commit();        
    }
}
