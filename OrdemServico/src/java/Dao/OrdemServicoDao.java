/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.OrdemServico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public interface OrdemServicoDao {

    public int inserirOS(OrdemServico os) throws SQLException;
    public void editarOS(OrdemServico os) throws SQLException;
    public void gerarRelatorio() throws SQLException;
    public void alteraStatus(String status) throws SQLException;
    public OrdemServico buscaOS(int idOrdemServico) throws SQLException;
    public void preencherOS(OrdemServico os, ResultSet rs) throws SQLException;
    public void excluirOS(int idOrdemServico) throws SQLException;
    public ArrayList<OrdemServico> buscarTodos() throws SQLException;
    
}
