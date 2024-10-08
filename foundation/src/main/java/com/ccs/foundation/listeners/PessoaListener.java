package com.ccs.foundation.listeners;

import com.ccs.foundation.configs.FoundationConstants;
import com.ccs.foundation.entities.Pessoa;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PessoaListener {

    private final RabbitTemplate rabbitTemplate;
    private final FoundationConstants foundationConstants;

    @PostUpdate
    @PostPersist
    public void publishEvent(Pessoa pessoa) {
        rabbitTemplate.convertAndSend(foundationConstants.getPessoaRoutingKey(),pessoa);
        log.info("Pessoa publicado: {}", pessoa);
    }

}
