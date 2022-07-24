package br.com.ewerton.loja.dao;

import br.com.ewerton.loja.model.Pedido;

import javax.persistence.EntityManager;

public class PedidoDAO {

    private final EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        this.em.persist(pedido);
    }
}
