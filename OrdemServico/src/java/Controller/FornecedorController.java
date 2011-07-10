/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.JDBCFornecedorDao;
import Model.Fornecedor;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author arthur
 */
@ManagedBean(name="FornecedorController")
@ViewScoped
public class FornecedorController {
    private Fornecedor fornecedor;
    private JDBCFornecedorDao dao;
    
    public FornecedorController() throws SQLException{
        fornecedor = new Fornecedor();
        dao = new JDBCFornecedorDao();
    }
    
    public void salvar() throws SQLException{
        System.out.println("Entrou no salvar!!");
        dao.inserirFornecedor(fornecedor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Fornecedor inserido com sucesso!",""));
    }

    /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the dao
     */
    public JDBCFornecedorDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(JDBCFornecedorDao dao) {
        this.dao = dao;
    }
    
    
}
