
package jms;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @author Semeniuk Vadym
 */
@MessageDriven(mappedName ="jmsjndi" )
public class MessageReceiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        ObjectMessage issueDTOmessage = (ObjectMessage) message;
        try {
            System.out.println("Issue changed: "+ (issueDTOmessage.getObject()).toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}