package com.bilgeadam.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    //Mail işlemi için config işlemleri

    @Value("${rabbitmq.exchange-auth}")
    String exchangeName ;
    @Value("${rabbitmq.registerMailQueue2}")
    private String registerMailQueue2;
    @Value("${rabbitmq.registerMailBindingKey}")
    private String registerMailBindingKey;

    @Bean
    DirectExchange exchangeAuth(){
        return new DirectExchange(exchangeName);
    }

    @Bean
    Queue registerMailQueue(){
        return new Queue(registerMailQueue2);
    }

    @Bean
    public Binding bindingRegisterMail(final Queue registerMailQueue, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(registerMailQueue).to(exchangeAuth).with(registerMailBindingKey);
    }
}
