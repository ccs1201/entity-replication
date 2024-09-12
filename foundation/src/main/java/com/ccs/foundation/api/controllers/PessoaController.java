package com.ccs.foundation.api.controllers;

import com.ccs.foundation.api.controllers.models.input.PessoaInput;
import com.ccs.foundation.api.controllers.models.output.PessoaOutput;
import com.ccs.foundation.entities.Pessoa;
import com.ccs.foundation.respositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaOutput create(@RequestBody PessoaInput input) {
        return PessoaOutput.toOutput(pessoaRepository.save(input.toPessoa()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PessoaOutput update(@PathVariable UUID id, @RequestBody PessoaInput input) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow();
        pessoa.setNome(input.nome());
        return PessoaOutput.toOutput(pessoaRepository.save(pessoa));
    }
}
