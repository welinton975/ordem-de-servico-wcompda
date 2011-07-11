/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.OrdemServico;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class JDBCOrdemServicoDao implements OrdemServicoDao {

    private Connection conn;
    private int proximoId;

    public JDBCOrdemServicoDao() throws SQLException {
        conn = JDBCConnection.getConnection();
    }

    @Override
    public int inserirOS(OrdemServico os) throws SQLException {
        Statement st = conn.createStatement();
        Statement stAutoIncrement = conn.createStatement();
        ResultSet rsAutoIncrement = stAutoIncrement.executeQuery("show table status like 'ordem_servico'");
        rsAutoIncrement.next();
        proximoId = rsAutoIncrement.getInt("Auto_increment");
        String sql = "insert into ordem_servico values(null" + ", " + os.getCliente().getId() + ", "
                + "'em aprovação'" + ", " + os.getOrcamento() + ");";
        st.executeUpdate(sql);
        conn.commit();
        return proximoId;
    }

    @Override
    public void editarOS(OrdemServico os) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "update ordem_servico set estado = '" + os.getStatus() + "',"
                + " orcamento = " + os.getOrcamento() + ", "
                + " ordem_servico.idOrdemServico = " + os.getIdOrdemServico() + ", "
                + " ordem_servico.idCliente = " + os.getCliente().getId()
                + " where ordem_servico.idOrdemServico = " + os.getIdOrdemServico() + ";";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public void gerarRelatorio() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void alteraStatus(String status) throws SQLException {
    }

    @Override
    public OrdemServico buscaOS(int idOrdemServico) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "select idOrdemServico, idCliente, estado, orcamento "
                + "from ordem_servico "
                + "where ordem_servico.idOrdemServico = " + idOrdemServico + ";";
        ResultSet rs;
        rs = st.executeQuery(sql);
        rs.next();
        OrdemServico os = new OrdemServico();
        preencherOS(os, rs);
        conn.commit();
        return os;

    }

    @Override
    public void preencherOS(OrdemServico os, ResultSet rs) throws SQLException {
        os.setIdOrdemServico(rs.getInt("idOrdemServico"));
        os.getCliente().setId(rs.getInt("idCliente"));
        os.setStatus(rs.getString("estado"));
        os.setOrcamento(rs.getFloat("orcamento"));
    }

    @Override
    public void excluirOS(int idOrdemServico) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "delete from ordem_servico.ordem_servico where idOrdemServico = " + idOrdemServico + "; ";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public ArrayList<OrdemServico> buscarTodos() throws SQLException {
        Statement st = conn.createStatement();
        ArrayList<OrdemServico> os = new ArrayList<OrdemServico>();
        String sql = "select idOrdemServico, idCliente, estado, orcamento "
                + "from ordem_servico;";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            OrdemServico ordem = new OrdemServico();
            preencherOS(ordem, rs);
            os.add(ordem);
        }
        conn.commit();
        return os;
    }
}
