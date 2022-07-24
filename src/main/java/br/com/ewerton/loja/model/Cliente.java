package br.com.ewerton.loja.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
}
