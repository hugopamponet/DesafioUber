package br.com.desafioUber.adapters;

public interface EmailSenderGateway {

	void sendEmail(String to, String subject, String body);
}
