package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cliente;
import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.console.Start;


import javax.management.MBeanNotificationInfo;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Pedido}.
 * @author thiago leite
 */
public class PedidoNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter armazenar e ter acesso os pedidos
     */
    public PedidoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    private double calcularTotal(List<Produto> produtos, Cupom cupom) {

        double total = 0.0;
        for (Produto produto: produtos) {
            total += produto.calcularFrete();
        }

        if (cupom != null) {
            return  total * (1 - cupom.getDesconto());
        } else {
            return  total;
        }

    }

    /**
     * Salva um novo pedido sem cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     */
    public void salvar(Pedido novoPedido) {
        salvar(novoPedido, null);
    }

    /**
     * Salva um novo pedido com cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     * @param cupom Cupom de desconto a ser utilizado
     */
    public void salvar(Pedido novoPedido, Cupom cupom) {

        //Definir padrão código
        //Pegar data do dia corrente
        //Formatar código
        String codigo = "PE%02d%4d%04d";
        LocalDate hoje = LocalDate.now();
        codigo = String.format(codigo, hoje.getMonthValue(), hoje.getYear(), bancoDados.getPedidos().length);
        //Setar código no pedido
        novoPedido.setCodigo(codigo);
        //Setar cliente no pedido
        novoPedido.setCliente(Start.getClienteLogado());
        //Calcular e set total
        novoPedido.setPreco(calcularTotal(novoPedido.itens(), cupom));
        //Adicionar no banco
        bancoDados.adicionarPedido(novoPedido);
        //Mensagem
        System.out.print("Pedido salvo");;
    }

    /**
     * Exclui um pedido a partir de seu código de rastreio.
     * @param codigo Código do pedido
     */
    public void excluir(String codigo) {

        int pedidoExclusao = -1;
        for (int i = 0; i < bancoDados.getPedidos().length; i++) {

            Pedido pedido = bancoDados.getPedidos()[i];
            if (pedido.codigo().equals(codigo)) {
                pedidoExclusao = i;
                break;
            }
        }

        if (pedidoExclusao != -1) {
            bancoDados.removerPedido(pedidoExclusao);
            System.out.println("Pedido excluído com sucesso.");
        } else {
            System.out.println("Pedido inexistente.");
        }
    }

    /**
     * Lista todos os pedidos realizados.
     */
    //TODO Método de listar todos os pedidos
    public void listarPedidos() {

        if (bancoDados.getPedidos().length == 0) {
            System.out.println("Não existem pedidos cadastrados");
        } else {
            for (Pedido pedido : bancoDados.getPedidos()
            ) {
                System.out.println(pedido.toString());
            }
        }


    }
    /**
    Método para consultar um pedido através de seu código
     */
    public Optional<Pedido> consultar (String codigo) {

        for (Pedido pedido: bancoDados.getPedidos()) {
            if (pedido.codigo().equalsIgnoreCase(codigo)){
                return Optional.of(pedido);
            }
        }
        return Optional.empty();
    }
}
