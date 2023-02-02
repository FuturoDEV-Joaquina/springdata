package br.com.futurodev.springdata.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String name;

    @Column(name = "nascimento")
    private Date birthday;

    @Column(length = 100)
    private String email;

    @Column(name = "telefone", length = 30)
    private String phone;

    @Column(length = 30)
    private String whatsapp;

    @Column(name = "endereco",length = 500)
    private String address;

}
