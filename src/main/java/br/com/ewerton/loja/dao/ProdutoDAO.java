package br.com.ewerton.loja.dao;

import br.com.ewerton.loja.model.Produto;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class ProdutoDAO {

    private EntityManager em;

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeCategoria(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoProduto(String nome) {
        String jpql = "SELECT p.preco FROM Produto p where p.nome = :nome";

        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<Produto> buscarProdutoCriteriaAPI(String nome, BigDecimal preco, LocalDate dataCadastro){
        var builder = em.getCriteriaBuilder();
        var query = builder.createQuery(Produto.class);
        var from = query.from(Produto.class);
        var and = builder.and();

        if(nome != null && !nome.trim().isEmpty())
            and = builder.and(and, builder.equal(from.get("nome"), nome));
        if(preco != null)
            and = builder.and(and, builder.equal(from.get("preco"), preco));
        if(dataCadastro != null)
            and = builder.and(and, builder.equal(from.get("dataCadastro"), dataCadastro));

        query.where(and);

        return em.createQuery(query).getResultList();
    }
}
