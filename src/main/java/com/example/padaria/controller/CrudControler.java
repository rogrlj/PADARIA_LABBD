package com.example.padaria.controller;

import com.example.padaria.model.Produto;
import com.example.padaria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

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
    public String home(Produto produto) {
        System.out.println(produto);
        return "crud";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Produto produto){

        produtoRepository.adicionar(produto);

        System.out.println(produto.getNome());
        System.out.println(produto);
        return "crud";
    }
}
