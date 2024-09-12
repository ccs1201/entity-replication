package vendas.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class VendasConstants {

    @Value("${foundation.exchange}")
    private String exchange;

    @Value("${vendas.pessoa.queue}")
    private String pessoaQueue;

    @Value("${foundation.pessoa.key}")
    private String pessoaRoutingKey;

}
