package vendas.listeners;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import vendas.configs.VendasConstants;
import vendas.entities.Pessoa;
import vendas.respositories.PessoaRepository;

@Component
@RequiredArgsConstructor
public class PessoaListener {

    private final PessoaRepository pessoaRepository;
    private final VendasConstants vendasConstants;

    @RabbitListener(queues ="vendas.pessoa")
    public void onEventPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

}
