package br.com.futurodev.springdata.services;

import br.com.futurodev.springdata.models.Documento;
import br.com.futurodev.springdata.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public Documento salvar(Documento documento) throws Exception {

        if (documento.getPessoa() == null) {
            throw new Exception("Pessoa é obrigatória!");
        }
        if (documento.getTipo() == null || documento.getTipo().isEmpty()) {
            throw new Exception("Tipo é obrigatório!");
        }
        if (documento.getNumero() == null || documento.getNumero().isEmpty()) {
            throw new Exception("Número é obrigatório!");
        }
        if (documento.getOrgaoEmissor() == null || documento.getOrgaoEmissor().isEmpty()) {
            throw new Exception("Órgão Emissor é obrigatório!");
        }

        if (documento.getId() != null) { // Editando
            Documento documentoEdit = buscarPorId(documento.getId());
            if (documentoEdit == null) {
                throw new Exception("Documento não encontrado! (ID " + documento.getId() + ")");
            }
        }

        return documentoRepository.save(documento);
    }

    public List<Documento> buscarTodas() {
        return documentoRepository.findAll();
    }

    public Documento buscarPorId(Long id) {
        return documentoRepository.findById(id).get();
    }

    public boolean apagar(Long id) {
        try {
            documentoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
          return false;
        }
    }

}
