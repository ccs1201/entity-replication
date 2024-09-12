package com.ccs.foundation.api.controllers;

import com.ccs.foundation.entities.Pessoa;
import com.ccs.foundation.respositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }
}
