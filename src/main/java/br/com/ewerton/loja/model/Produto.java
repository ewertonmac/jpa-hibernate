package br.com.ewerton.loja.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    @Column(name="data_cadastro")
    private LocalDate dataCadastro;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
