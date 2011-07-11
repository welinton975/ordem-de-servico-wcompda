/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AparelhoClienteDao;
import Dao.JDBCAparelhoClienteDao;
import Dao.JDBCOrdemServicoDao;
import Dao.JDBCServicoDao;
import Dao.OrdemServicoDao;
import Dao.ServicoDao;
import Model.OrdemServico;
import Model.Servico;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pedro
 */
@ManagedBean(name = "OrdemServicoController")
@ViewScoped
public class OrdemServicoController {

    private OrdemServico ordemServico;
    private Servico servicoCorrente;
    private OrdemServicoDao daoOs;
    private ServicoDao daoServico;
    private AparelhoClienteDao daoAparelho;
    private boolean concluido = false;
    private int passos = 0;
    private int idOS;

    public OrdemServicoController() throws SQLException {
        System.out.println("No construtor! ");
        ordemServico = new OrdemServico();
        servicoCorrente = new Servico();
        daoOs = new JDBCOrdemServicoDao();
        daoServico = new JDBCServicoDao();
        daoAparelho = new JDBCAparelhoClienteDao();
    }

    public void salvar() throws SQLException {
        System.out.println("Salvando...");
        servicoCorrente.getAparelho().getCliente().setId(1);
        servicoCorrente.getTecnico().setId(0);
        ordemServico.getServicos().add(servicoCorrente);
        if (passos == 0) {
            idOS = daoOs.inserirOS(ordemServico);
        }
        ordemServico.setIdOrdemServico(idOS);
        servicoCorrente.setOrdemServico(ordemServico);
        daoServico.insereServico(servicoCorrente);
        daoAparelho.inserirAparelho(servicoCorrente.getAparelho());
        passos++;
        System.out.println("Salvou...");
    }

    public void salvarProximoPasso() throws SQLException {
        salvar();
        //ordemServico = new OrdemServico();
        servicoCorrente = new Servico();
    }

    public void salvarConclusao() throws SQLException {
        salvar();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitação de Ordem de Serviço efetuada com sucesso", ""));
        ordemServico = new OrdemServico();
        servicoCorrente = new Servico();
        concluido = true;
    }

    public void editar() throws SQLException {
        daoOs.editarOS(ordemServico);
    }

    /**
     * @return the servicoCorrente
     */
    public Servico getServicoCorrente() {
        return servicoCorrente;
    }

    /**
     * @param servicoCorrente the servicoCorrente to set
     */
    public void setServicoCorrente(Servico servicoCorrente) {
        this.servicoCorrente = servicoCorrente;
    }

    /**
     * @return the concluido
     */
    public boolean isConcluido() {
        return concluido;
    }

    /**
     * @param concluido the concluido to set
     */
    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
}
