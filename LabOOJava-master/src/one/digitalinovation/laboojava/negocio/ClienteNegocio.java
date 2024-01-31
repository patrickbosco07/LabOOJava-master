package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cliente;
import one.digitalinovation.laboojava.entidade.Produto;

import java.util.List;
import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Cliente}.
 * @author thiago leite
 */
public class ClienteNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter acesso aos clientes cadastrados
     */
    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Consulta o cliente pelo seu CPF.
     * @param cpf CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     */
    public Optional<Cliente> consultar(String cpf) {

        for (Cliente cliente: bancoDados.getCliente()) {
            if (cliente.getCpf().equals(cpf)){
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

        /**
         * Cadastra um novo cliente.
         * @param cliente Novo cliente que terá acesso a aplicação
         */
        //TODO Fazer a inclusão de cliente
    public void salvar(Cliente cliente) {
        this.bancoDados.adicionarCliente(cliente);
        System.out.println("Cadastro feito com sucesso");
    }

        /**
         * Exclui um cliente específico.
         * @param cpf CPF do cliente
         */
        //TODO Fazer a exclusão de cliente
    public void excluir(String cpf) {

        int posicao;
        Cliente cliente;
        for (int i = 0; i < bancoDados.getCliente().length; i++) {
            cliente = bancoDados.getCliente()[i];
            if (cliente.equals(cpf)){
                posicao = i;
                bancoDados.removerCliente(posicao);
                System.out.println("Excluído com sucesso");
            }
        }
    }
    }
