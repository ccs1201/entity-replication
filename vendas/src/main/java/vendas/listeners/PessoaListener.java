package vendas.listeners;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import vendas.entities.Pessoa;
import vendas.respositories.PessoaRepository;

@Component
@RequiredArgsConstructor
public class PessoaListener {

    private final PessoaRepository pessoaRepository;

    @RabbitListener(queues ="fnd.pessoa.vendas")
    public void onEventPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

}
