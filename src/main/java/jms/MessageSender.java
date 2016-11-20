package jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;


@Stateless
public class MessageSender {
    @Resource(mappedName = "jmspool")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jmsjndi")
    private Topic topic;
    public void send() {
        JMSContext jmsContext = connectionFactory.createContext();
        JMSProducer jmsProducer = jmsContext.createProducer();
        System.out.println("Sending object to create: ");
        jmsProducer.send(topic, "request have just isued");

    }
}