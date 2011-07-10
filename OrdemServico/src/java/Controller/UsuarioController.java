/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.UsuarioDao;
import Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author pedro
 */
public abstract class UsuarioController {

    private Usuario usuario;
    private UsuarioDao dao;
    private String idUsuario = "0";
    private TreeMap<String, String> clienteTree;
    private List<Usuario> usuarios;

    public UsuarioController() throws SQLException {
        usuarios = new ArrayList<Usuario>();
        clienteTree = new TreeMap<String, String>();
        reset();
        buscarUsuarios();
        System.out.println("Passei aqui!");
    }

    public void buscarUsuarios() throws SQLException {
        setUsuarios(dao.buscarTodos());
    }

    public void salvar() throws SQLException {
        System.out.println("Nome do usuário salvar: " + usuario.getNome());
        dao.inserirUsuario(usuario);
        //setClienteTree(new TreeMap<String, String>());
        //buscarClientes();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente inserido com sucesso", ""));
        //setUsuario(null);
        //idUsuario = "0";
    }

    public void editar() throws SQLException {
        System.out.println("Novo nome: " + usuario.getNome());
        dao.editarUsuario(usuario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente editado com sucesso", ""));
        setClienteTree(new TreeMap<String, String>());
        buscarUsuarios();
        idUsuario = "0";
    }

    public void buscarClientes() throws SQLException {
        ArrayList<Usuario> clientes = new ArrayList<Usuario>();
        clientes = getDao().buscarTodos();
        //System.out.println("Size: " + clientes.size());
        for (int i = 0; i < clientes.size(); i++) {
            //System.out.println("Nome: " + clientes.get(i).getNome());
            String id = String.valueOf(clientes.get(i).getId());
            clienteTree.put((clientes.get(i)).getNome(), id);
        }
    }

    public void carregarUsuario() throws SQLException {
        if (idUsuario.equals("0")) {
            reset();
        } else {
            usuario = dao.buscarUsuario(Integer.parseInt(idUsuario));
        }
    }

    public abstract void reset() throws SQLException;

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the dao
     */
    public UsuarioDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(UsuarioDao dao) {
        this.dao = dao;
    }

    /**
     * @return the clienteTree
     */
    public TreeMap<String, String> getClienteTree() {
        return clienteTree;
    }

    /**
     * @param clienteTree the clienteTree to set
     */
    public void setClienteTree(TreeMap<String, String> clienteTree) {
        this.clienteTree = clienteTree;
    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() throws SQLException {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
