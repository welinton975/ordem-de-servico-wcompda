/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author pedro
 */
public class Servico {

    private int idServico;
    private OrdemServico ordemServico;
    private AparelhoCliente aparelho;
    private Tecnico tecnico;
    private String descricao;
    private float preco;
    private String tempoReparo;
    private String status;

    public Servico() {
        ordemServico = new OrdemServico();
        tecnico = new Tecnico();
        aparelho = new AparelhoCliente();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getTempoReparo() {
        return tempoReparo;
    }

    public void setTempoReparo(String tempo_reparto) {
        this.tempoReparo = tempo_reparto;
    }

    /**
     * @return the aparelho
     */
    public AparelhoCliente getAparelho() {
        return aparelho;
    }

    /**
     * @param aparelho the aparelho to set
     */
    public void setAparelho(AparelhoCliente aparelho) {
        this.aparelho = aparelho;
    }

    /**
     * @return the Tecnico
     */
    public Tecnico getTecnico() {
        return tecnico;
    }

    /**
     * @param Tecnico the Tecnico to set
     */
    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
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

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
