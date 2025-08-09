package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafioUber.application.EmailSenderService;
import br.com.desafioUber.core.EmailRequest;
import exception.EmailServiceException;

@RestController
@RequestMapping("/email")
public class EmailSenderController {
	
	private final EmailSenderService emailSenderService;
	
	@Autowired
	public EmailSenderController(EmailSenderService emailService) {
		this.emailSenderService = emailService;
	}
	
	@PostMapping()
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
		try {
			this.emailSenderService.sendEmail(request.to(), request.subject(), request.body());
			return ResponseEntity.ok("Email sent successfully");
		} catch (EmailServiceException ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email");
		}
	}
}