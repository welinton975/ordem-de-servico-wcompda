/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.JDBCPecaDao;
import Model.Peca;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author arthur
 */

@ManagedBean(name="PecaController")
@ViewScoped
public class PecaController{
    
    private Peca peca;
    private JDBCPecaDao dao;
    
    
    public PecaController() throws SQLException{
        System.out.println("entrou no construtor!");
        peca = new Peca();
        dao = new JDBCPecaDao();
    }
    
    public void salvar() throws SQLException{
        System.out.println("entrou no SALVARR!");
        dao.inserirPeca(peca);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Peça inserida com sucesso!",""));
    
    }
    
    public void editar() throws SQLException{
        dao.editarPeca(peca);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Peça editada com sucesso!",""));
    }
    
    public void excluir() throws SQLException{
        dao.excluirPeca(peca.getId());
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Peça excluída com sucesso!", ""));
    }
    
    public void buscar() throws SQLException{
        dao.buscarPeca(peca.getId());
    }

    /**
     * @return the peca
     */
    public Peca getPeca() {
        return peca;
    }

    /**
     * @param peca the peca to set
     */
    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    /**
     * @return the dao
     */
    public JDBCPecaDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(JDBCPecaDao dao) {
        this.dao = dao;
    }
    
}
