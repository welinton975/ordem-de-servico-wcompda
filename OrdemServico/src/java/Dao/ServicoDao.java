/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Servico;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public interface ServicoDao {
    
    public void insereServico(Servico s) throws SQLException;
    public Servico buscarServico(int idServico) throws SQLException;
    public void editarServico(Servico s) throws SQLException;
    public void preencherServico(Servico s, ResultSet rs) throws SQLException;
    public void alterarStatus(String status) throws SQLException;

}
