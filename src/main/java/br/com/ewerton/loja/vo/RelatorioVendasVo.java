package br.com.ewerton.loja.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class RelatorioVendasVo {
    private String nome;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;
}
