package pt.uc.dei.eai.common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Responsible to send e-mails.
 */
public class SendMail
{
    public static String SMTP_SERVER = "smtp.dei.uc.pt";
	
    /**
     * Send an e-mail.
     * @param recipients e-mail recipients
     * @param subject e-mail subject
     * @param message body of e-mail
     * @param from e-mail source
     * @throws Exception
     * @throws MessagingException
     */
    public static void postMail(String recipient, String subject, String message , String from) 
            throws Exception, MessagingException
    {
        boolean debug = false;

        // Test if we have the recipient list
        if(recipient == null)
            throw new Exception("E-mail list is empty!");
        
        // Set the host SMTP address
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_SERVER);
    
        // Create some properties and get the default Session
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);
    
        // Create a message
        Message msg = new MimeMessage(session);
    
        // Set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);
    
        InternetAddress[] addressTo = new InternetAddress[1]; 
        addressTo[0] = new InternetAddress(recipient);
        
        msg.setRecipients(Message.RecipientType.TO, addressTo);
        
        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);        
    }
}