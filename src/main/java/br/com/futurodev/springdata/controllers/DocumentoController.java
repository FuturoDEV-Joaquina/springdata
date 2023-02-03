package br.com.futurodev.springdata.controllers;

import br.com.futurodev.springdata.models.Documento;
import br.com.futurodev.springdata.services.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping
    public List<Documento> get() {
        return documentoService.buscarTodas();
    }

    @GetMapping(value = "/{id}")
    public Documento getById(@PathVariable Long id) {
        return documentoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Documento documento) {
        try {
            documento.setId(null);
            return ResponseEntity.ok(documentoService.salvar(documento));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody Documento documento) {
        try {
            if (documento.getId() == null) {
                throw new Exception("ID é obrigatório!");
            }
            return ResponseEntity.ok(documentoService.salvar(documento));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping
    public boolean delete(Long id) {
        return documentoService.apagar(id);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deletePath(@PathVariable Long id) {
        return documentoService.apagar(id);
    }

}
