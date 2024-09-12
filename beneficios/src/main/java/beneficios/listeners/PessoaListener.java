package beneficios.listeners;


import beneficios.entities.Pessoa;
import beneficios.respositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PessoaListener {

    private final PessoaRepository pessoaRepository;

    @RabbitListener(queues = "fnd.pessoa.beneficios")
    public void onEventPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        log.info("Pessoa salva com sucesso: {}", pessoa);
    }

}
