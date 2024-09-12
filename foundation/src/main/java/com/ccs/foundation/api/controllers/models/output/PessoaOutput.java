package com.ccs.foundation.api.controllers.models.output;

import com.ccs.foundation.entities.Pessoa;

import java.time.OffsetDateTime;
import java.util.UUID;

public record PessoaOutput(UUID id,
                           String nome,
                           String email,
                           OffsetDateTime dataCriacao,
                           OffsetDateTime dataAtualizacao) {

    public static PessoaOutput toOutput(Pessoa pessoa) {
        return new PessoaOutput(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(),
                pessoa.getDataCriacao(), pessoa.getDataAtualizacao());
    }
}
