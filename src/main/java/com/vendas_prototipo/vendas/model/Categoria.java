package com.vendas_prototipo.vendas.model;
import com.vendas_prototipo.vendas.dto.CategoriaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();

    /*public Categoria(String nome) {
        this.nome = nome;
    }*/

    public Categoria(CategoriaDto categoriaDto) {
        this.id = categoriaDto.id();
        this.nome = categoriaDto.nome();
    }
}
