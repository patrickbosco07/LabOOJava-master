package one.digitalinovation.laboojava.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um cliente.
 * @author thiago leite
 */
public class Pedido {

    //TODO Preencher esta classe

    private String codigo;

    private Cliente cliente;

    private List<Produto> itens;

    private Double preco;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public String codigo() {
        return codigo;
    }

    public Pedido setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public Cliente cliente() {
        return cliente;
    }

    public Pedido setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public List<Produto> itens() {
        return itens;
    }

    public Pedido setItens(List<Produto> itens) {
        this.itens = itens;
        return this;
    }

    public Double preco() {
        return preco;
    }

    public Pedido setPreco(Double preco) {
        this.preco = preco;
        return this;
    }

    private StringBuilder getProdutosComprados() {

        StringBuilder produtos = new StringBuilder();

        produtos.append("[");
        for (Produto produto: itens()) {
            produtos.append(produto.toString());
            produtos.append("Qtd:");
            produtos.append(produto.getQuantidade());
            produtos.append(" ");

        }
        produtos.append("]");
        return produtos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "código:" + codigo + "\'" +
                ", cliente:" + cliente +
                ", itens:" + getProdutosComprados() +
                ", total:" + preco +
                ';';
    }
}
