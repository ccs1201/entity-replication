package com.ccs.foundation.entities;

import com.ccs.foundation.listeners.PessoaListener;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(PessoaListener.class)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;
    private String nome;
    private String email;
    private boolean ativo = true;
    @CreationTimestamp
    private OffsetDateTime dataCriacao;
    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
}
