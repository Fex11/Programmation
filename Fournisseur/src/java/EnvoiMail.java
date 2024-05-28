import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoiMail {

    public static void main(String[] args) {
        // Remplacez ces informations par vos propres détails
        String destinataire = "fournisseur421@gmail.com";
        String sujet = "Sujet du mail";
        String corps = "Mety ve fex ah";

        // Configuration des propriétés
        Properties proprietes = new Properties();
        proprietes.put("mail.smtp.auth", "true");
        proprietes.put("mail.smtp.starttls.enable", "true");
        proprietes.put("mail.smtp.host", "smtp.gmail.com"); // Remplacez par votre serveur SMTP
        proprietes.put("mail.smtp.port", "587"); // Port SMTP, peut varier en fonction de la configuration

        // Création de la session
        Session session = Session.getInstance(proprietes, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                // Remplacez ces informations par vos propres identifiants
                return new javax.mail.PasswordAuthentication("morelb470@gmail.com", "zawx aafs nepi hxcb");
            }
        });

        try {
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("morelb470@gmail.com")); // Expéditeur
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire)); // Destinataire
            message.setSubject(sujet); // Sujet
            message.setText(corps); // Corps du message

            // Envoi du message
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
