package com.vendas_prototipo.vendas.dto;

import com.vendas_prototipo.vendas.model.Categoria;

import java.util.List;

public record ProdutoDto(String nome, String descricao, float preco, int quantidade, List<CategoriaDto> categoriasDto) {
}
