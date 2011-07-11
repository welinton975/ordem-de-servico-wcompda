/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class OrdemServico {

    private int idOrdemServico;
    private String status;
    private float orcamento;
    private Cliente cliente;
    private ArrayList<Servico> servicos;
    
    public OrdemServico(){
    	cliente = new Cliente();
        servicos = new ArrayList<Servico>();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(int idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public float getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
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
     * @return the servicos
     */
    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    /**
     * @param servicos the servicos to set
     */
    public void setServicos(ArrayList<Servico> servicos) {
        this.servicos = servicos;
    }
}
