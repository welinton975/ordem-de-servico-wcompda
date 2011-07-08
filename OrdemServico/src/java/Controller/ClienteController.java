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
import javax.faces.bean.RequestScoped;

/**
 *
 * @author pedro
 */

@ManagedBean(name="ClienteController")
@RequestScoped
public class ClienteController extends UsuarioController {

    private Date data;

    public ClienteController() throws SQLException{
        super();
    }

    @Override
    public void reset() throws SQLException {
        setUsuario(new Cliente());
        setDao(new JDBCClienteDao());
    }

    @Override
    public void salvar() throws SQLException{
        String data;
        Date agora = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd/mm/yyyy");
        data = form.format(agora);
        getCliente().setDataCadastro(agora);
        System.out.println("Cidade: " + getCliente().getCidade());
        super.salvar();
    }
    
    public Cliente getCliente(){
        return (Cliente) getUsuario();
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

}
