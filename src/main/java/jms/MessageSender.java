package jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;


@Stateless
public class MessageSender {
    @Resource(mappedName = "jms/mkr")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/topic")
    private Topic topic;

//    public void send(String message) {
//        JMSContext jmsContext = connectionFactory.createContext();
//        JMSProducer jmsProducer = jmsContext.createProducer();
//        System.out.println("Sending object to create: ");
//        jmsProducer.send(topic, message);
//
//    }

    public void send( String text)  {
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer;
            producer = session.createProducer(topic);
            producer.send(session.createTextMessage(text));
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}