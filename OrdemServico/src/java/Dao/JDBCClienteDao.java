/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Cliente;
import Model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class JDBCClienteDao extends JDBCUsuarioDao {

    public JDBCClienteDao() throws SQLException {
        super();
    }

    @Override
    public Usuario buscarUsuario(int id) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "select idUsuario, nome, email, senha, data_cadastro, contato, cnpj, insc_estadual, endereco, complemento, bairro, "
                + "cidade, estado, cep, telefone, fax, observacao "
                + "from usuario, cliente "
                + "where cliente.idCliente = " + id + " and cliente.idCliente = usuario.idUsuario";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        Cliente c = new Cliente();
        preencherUsuario(c, rs);
        conn.commit();
        return c;
    }

    @Override
    public ArrayList<Usuario> buscarTodos() throws SQLException {
        Statement st = conn.createStatement();
        String sql = "select idUsuario, nome, email, senha, data_cadastro, contato, cnpj, insc_estadual, endereco, complemento, bairro, "
                + "cidade, estado, cep, telefone, fax, observacao "
                + "from usuario, cliente "
                + "where cliente.idCliente = usuario.idUsuario";
        ResultSet rs = st.executeQuery(sql);
        System.out.println(sql);
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        while (rs.next()) {
            Cliente c = new Cliente();
            preencherUsuario(c, rs);
            usuarios.add(c);
        }
        conn.commit();
        return usuarios;
    }

    @Override
    public void editarUsuario(Usuario usuario) throws SQLException {
        super.editarUsuario(usuario);
        Statement st = conn.createStatement();
        Cliente c = (Cliente) usuario;
        String sql = "update cliente set contato = '" + c.getContato() + "', "
                + "cnpj = '" + c.getCnpj() + "', "
                + "insc_estadual = '" + c.getInscricaoEstadual() + "', "
                + "endereco = '" + c.getEndereco() + "', "
                + "complemento = '" + c.getComplemento() + "', "
                + "bairro = '" + c.getBairro() + "', "
                + "cidade = '" + c.getCidade() + "', "
                + "estado = '" + c.getEstado() + "', "
                + "cep = '" + c.getCep() + "', "
                + "telefone = '" + c.getTelefone() + "', "
                + "fax = '" + c.getFax() + "', "
                + "observacao = '" + c.getObservacao() + "' "
                + "where idCliente = " + c.getId() + ";";
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public void inserirUsuario(Usuario usuario) throws SQLException {
        super.inserirUsuario(usuario);
        Statement st = conn.createStatement();
        Cliente c = (Cliente) usuario;
        System.out.println("Cidade no Dao: " + c.getCidade());
        String sql = "insert into cliente values(" + proximoId + ", "
                + "'" + c.getContato() + "', "
                + "'" + c.getCnpj() + "', "
                + "'" + c.getInscricaoEstadual() + "', "
                + "'" + c.getEndereco() + "', "
                + "'" + c.getComplemento() + "', "
                + "'" + c.getBairro() + "', "
                + "'" + c.getCidade() + "', "
                + "'" + c.getEstado() + "', "
                + "'" + c.getCep() + "', "
                + "'" + c.getTelefone() + "', "
                + "'" + c.getFax() + "', "
                + "'" + c.getObservacao() + "'); ";
        System.out.println(sql);
        st.executeUpdate(sql);
        conn.commit();
    }

    @Override
    public void preencherUsuario(Usuario u, ResultSet rs) throws SQLException {
        super.preencherUsuario(u, rs);
        Cliente c = (Cliente) u;
        c.setContato(rs.getString("contato"));
        c.setCnpj(rs.getString("cnpj"));
        c.setInscricaoEstadual(rs.getString("insc_estadual"));
        c.setEndereco(rs.getString("endereco"));
        c.setComplemento(rs.getString("complemento"));
        c.setBairro(rs.getString("bairro"));
        c.setCidade(rs.getString("cidade"));
        c.setEstado(rs.getString("estado"));
        c.setCep(rs.getString("cep"));
        c.setTelefone(rs.getString("telefone"));
        c.setFax(rs.getString("fax"));
        c.setObservacao(rs.getString("observacao"));
    }
}
