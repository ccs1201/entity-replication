package vendas.configs;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;


@Configuration
@RequiredArgsConstructor
public class AMQPConfig {

    private final VendasConstants vendasConstants;

    @Bean
    ObjectMapper objectMapper() {
        final var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDefaultPropertyInclusion(NON_NULL);
        return mapper;
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        var msgConverter = new Jackson2JsonMessageConverter(objectMapper);
        msgConverter.setAlwaysConvertToInferredType(true);
        return msgConverter;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
            ConnectionFactory connectionFactory, MessageConverter jackson2JsonMessageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(jackson2JsonMessageConverter);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder
                .fanoutExchange(vendasConstants.getExchange())
                .durable(true)
                .build();
    }

    @Bean
    public Queue pessoaQueue() {
        return QueueBuilder.durable(vendasConstants.getPessoaQueue())
                .build();
    }

    @Bean
    public Binding pessoaBinding(Queue pessoaQueue, Exchange exchange) {
        return BindingBuilder.bind(pessoaQueue)
                .to(exchange)
                .with(vendasConstants.getPessoaRoutingKey())
                .noargs();
    }
}
