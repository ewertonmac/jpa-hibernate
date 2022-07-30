package br.com.ewerton.loja.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    public void adicionarItem(ItemPedido item) {
        if (this.itens == null) this.itens = new ArrayList<>();
        if (valorTotal == null) this.valorTotal = BigDecimal.ZERO;

        item.setPedido(this);
        this.itens.add(item);
        var valorPedido = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
        this.valorTotal = this.valorTotal.add(valorPedido);
    }
}
