/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.JDBCClienteDao;
import Model.Cliente;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author pedro
 */
@ManagedBean(name = "ClienteController")
@ViewScoped
public class ClienteController extends UsuarioController {

    private Date data;
    private boolean editando = true;

    //private TreeMap<String, String> clienteTree;
    public ClienteController() throws SQLException {
        super();
        buscarClientes();
    }

    @Override
    public void reset() throws SQLException {
        setUsuario(new Cliente());
        setDao(new JDBCClienteDao());
    }

    @Override
    public void salvar() throws SQLException {
        System.out.println("Entrei no salvar");
        String data;
        Date agora = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd/mm/yyyy");
        data = form.format(agora);
        getCliente().setDataCadastro(agora);
        System.out.println("Cidade: " + getCliente().getCidade());
        super.salvar();
        setUsuario(new Cliente());
        //editando = true;
        buscarUsuarios();
    }

    public String redirecionar() {
        StringBuilder sb = new StringBuilder();
        sb.append("index");
        sb.append("?faces-redirect=true");
        return sb.toString();
    }

    @Override
    public void editar() throws SQLException {
        super.editar();
        setUsuario(new Cliente());
    }

    public Cliente getCliente() {
        return (Cliente) getUsuario();
    }

    public void setCliente(Cliente cliente) throws SQLException {
        setUsuario(cliente);
    }

    public void resetCliente(){
        //editando = false;
        //System.out.println("Resetando cliente...");
        setUsuario(new Cliente());
        //System.out.println("id = " + getUsuario().getId());
    }

    public void editando(){
        System.out.println("Resetando...");
        editando = true;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the editando
     */
    public boolean isEditando() {
        return editando;
    }

    /**
     * @param editando the editando to set
     */
    public void setEditando(boolean editando) {
        this.editando = editando;
    }
}
