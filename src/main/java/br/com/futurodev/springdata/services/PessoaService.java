package br.com.futurodev.springdata.services;

import br.com.futurodev.springdata.models.Pessoa;
import br.com.futurodev.springdata.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa) throws Exception {
        if (pessoa.getName() == null || pessoa.getName().isEmpty()) {
            throw new Exception("Nome é obrigatório");
        }

        if (pessoa.getId() != null) { // Editando
            Pessoa pessoaEdit = buscarPorId(pessoa.getId());
            if (pessoaEdit == null) {
                throw new Exception("Pessoa não encontrada! (ID " + pessoa.getId() + ")");
            }
        }

        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> buscarTodas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).get();
    }

    public boolean apagar(Long id) {
        try {
            pessoaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
          return false;
        }
    }

    public List<Pessoa> buscarPorNome(String name) {
        return pessoaRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

}
