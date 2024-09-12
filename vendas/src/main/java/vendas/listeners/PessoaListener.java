package beneficios.listeners;


import beneficios.entities.Pessoa;
import beneficios.respositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessoaListener {

    private final PessoaRepository pessoaRepository;

    @RabbitListener(queues = "pessoa")
    public void onEventPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

}
