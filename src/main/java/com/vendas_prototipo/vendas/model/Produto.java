package com.vendas_prototipo.vendas.model;

import com.vendas_prototipo.vendas.dto.CategoriaDto;
import com.vendas_prototipo.vendas.dto.ProdutoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private float preco;

    @Column(nullable = false)
    private int quantidade;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
                joinColumns = @JoinColumn(name = "id_produto"),
                inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categorias = new ArrayList<>();

    public Produto(ProdutoDto produtodto) {
        this.nome = produtodto.nome();
        this.descricao = produtodto.descricao();
        this.preco = produtodto.preco();
        this.quantidade = produtodto.quantidade();
        for(CategoriaDto cDto: produtodto.categoriasDto()){
            this.categorias.add(new Categoria(cDto));
        }
    }
}
