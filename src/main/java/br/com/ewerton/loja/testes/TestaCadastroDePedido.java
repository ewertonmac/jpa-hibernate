package br.com.ewerton.loja.testes;

import br.com.ewerton.loja.dao.CategoriaDAO;
import br.com.ewerton.loja.dao.ClienteDao;
import br.com.ewerton.loja.dao.PedidoDAO;
import br.com.ewerton.loja.dao.ProdutoDAO;
import br.com.ewerton.loja.model.*;
import br.com.ewerton.loja.util.JPAUtil;
import br.com.ewerton.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestaCadastroDePedido {

    public static void main(String[] args) {
        var entityManager = JPAUtil.criarEntityManager();
        entityManager.getTransaction().begin();
        popularBancoDeDados(entityManager);
        entityManager.flush();

        var produtoDAO = new ProdutoDAO(entityManager);
        var clienteDao = new ClienteDao(entityManager);
        var pedidoDAO = new PedidoDAO(entityManager);

        var produto1 = produtoDAO.buscarPorId(1L);
        var produto2 = produtoDAO.buscarPorId(2L);
        var produto3 = produtoDAO.buscarPorId(3L);

        var cliente1 = clienteDao.buscarPorId(1L);
        var cliente2 = clienteDao.buscarPorId(1L);
        var cliente3 = clienteDao.buscarPorId(1L);

        var pedido1 = Pedido.builder()
                .cliente(cliente1)
                .data(LocalDate.now())
                .build();

        var pedido2 = Pedido.builder()
                .cliente(cliente2)
                .data(LocalDate.now())
                .build();

        var pedido3 = Pedido.builder()
                .cliente(cliente3)
                .data(LocalDate.now())
                .build();

        pedido1.adicionarItem(new ItemPedido(1, produto1));
        pedido1.adicionarItem(new ItemPedido(2, produto2));
        pedido1.adicionarItem(new ItemPedido(1, produto3));
        pedido2.adicionarItem(new ItemPedido(3, produto1));
        pedido2.adicionarItem(new ItemPedido(1, produto2));
        pedido2.adicionarItem(new ItemPedido(1, produto3));
        pedido3.adicionarItem(new ItemPedido(4, produto1));
        pedido3.adicionarItem(new ItemPedido(8, produto2));
        pedido3.adicionarItem(new ItemPedido(5, produto3));

        pedidoDAO.cadastrar(pedido1);
        pedidoDAO.cadastrar(pedido2);
        pedidoDAO.cadastrar(pedido3);
        entityManager.getTransaction().commit();
        imprimeRelatorio(pedidoDAO);

        var pedido = pedidoDAO.buscarPedidoComCliente(pedido1.getId());

        entityManager.close();

        System.out.println(pedido.getCliente().getNome() + " - " + pedido.getCliente().getCpf());

    }

    private static void popularBancoDeDados(EntityManager entityManager) {
        var celulares = Categoria.builder().nome("CELULARES").build();
        var videogames = Categoria.builder().nome("VIDEOGAMES").build();
        var informatica = Categoria.builder().nome("INFORMATICA").build();

        var celular = Produto.builder().nome("Xiaomi Redmi").descricao("Smartphone Android Xiaomi")
                .preco(BigDecimal.valueOf(1500)).dataCadastro(LocalDate.now()).categoria(celulares)
                .build();
        var xbox = Produto.builder().nome("Xbox").descricao("Xbox Series X")
                .preco(BigDecimal.valueOf(4500)).dataCadastro(LocalDate.now()).categoria(videogames)
                .build();
        var mackbook = Produto.builder().nome("Mackbook air").descricao("Mackbook air M1")
                .preco(BigDecimal.valueOf(13000)).dataCadastro(LocalDate.now()).categoria(informatica)
                .build();

        var cliente = Cliente.builder().dadosPessoais(DadosPessoais.builder().nome("Ewerton").cpf("123456").build()).build();
        var cliente2 = Cliente.builder().dadosPessoais(DadosPessoais.builder().nome("João").cpf("123456").build()).build();
        var cliente3 = Cliente.builder().dadosPessoais(DadosPessoais.builder().nome("Pedro").cpf("123456").build()).build();

        var categoriaDAO = new CategoriaDAO(entityManager);
        var produtoDAO = new ProdutoDAO(entityManager);
        var clienteDao = new ClienteDao(entityManager);

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(videogames);
        categoriaDAO.cadastrar(informatica);
        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(xbox);
        produtoDAO.cadastrar(mackbook);
        clienteDao.cadastrar(cliente);
        clienteDao.cadastrar(cliente2);
        clienteDao.cadastrar(cliente3);

        produtoDAO.buscarProdutoCriteriaAPI("Mackbook air", BigDecimal.ONE, LocalDate.now());
    }

    private static void imprimeRelatorio(PedidoDAO dao){
        List<RelatorioVendasVo> vendas = dao.gerarRelatorioVendas();

        System.out.println("\n".repeat(200));
        vendas.forEach(obj -> {
            var dataFormatada = obj.getDataUltimaVenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.println(String.format("%22s | %8s | %16s", obj.getNome(), obj.getQuantidadeVendida(), dataFormatada));

        });
    }
}
