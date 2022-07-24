package br.com.ewerton.loja.dao;

import br.com.ewerton.loja.model.Cliente;

import javax.persistence.EntityManager;

public class ClienteDao {

    private final EntityManager em;
    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return this.em.find(Cliente.class, id);
    }
}
