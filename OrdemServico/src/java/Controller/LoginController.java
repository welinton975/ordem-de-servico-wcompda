/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.JDBCClienteDao;
import Dao.UsuarioDao;
import Model.Cliente;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pedro
 */
@ManagedBean (name="LoginController")
@RequestScoped
public class LoginController {

    private Cliente cliente;
    private String email;
    private String senha;
    private UsuarioDao daoCliente;
    private HttpSession session;

    public LoginController() throws SQLException {
        cliente = new Cliente();
        daoCliente = new JDBCClienteDao();
    }

    public String login() throws SQLException {
        String pagina;
        cliente = (Cliente) daoCliente.buscarPorEmail(email);
        if (cliente == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário incorreto", ""));
            pagina = "";
        } else if (senha.equals(cliente.getSenha())) {
            pagina = "cliente?faces-redirect=true";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getCurrentInstance().getExternalContext().getRequest();
            session = request.getSession();
            session.setAttribute("cliente", cliente);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta", ""));
            pagina = "";
        }
        return pagina;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return email;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
