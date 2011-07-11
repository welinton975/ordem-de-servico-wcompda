/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author pedro
 */
public class AparelhoCliente {

    private int idAparelho;
    private String codigo;
    private String modelo;
    private Cliente cliente;

    public AparelhoCliente(){
        cliente = new Cliente();
    }

    /**
     * @return the idAparelho
     */
    public int getIdAparelho() {
        return idAparelho;
    }

    /**
     * @param idAparelho the idAparelho to set
     */
    public void setIdAparelho(int idAparelho) {
        this.idAparelho = idAparelho;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
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

}
