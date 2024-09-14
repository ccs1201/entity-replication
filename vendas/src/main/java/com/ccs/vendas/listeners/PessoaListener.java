package com.ccs.vendas.listeners;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.ccs.vendas.entities.Pessoa;
import com.ccs.vendas.respositories.PessoaRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class PessoaListener {

    private final PessoaRepository pessoaRepository;

    @RabbitListener(queues ="fnd.pessoa.vendas")
    public void onEventPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        log.info("Pessoa salva com sucesso: {}", pessoa);
    }

}
