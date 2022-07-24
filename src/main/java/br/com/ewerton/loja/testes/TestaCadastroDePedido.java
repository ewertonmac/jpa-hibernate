package br.com.ewerton.loja.testes;

import br.com.ewerton.loja.dao.CategoriaDAO;
import br.com.ewerton.loja.dao.ClienteDao;
import br.com.ewerton.loja.dao.PedidoDAO;
import br.com.ewerton.loja.dao.ProdutoDAO;
import br.com.ewerton.loja.model.*;
import br.com.ewerton.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TestaCadastroDePedido {

    public static void main(String[] args) {
        var entityManager = JPAUtil.criarEntityManager();
        entityManager.getTransaction().begin();
        popularBancoDeDados(entityManager);
        entityManager.flush();

        var produtoDAO = new ProdutoDAO(entityManager);
        var clienteDao = new ClienteDao(entityManager);
        var pedidoDAO = new PedidoDAO(entityManager);

        var produto = produtoDAO.buscarPorId(1L);
        var cliente = clienteDao.buscarPorId(1L);
        var pedido = Pedido.builder()
                .cliente(cliente)
                .data(LocalDate.now())
                .build();

        var itemPedido = ItemPedido.builder()
                .produto(produto)
                .precoUnitario(produto.getPreco())
                .quantidade(2)
                .build();

        pedido.adicionarItem(itemPedido);

        pedidoDAO.cadastrar(pedido);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void popularBancoDeDados(EntityManager entityManager) {
        var cliente = Cliente.builder().nome("Ewerton").cpf("123456").build();
        var celulares = Categoria.builder().nome("CELULARES").build();
        var celular = Produto.builder().nome("Xiaomi Redmi").descricao("Smartphone Android Xiaomi")
                .preco(BigDecimal.valueOf(1500)).dataCadastro(LocalDate.now()).categoria(celulares)
                .build();


        var categoriaDAO = new CategoriaDAO(entityManager);
        var produtoDAO = new ProdutoDAO(entityManager);
        var clienteDao = new ClienteDao(entityManager);

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        clienteDao.cadastrar(cliente);
    }
}
