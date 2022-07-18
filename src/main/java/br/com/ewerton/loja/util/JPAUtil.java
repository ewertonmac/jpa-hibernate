package br.com.ewerton.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja");

    private JPAUtil(){}

    public static EntityManager criarEntityManager(){
        return FACTORY.createEntityManager();
    }
}
