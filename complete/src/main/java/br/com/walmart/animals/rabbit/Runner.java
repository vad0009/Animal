package br.com.walmart.animals.rabbit;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import br.com.walmart.animals.config.RabbitConfig;

/*Ele recupera o RabbitTemplate do aplicativo e envia um "Animals from RabbitMQ!" 
 Mensagem na fila "animals-test". Finalmente, fecha o aplicativo Spring e o aplicativo termina.*/
@Component
public class Runner implements CommandLineRunner {

	// Auxilia a classe a simplificar o acesso síncronizando com RabbitMQ (envio
	// e recebimento de mensagens).
	private final RabbitTemplate rabbitTemplate;

	// Class Receiver
	private final Receiver receiver;
	
	// Os métodos atuais só devem ser usados ​​pelo código de inicialização e
	// desligamento.
	//private final ConfigurableApplicationContext context;

	public Runner(Receiver receiver, RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
		//this.context=context;
	}

	// Interface usada para indicar que um bean deve ser executado quando está
	// contido em SpringApplication.
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending message...");
		rabbitTemplate.convertAndSend(RabbitConfig.queueName,"ANIMAL");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		// context.close();
	}

}
