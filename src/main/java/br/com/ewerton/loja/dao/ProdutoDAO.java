package br.com.ewerton.loja.dao;

import br.com.ewerton.loja.model.Produto;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;

@AllArgsConstructor
public class ProdutoDAO {

    private EntityManager em;

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }

}
