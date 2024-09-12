package com.ccs.foundation.api.models.output;

import com.ccs.foundation.entities.Pessoa;

import java.time.OffsetDateTime;
import java.util.UUID;

public record PessoaOutput(UUID id,
                           String nome,
                           String email,
                           boolean ativo,
                           OffsetDateTime dataCriacao,
                           OffsetDateTime dataAtualizacao) {

    public static PessoaOutput toOutput(Pessoa pessoa) {
        return new PessoaOutput(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(),
                pessoa.isAtivo(), pessoa.getDataCriacao(), pessoa.getDataAtualizacao());
    }
}
