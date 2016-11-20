package jms;


import javax.jms.*;

/**
 * @author Semeniuk Vadym
 */
public class CustomMessageListener implements MessageListener {
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