package com.example.padaria.controller;

import com.example.padaria.model.Produto;
import com.example.padaria.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/crud")
public class CrudControler {


    private ProdutoRepository produtoRepository;
    {
        try{
            produtoRepository = new ProdutoRepository();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoRepository.listarProdutos();

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable("id") Long id){
        Produto produto = produtoRepository.buscarProduto(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable("id") Long id){
        produtoRepository.remover(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable("id") Long id, Produto produto){
        produtoRepository.atualizar(produto, id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/produto")
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto){

        produtoRepository.adicionar(produto);

        return ResponseEntity.ok(produto);
    }
}
