package br.com.ewerton.loja.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
    private Integer quantidade;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Pedido pedido;

}
