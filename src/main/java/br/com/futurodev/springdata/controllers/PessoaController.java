package br.com.futurodev.springdata.controllers;

import br.com.futurodev.springdata.models.Pessoa;
import br.com.futurodev.springdata.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> get(String name) {
        if (name != null && !name.isEmpty()) {
            return pessoaService.buscarPorNome(name);
        }
        return pessoaService.buscarTodas();
    }

    @GetMapping(value = "/{id}")
    public Pessoa getById(@PathVariable Long id) {
        return pessoaService.buscarPorId(id);
    }

    @PostMapping
    public Pessoa post(@RequestBody Pessoa pessoa) {
        try {
            pessoa.setId(null);
            return pessoaService.salvar(pessoa);
        } catch (Exception e) {
        }
        return null;
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody Pessoa pessoa) {
        try {
            if (pessoa.getId() == null) {
                throw new Exception("ID é obrigatório!");
            }
            return ResponseEntity.ok(pessoaService.salvar(pessoa));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping
    public boolean delete(Long id) {
        return pessoaService.apagar(id);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deletePath(@PathVariable Long id) {
        return pessoaService.apagar(id);
    }

}
