package com.vendas_prototipo.vendas.service;

import com.vendas_prototipo.vendas.dto.CategoriaDto;
import com.vendas_prototipo.vendas.model.Categoria;
import com.vendas_prototipo.vendas.model.Produto;
import com.vendas_prototipo.vendas.dto.ProdutoDto;
import com.vendas_prototipo.vendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository pR;

    public void cadastrarProduto(ProdutoDto produtoDto){
        Produto produto = new Produto(produtoDto);
        this.pR.save(produto);
    }

    public void editarProduto(Integer id, ProdutoDto produtoDto){
        Produto produto = new Produto(produtoDto);
        produto.setId(id);
        this.pR.save(produto);
    }

    public void deletarProduto(Integer id){
        this.pR.deleteById(id);
    }

    public List<ProdutoDto> buscarTodos(){
        List<Produto> produtos = this.pR.findAll();
        List<ProdutoDto> produtosDto = new ArrayList<>();
        for(Produto produto: produtos){
            List<CategoriaDto> catDto = new ArrayList<>();
            for(Categoria cat : produto.getCategorias()){
                catDto.add(new CategoriaDto(cat.getId(), cat.getNome()));
            }
            produtosDto.add(new ProdutoDto(produto.getNome(),
                    produto.getDescricao(), produto.getPreco(),
                    produto.getQuantidade(), catDto ));
        }
        return produtosDto;
    }

    public Optional<ProdutoDto> buscarPorId(Integer id){
        Optional<Produto> produto = this.pR.findById(id);
        if(produto.isPresent()){
            List<CategoriaDto> catDto = new ArrayList<>();
            for(Categoria cat : produto.get().getCategorias()){
                catDto.add(new CategoriaDto(cat.getId(), cat.getNome()));
            }
            return Optional.of(new ProdutoDto(produto.get().getNome(),
                    produto.get().getDescricao(), produto.get().getPreco(),
                    produto.get().getQuantidade(), catDto ));
        }
        Optional<ProdutoDto> optional = Optional.ofNullable(null);
        return optional;
    }
}
