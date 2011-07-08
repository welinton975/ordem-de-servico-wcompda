/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Dao.UsuarioDao;
import Model.Usuario;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author pedro
 */
public abstract class UsuarioController {

    private Usuario usuario;
    private UsuarioDao dao;

    public UsuarioController() throws SQLException{
        reset();
    }

    public void salvar() throws SQLException{
        dao.inserirUsuario(usuario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Cliente inserido com sucesso",""));
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

}
