package br.com.futurodev.springdata.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @Column(length = 20, nullable = false)
    private String tipo;

    @Column(length = 50, nullable = false)
    private String numero;

    @Column(name = "orgao_emissor", length = 20, nullable = false)
    private String orgaoEmissor;

}
