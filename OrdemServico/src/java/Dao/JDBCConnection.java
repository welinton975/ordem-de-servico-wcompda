/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class JDBCConnection {

    public static Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/ordem_servico";
        String username = "root";
        String password = "root";
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch(ClassNotFoundException e){System.out.println("ClassNotFoundException na classe de conexao com o banco de dados");}
        catch(InstantiationException e){System.out.println("InstantiationException na classe de conexao com o banco de dados");}
        catch(IllegalAccessException e){System.out.println("IllegalAccessException na classe de conexao com o banco de dados");}
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
        return connection;
    }

}
