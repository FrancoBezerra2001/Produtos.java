package com.example.minhaapi.controller;
import com.example.minhaapi.model.Produto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private static List<Produto> produtos = new ArrayList<>();
    private static Integer contadorId = 1;

    @GetMapping
    public List<Produto> listarTodos() {
        return produtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto) {
        produto.setId(contadorId++);
        produtos.add(produto);
        return ResponseEntity.status(201).body(produto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Integer id,
                                             @RequestBody Produto produtoAtualizado) {

        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(p -> {
                    if (produtoAtualizado.getNome() != null) {
                        p.setNome(produtoAtualizado.getNome());
                    }
                    if (produtoAtualizado.getPreco() != null) {
                        p.setPreco(produtoAtualizado.getPreco());
                    }
                    return ResponseEntity.ok(p);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean removido = produtos.removeIf(p -> p.getId().equals(id));

        if (removido) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}