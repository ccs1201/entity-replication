package beneficios.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class FoundationConstants {

    @Value("${foundation.exchange}")
    private String exchange;

    @Value("${foundation.pessoa.queue}")
    private String pessoaQueue;

}
