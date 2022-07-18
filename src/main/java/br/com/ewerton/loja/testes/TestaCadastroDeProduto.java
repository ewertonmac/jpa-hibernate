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

        EntityManager em = JPAUtil.criarEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        em.getTransaction().begin();
        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}