package br.com.ewerton.loja.dao;

import br.com.ewerton.loja.model.Pedido;
import br.com.ewerton.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {

    private final EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public BigDecimal valorTotalVendas() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";

        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<RelatorioVendasVo> gerarRelatorioVendas() {
        String jpql = "SELECT new br.com.ewerton.loja.vo.RelatorioVendasVo( " +
                "produto.nome, " +
                "SUM(item.quantidade) as quantidade, " +
                "MAX(pedido.data)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto  " +
                "GROUP BY nome " +
                "ORDER BY quantidade desc";

        return em.createQuery(jpql, RelatorioVendasVo.class)
                .getResultList();
    }

    public Pedido buscarPedidoComCliente(Long id) {
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
