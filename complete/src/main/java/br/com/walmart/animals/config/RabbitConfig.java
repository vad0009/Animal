package br.com.walmart.animals.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.walmart.animals.rabbit.Receiver;
@Configuration
@EnableRabbit
public class RabbitConfig {
	public final static String queueName = "animals.boot.queue";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	// Cria MessageConsumers simultâneos para os ouvintes especificados.
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

		// Define o ConnectionFactory para obter Conexões RabbitMQ.
		container.setConnectionFactory(connectionFactory);

		// Define o nome da(s) fila(s) para receber mensagens.
		container.setQueueNames(queueName);

		// Define a implementação do ouvinte de mensagem para ser registrado.
		container.setMessageListener(listenerAdapter);

		return container;
	}

	@Bean
	// Permite que os métodos de ouvinte operem em tipos de conteúdo de
	// mensagens, completamente independentes da API Rabbit.
	MessageListenerAdapter listenerAdapter(Receiver receiver) {

		// Cria um novo MessageListenerAdapter para um determinado dado,
		// enquanto também declara seu método POJO.
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
}
