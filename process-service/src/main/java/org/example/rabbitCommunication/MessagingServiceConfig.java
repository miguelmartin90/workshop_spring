package org.example.rabbitCommunication;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingServiceConfig {

    // Desde queue_pagamento se enviarán y se recibirán los msjs. Se creará en Exchange
    public static final String QUEUE = "reader-request-queue";

    public static final String EXCHANGE = "reader-request-exchange";

    // Clave de enrrutamiento. Se usará para vincular la cola al Exchange y
    // filtrar los mensajes que la queue recibe.
    public static final String ROUTING_KEY = "reader-request-rout-key";

    // Un Bean es un componente que es reutilizable en cualquier parte del proyecto
    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    // Me va a vincular la cola al exchange con una clave de enrutamiento
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // Método para serializar y deserializar a json el objeto java pasado
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    // Comunicación con el servidor RabbitMQ
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
