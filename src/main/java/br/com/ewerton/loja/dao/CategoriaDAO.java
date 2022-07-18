package br.com.ewerton.loja.dao;

import br.com.ewerton.loja.model.Categoria;
import br.com.ewerton.loja.model.Produto;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;

@AllArgsConstructor
public class CategoriaDAO {

    private EntityManager em;

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }

}
