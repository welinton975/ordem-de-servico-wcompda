/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author arthur
 */
public class Peca {
    
    private int id;
    private String nome;
    private float preco;
    private int quantidade;

    /**
     * @return the nome
     */
    public String getNome() {
        System.out.println("entrou no nome porra!!");
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the preco
     */
    public float getPreco() {
        System.out.println("entrou no preço porra!!");
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        System.out.println("entrou na qtd porra!!");
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the id
     */
    public int getId() {
        System.out.println("entrou no id porra!!");
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    
    
    
    
}
