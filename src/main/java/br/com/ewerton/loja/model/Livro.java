package br.com.ewerton.loja.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro extends Produto{

    private String autor;
    private Integer quantidadePaginas;
}
