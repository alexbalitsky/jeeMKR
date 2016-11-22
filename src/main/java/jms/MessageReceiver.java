
package jms;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Ignatenko Olexandr
 */
@MessageDriven(mappedName = "jms/topic")
public class MessageReceiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage vacancymessage = (TextMessage) message;
        try {
            System.out.println("Message changed: " + (vacancymessage.getText()));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}