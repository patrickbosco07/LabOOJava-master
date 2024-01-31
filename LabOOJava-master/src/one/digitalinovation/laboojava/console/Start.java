package one.digitalinovation.laboojava.console;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.entidade.constantes.Materias;
import one.digitalinovation.laboojava.negocio.ClienteNegocio;
import one.digitalinovation.laboojava.negocio.PedidoNegocio;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * @author thiago leite
 */
public class Start {

    private static Cliente clienteLogado = null;

    private static Banco banco = new Banco();

    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

    /**
     * Método utilitário para inicializar a aplicação.
     * @param args Parâmetros que podem ser passados para auxiliar na execução.
     */
    public static void main(String[] args) {

        System.out.println("Bem vindo ao e-Compras");

        String opcao = "";

        while(true) {

            if (clienteLogado == null) {

                System.out.println("Digite o cpf:");

                String cpf ="";
                cpf = LeitoraDados.lerDado();

                identificarUsuario(cpf);

            }

            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Excluir Livro");
            //TODO Desafio: Consultar Livro(nome)
            System.out.println("3 - Cadastrar Caderno");
            System.out.println("4 - Excluir Caderno");
            //TODO Desafio: Consultar Caderno(matéria)
            System.out.println("5 - Fazer pedido");
            System.out.println("6 - Excluir pedido");
            //TODO Desafio: Consultar Pedido(código)
            System.out.println("7 - Listar produtos");
            System.out.println("8 - Listar pedidos");
            System.out.println("9 - Consultar Livro");
            System.out.println("10 - Consultar Caderno");
            System.out.println("11 - Consultar Pedido");
            System.out.println("12 - Excluir cadastro");
            System.out.println("13 - Deslogar");
            System.out.println("14 - Sair");

            opcao = LeitoraDados.lerDado();

            switch (opcao) {
                case "1":
                    Livro livro = LeitoraDados.lerLivro();
                    produtoNegocio.salvar(livro);
                    break;
                case "2":
                    System.out.println("Digite o código do livro");
                    String codigoLivro = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoLivro);
                    break;
                case "3":
                    //TODO Cadastrar Caderno
                    Caderno caderno = LeitoraDados.lerCaderno();
                    produtoNegocio.salvar(caderno);
                    break;
                case "4":
                    //TODO Excluir Caderno
                    System.out.println("Digite o código do caderno)");
                    String codigo = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigo);
                    break;
                case "5":
                    Pedido pedido = LeitoraDados.lerPedido(banco);
                    Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

                    if (cupom.isPresent()) {
                        pedidoNegocio.salvar(pedido, cupom.get());
                    } else {
                        pedidoNegocio.salvar(pedido);
                    }
                    break;
                case "6":
                    System.out.println("Digite o código do pedido");
                    String codigoPedido = LeitoraDados.lerDado();
                    pedidoNegocio.excluir(codigoPedido);
                    break;
                case "7":
                    produtoNegocio.listarTodos();
                    break;
                case "8":
                    //TODO Listar todos os Pedidos
                    pedidoNegocio.listarPedidos();
                    break;
                case "9":
                    System.out.println("Informe o nome do livro");
                    String nomeLivro = LeitoraDados.lerDado();
                    System.out.println(produtoNegocio.consultarLivro(nomeLivro));
                    break;
                case "10":
                    System.out.println("Informe a quantidade de matéria do cadeno");
                    String materiaCaderno = LeitoraDados.lerDado();
                    System.out.println(produtoNegocio.consultarCaderno(Materias.valueOf(materiaCaderno)));
                    break;
                case "11":
                    System.out.println("Informe o código do pedido");
                    String codigoProdutoo = LeitoraDados.lerDado();
                    System.out.println(pedidoNegocio.consultar(codigoProdutoo));
                    break;
                case "12":
                    System.out.println("Informe o cpf");
                    String meuCpf = LeitoraDados.lerDado();
                    clienteNegocio.excluir(meuCpf);
                case "13":
                    System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                    clienteLogado = null;
                    break;
                case "14":
                    System.out.println("Aplicação encerrada.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static Cliente getClienteLogado() {
        return clienteLogado;
    }

    public static void setClienteLogado(Cliente clienteLogado) {
        Start.clienteLogado = clienteLogado;
    }

    /**
     * Procura o usuário na base de dados.
     * @param cpf CPF do usuário que deseja logar na aplicação
     */
    private static void identificarUsuario(String cpf) {

        Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();
            System.out.println(String.format("Olá %s! Você está logado.", cliente.getNome()));
            clienteLogado = cliente;
        } else {
            System.out.println("Usuário não cadastrado.");
            Cliente cliente = LeitoraDados.lerCliente();
            clienteNegocio.salvar(cliente);
            clienteLogado = cliente;
        }
    }
}
