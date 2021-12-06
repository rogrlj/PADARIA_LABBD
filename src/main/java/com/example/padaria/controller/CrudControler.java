package com.example.padaria.controller;

import com.example.padaria.model.Produto;
import com.example.padaria.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoRepository.listarProdutos();
        model.addAttribute("produtos", produtos);

        return "crud";
    }

    @GetMapping("/teste")
    public ResponseEntity<Object> teste(){
        Object object = "teste";
        return ResponseEntity.ok(object);
    }

    @GetMapping("/{id}")
    public String buscarProduto(@PathVariable("id") Long id, Model model){
        Produto produto = produtoRepository.buscarProduto(id);
        model.addAttribute("produto", produto);
        return "crud";
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable("id") Long id){
        produtoRepository.remover(id);
        return "crud";
    }

    @PutMapping("/alterar/{id}")
    public String alterarProduto(@PathVariable("id") Long id, Produto produto){
        produtoRepository.atualizar(produto, id);
        return "crud";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Produto produto){

        produtoRepository.adicionar(produto);

        return "crud";
    }
}
