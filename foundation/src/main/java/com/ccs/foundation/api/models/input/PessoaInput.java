package com.ccs.foundation.api.models.input;

import com.ccs.foundation.entities.Pessoa;

public record PessoaInput(String nome, String email) {
    public Pessoa toPessoa() {
        return new Pessoa(nome, email);
    }
}
