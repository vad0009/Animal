package br.com.walmart.animals.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.walmart.animals.rabbit.AnimalsListener;

@Configuration
@EnableRabbit
public class RabbitConfig {

	public final static String queueName = "animals.boot.queue";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

		container.setConnectionFactory(connectionFactory);

		container.setQueueNames(queueName);

		container.setMessageListener(listenerAdapter);

		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(AnimalsListener receiver) {

		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
}
