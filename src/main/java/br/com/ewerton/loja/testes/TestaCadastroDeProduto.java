package br.com.ewerton.loja.testes;

import br.com.ewerton.loja.dao.CategoriaDAO;
import br.com.ewerton.loja.dao.ProdutoDAO;
import br.com.ewerton.loja.model.Categoria;
import br.com.ewerton.loja.model.Produto;
import br.com.ewerton.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TestaCadastroDeProduto {

    public static void main(String[] args) {
        var entityManager = JPAUtil.criarEntityManager();
        entityManager.getTransaction().begin();
        cadastrarProduto(entityManager);
        entityManager.flush();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        var produto = produtoDAO.buscarPorId(1L);
        var todosProdutos = produtoDAO.buscarTodos();
        var produtosPorNome = produtoDAO.buscarPorNome("Xiaomi Redmi");
        var produtos = produtoDAO.buscarPorNomeCategoria("CELULARES");
        var precoProduto = produtoDAO.buscarPrecoProduto("Xiaomi Redmi");

    }

    private static void cadastrarProduto(EntityManager entityManager) {
        Categoria celulares = Categoria.builder()
                .nome("CELULARES")
                .build();

        Produto celular = Produto.builder()
                .nome("Xiaomi Redmi")
                .descricao("Smartphone Android Xiaomi")
                .preco(BigDecimal.valueOf(1500))
                .dataCadastro(LocalDate.now())
                .categoria(celulares)
                .build();

        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
    }
}
