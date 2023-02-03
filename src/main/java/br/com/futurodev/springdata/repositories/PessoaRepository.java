package br.com.futurodev.springdata.repositories;

import br.com.futurodev.springdata.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


    // DerivedQuery
    public List<Pessoa> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

    // JPQL
    @Query("SELECT p FROM Pessoa p WHERE UPPER(p.name) LIKE UPPER(:name) ")
    public List<Pessoa> findByName(String name);

    // NativeQuery
    @Query(value = "SELECT p.* FROM pessoa p WHERE p.nome ILIKE :name", nativeQuery = true)
    public List<Pessoa> findByNameNative(String name);

}
