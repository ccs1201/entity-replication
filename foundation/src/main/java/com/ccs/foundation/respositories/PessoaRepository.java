package com.ccs.foundation.respositories;

import com.ccs.foundation.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
  }