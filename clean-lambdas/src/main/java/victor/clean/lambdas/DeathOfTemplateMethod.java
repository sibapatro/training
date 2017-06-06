package victor.clean.lambdas;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

public class DeathOfTemplateMethod {

	public void sendOrderReceivedEmail(String emailAddress) {
		EmailContext context = new EmailContext(/*smtpConfig,etc*/);
		int MAX_RETRIES = 3;
		for (int i = 0; i < MAX_RETRIES; i++ ) {
			Email email = new Email(); // constructor generates new unique ID
			email.setSender("john.doe@corp.com");
			email.setReplyTo("/dev/null");
			email.setTo(emailAddress);
			email.setSubject("Order Received");
			email.setBody("Thank you for your order");
			boolean success = context.send(email);
			if (success) break;
		}
	}
}

class EmailContext {
	public boolean send(Email email) {
		return new Random().nextBoolean();
	}
}

class Email {
	@Getter @Setter private String sender;
	@Getter @Setter private String subject;
	@Getter @Setter private String body;
	@Getter @Setter private String replyTo;
	@Getter @Setter private String to;
}
