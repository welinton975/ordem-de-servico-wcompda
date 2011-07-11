/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Servico;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class JDBCServicoDao implements ServicoDao {

    private Connection conn;

    public JDBCServicoDao() throws SQLException {
        conn = JDBCConnection.getConnection();
    }

    @Override
    public int insereServico(Servico s) throws SQLException {
        Statement st = conn.createStatement();
        int proximoId;
        Statement stAutoIncrement = conn.createStatement();
        ResultSet rsAutoIncrement = stAutoIncrement.executeQuery("show table status like 'servico'");
        rsAutoIncrement.next();
        proximoId = rsAutoIncrement.getInt("Auto_increment");
        String sql = "insert into servico values(null" + ", "
                + s.getOrdemServico().getIdOrdemServico() + ", "
                + s.getAparelho().getIdAparelho() + ", "
                + s.getTecnico().getId() + ", "
                + "'" + s.getDescricao() + "', "
                + s.getPreco() + ", "
                + "'" + s.getTempoReparo() + "',"
                + "'" + s.getStatus() + "');";
        st.executeUpdate(sql);
        conn.commit();
        return proximoId;
    }

    @Override
    public Servico buscarServico(int idServico) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "select idServico, idOrdemServico, idAparelhoCliente, idTecnico,"
                + " descricao, preco, tempo_reparo, status"
                + " from servico"
                + " where servico.idServico = " + idServico + ";";
        ResultSet rs;
        rs = st.executeQuery(sql);
        rs.next();
        Servico s = new Servico();
        preencherServico(s, rs);
        conn.commit();
        return s;
    }

    @Override
    public void editarServico(Servico s) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "update servico set descricao = " + "'" + s.getDescricao()
                + "', preco = " + s.getPreco()
                + ", tempo_reparo = '" + s.getTempoReparo()
                + "', status = '" + s.getStatus() + "' where servico.idServico = " + s.getIdServico() + ";";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public void alterarStatus(String status) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void preencherServico(Servico s, ResultSet rs) throws SQLException {
        s.setIdServico(rs.getInt("idServico"));
        s.getOrdemServico().setIdOrdemServico(rs.getInt("idOrdemServico"));
        s.getAparelho().setIdAparelho(rs.getInt("idAparelhoCliente"));
        s.getTecnico().setId(rs.getInt("idTecnico"));
        s.setDescricao(rs.getString("descricao"));
        s.setPreco(rs.getFloat("preco"));
        s.setTempoReparo(rs.getString("tempo_reparo"));
        s.setStatus(rs.getString("status"));
    }

    @Override
    public void excluirServico(int idServico) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "delete from servico where idServico = " + idServico + "; ";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public ArrayList<Servico> buscarPorOs(int idOrdemServico) throws SQLException {
        Statement st = conn.createStatement();
        ArrayList<Servico> servicos = new ArrayList<Servico>();
        String sql = "select idServico, idOrdemServico, idAparelhoCliente, idTecnico,"
                + " descricao, preco, tempo_reparo, status"
                + " from servico"
                + " where servico.idOrdemServico = " + idOrdemServico + ";";
        ResultSet rs;
        rs = st.executeQuery(sql);
        while(rs.next()){
            Servico s = new Servico();
            preencherServico(s, rs);
            servicos.add(s);
        }
        conn.commit();
        return servicos;
    }
}
