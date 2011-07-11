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

/**
 *
 * @author pedro
 */
public class JDBCServicoDao implements ServicoDao {
    
    private Connection conn;
    
    public JDBCServicoDao() throws SQLException{
        conn = JDBCConnection.getConnection();
    }

    @Override
    public void insereServico(Servico s) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "insert into servico values(null" + ", "
                + s.getOrdemServico().getIdOrdemServico() + ", "
                + s.getAparelho().getIdAparelho() + ", "
                + s.getTecnico().getId() + ", "
                + "'" + s.getDescricao() + "', "
                + s.getPreco() + ", "
                + "'" + s.getTempoReparo() + "');";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public Servico buscarServico(int idServico) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "select idServico, idOrdemServico, idAparelhoCliente, idCliente,"
                + " descricao, preco, tempo_reparo"
                + " from servico"
                + " where servico.idServico =" + idServico;
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
                + ", tempo_reparo = " + s.getTempoReparo()
                + " where servico.idServico =" + s.getIdServico() + ";";
        st.executeQuery(sql);
        conn.commit();
    }

    @Override
    public void alterarStatus(String status) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void preencherServico(Servico s, ResultSet rs) throws SQLException {
        s.setIdServico(rs.getInt("idServico"));
        s.getOrdemServico().setIdOrdemServico(rs.getInt("idOrdermServico"));
        s.getAparelho().setIdAparelho(rs.getInt("idAparelhoCliente"));
        s.getTecnico().setId(rs.getInt("idTecnico"));
        s.setDescricao(rs.getString("descricao"));
        s.setPreco(rs.getFloat("preco"));
        s.setTempoReparo(rs.getString("tempo_reparto"));
    }
}
