/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AparelhoClienteDao;
import Dao.JDBCAparelhoClienteDao;
import Dao.JDBCClienteDao;
import Dao.JDBCOrdemServicoDao;
import Dao.JDBCServicoDao;
import Dao.OrdemServicoDao;
import Dao.ServicoDao;
import Dao.UsuarioDao;
import Model.Cliente;
import Model.OrdemServico;
import Model.Servico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private UsuarioDao daoCliente;
    private boolean concluido = false;
    private boolean editando = false;
    private int passos = 0;
    private int idOS;
    private List<Servico> servicos;
    private ArrayList<OrdemServico> ordens;

    public OrdemServicoController() throws SQLException {
        ordemServico = new OrdemServico();
        servicoCorrente = new Servico();
        daoOs = new JDBCOrdemServicoDao();
        daoServico = new JDBCServicoDao();
        daoCliente = new JDBCClienteDao();
        daoAparelho = new JDBCAparelhoClienteDao();
        servicos = new ArrayList<Servico>();
        ordens = new ArrayList<OrdemServico>();
        buscarTodasOS();
    }

    public void salvar() throws SQLException {
        System.out.println("Salvando...");
        servicoCorrente.getAparelho().getCliente().setId(1);
        servicoCorrente.getTecnico().setId(0);
        getOrdemServico().getServicos().add(servicoCorrente);
        getOrdemServico().getCliente().setId(1);
        if (passos == 0) {
            idOS = daoOs.inserirOS(getOrdemServico());
        }
        getOrdemServico().setIdOrdemServico(idOS);
        servicoCorrente.setOrdemServico(getOrdemServico());
        System.out.println("Id cliente aparelho: " + servicoCorrente.getAparelho().getCliente().getId());
        servicoCorrente.getAparelho().setIdAparelho(daoAparelho.inserirAparelho(servicoCorrente.getAparelho()));
        servicoCorrente.setIdServico(daoServico.insereServico(servicoCorrente));
        servicos.add(servicoCorrente);
        passos++;
        System.out.println("Salvou...");
    }

    public void editarServico() throws SQLException {
        daoServico.editarServico(servicoCorrente);
        daoAparelho.editarAparelho(servicoCorrente.getAparelho());
        servicoCorrente = new Servico();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Servico editado com sucesso", ""));
        editando = false;
    }

    public void excluirServico() throws SQLException {
        daoServico.excluirServico(servicoCorrente.getIdServico());
        daoAparelho.excluirAparelho(servicoCorrente.getAparelho().getIdAparelho());
        servicos.remove(servicoCorrente);
        if(servicos.isEmpty()){
            daoOs.excluirOS(getOrdemServico().getIdOrdemServico());
        }
        servicoCorrente = new Servico();
        editando = false;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Serviço excluído com sucesso", ""));
    }

    public void resetEditando() {
        editando = true;
    }

    public void verifica() {
        System.out.println("Objeto servicoCorrente descrição: " + servicoCorrente.getDescricao());
    }

    public void salvarProximoPasso() throws SQLException {
        salvar();
        //ordemServico = new OrdemServico();
        servicoCorrente = new Servico();
    }

    public void salvarConclusao() throws SQLException {
        if (servicoCorrente.getIdServico() > 0) {
            salvar();
        }
        if (servicos.size() > 0) {
            //salvar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitação de ordem de serviço efetuada com sucesso", ""));
            //ordemServico = new OrdemServico();
            concluido = true;
        }
        if(servicos.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Pelo menos um serviço deve ser solicitado para que a ordem de serviço seja concluída", ""));
            //ordemServico = new OrdemServico();
            concluido = false;
        }
        servicoCorrente = new Servico();

    }

    public void buscarTodasOS() throws SQLException{
        ordens = daoOs.buscarTodos();
        for(int i = 0; i < ordens.size(); i++){
            ordens.get(i).setServicos(daoServico.buscarPorOs(ordens.get(i).getIdOrdemServico()));
            ordens.get(i).setCliente((Cliente)(daoCliente.buscarUsuario(ordens.get(i).getCliente().getId())));
        }
    }

    public void editar() throws SQLException {
        daoOs.editarOS(getOrdemServico());
    }

    public void editarOsCompleta() throws SQLException {
        editar();
        for(int i = 0; i < ordemServico.getServicos().size(); i++){
            ordemServico.getServicos().get(i).getOrdemServico().setIdOrdemServico(ordemServico.getIdOrdemServico());
            daoServico.editarServico(ordemServico.getServicos().get(i));
        }
         FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Ordem de Serviço alterada com sucesso", ""));
        editando = false;
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

    /**
     * @return the servicos
     */
    public List<Servico> getServicos() {
        return servicos;
    }

    /**
     * @param servicos the servicos to set
     */
    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
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

    /**
     * @return the ordens
     */
    public ArrayList<OrdemServico> getOrdens() {
        return ordens;
    }

    /**
     * @param ordens the ordens to set
     */
    public void setOrdens(ArrayList<OrdemServico> ordens) {
        this.ordens = ordens;
    }

    /**
     * @return the ordemServico
     */
    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    /**
     * @param ordemServico the ordemServico to set
     */
    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }
}
