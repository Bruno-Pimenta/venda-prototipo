package com.vendas_prototipo.vendas.Controller;

import com.vendas_prototipo.vendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import com.vendas_prototipo.vendas.dto.ProdutoDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService pS;

    @PostMapping("/salvar")
    public void cadastrarProduto(@RequestBody ProdutoDto produtoDto){
        this.pS.cadastrarProduto(produtoDto);
    }

    @PutMapping("/editar/{id}")
    public void editarProduto(@PathVariable Integer id, @RequestBody ProdutoDto produtoDto){
        this.pS.editarProduto(id, produtoDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deletarProduto(@PathVariable Integer id){
        this.pS.deletarProduto(id);
    }

    @GetMapping("/buscar")
    public List<ProdutoDto> buscarTodos(){
        return this.pS.buscarTodos();
    }

    @GetMapping("/buscar/{id}")
    public ProdutoDto buscarPorId(@PathVariable Integer id){
        return this.pS.buscarPorId(id).get();
    }
}
