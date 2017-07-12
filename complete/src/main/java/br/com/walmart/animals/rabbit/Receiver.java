package br.com.walmart.animals.rabbit;

import org.springframework.stereotype.Component;

/*
 * O Receiver é um POJO(Plain Old Java Object) simples que define um método para receber mensagens.
 * Quando você o registra para receber mensagens, você pode nomeá-lo qualquer
 * coisa que desejar.
 */
@Component
public class Receiver {

	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");

	}
}
