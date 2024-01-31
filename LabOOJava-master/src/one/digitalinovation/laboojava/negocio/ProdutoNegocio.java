package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Caderno;
import one.digitalinovation.laboojava.entidade.Livro;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.entidade.constantes.Materias;

import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Produto}.
 * @author thiago leite
 */
public class ProdutoNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter armazenar e ter acesso os produtos
     */
    public ProdutoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Salva um novo produto(livro ou caderno) na loja.
     * @param novoProduto Livro ou caderno que pode ser vendido
     */
    public void salvar(Produto novoProduto) {

        String codigo = "PR%04d";

        codigo = String.format(codigo, bancoDados.getProdutos().length);
        novoProduto.setCodigo(codigo);

        boolean produtoRepetido = false;
        for (Produto produto: bancoDados.getProdutos()) {
            if (produto.getCodigo().equals(novoProduto)) {
                produtoRepetido = true;
                System.out.println("Produto já cadastrado.");
                break;
            }
        }

        if (!produtoRepetido) {
            this.bancoDados.adicionarProduto(novoProduto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    /**
     * Exclui um produto pelo código de cadastro.
     * @param codigo Código de cadastro do produto
     */
    public void excluir(String codigo) {
        //TODO Implementar a exclusão
        int codigoExclusao = -1;

        for (int i = 0; i < bancoDados.getProdutos().length; i++) {
            Produto produto = bancoDados.getProdutos()[i];
            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                codigoExclusao = i;
                break;
            }else System.out.println("Código inválido, forneça um código correto");
        }

            if (codigoExclusao != -1){
                bancoDados.removerProduto(codigoExclusao);
                System.out.println("Produto excluído");
            }


    }

    /**
     * Obtem um livro a partir de seu nome.
     * @param nome do livro
     * @return Optional indicando a existência ou não do Produto
     */
    public Optional<Livro> consultarLivro(String nome) {

        for (Produto produto: bancoDados.getProdutos()) {
            if (produto instanceof Livro) {
                Livro livro = (Livro) produto;
                if (livro.getNome().equalsIgnoreCase(nome)) {
                    return Optional.of(livro);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtem um caderno a partir de sua matéria
     * @param materia do caderno
     * @return Optional indicando a existência ou não do Produto
     */
    public Optional<Caderno> consultarCaderno(Materias materia) {

        for (Produto produto: bancoDados.getProdutos()) {
            if (produto instanceof Caderno) {
                Caderno caderno = (Caderno) produto;
                if (caderno.materia().equals(materia)) {
                    return Optional.of(caderno);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Obtem um produto a partir do seu código
     * @param codigo do produto
     * @return Optional indicando se o produto existe ou não
     */
    public Optional<Produto> consultarProduto(String codigo) {

        for (Produto produto: bancoDados.getProdutos()) {
            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return Optional.of(produto);
                }
            }
        return Optional.empty();
        }


    /**
     * Lista todos os produtos cadastrados.
     */
    public void listarTodos() {

        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {

            for (Produto produto: bancoDados.getProdutos()) {
                System.out.println(produto.toString());
            }
        }
    }
}
