package com.santiagovl.dddpill.legacy.persistence.email;

import com.santiagovl.dddpill.legacy.User;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

@Component
public class UserJavaxEmailRepository implements UserEmailRepository {

  @Override
  public void send(final User user) {
    // Send a verification code via email
    final Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", "smtp.gmail.com");
    final Session session = Session.getDefaultInstance(properties);
    try {
      final MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress("foo@gmail.com"));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
      message.setSubject("Verification code");
      message.setText(String.format("%06d", new Random().nextInt(user.getCreatedAt().getNano())));
      // Transport.send(message);
    } catch (final MessagingException e) {
      e.printStackTrace();
    }
  }
}
