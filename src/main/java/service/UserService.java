package service;

import dao.GroupDAO;
import dao.bck.ItemDAO;
import dao.UserDAO;
import entity.Group;
import entity.bck.Item;
import entity.User;
import org.apache.log4j.Logger;

import static util.Constants.DEFAULT_GROUP;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Map;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class UserService {
    Logger LOG = Logger.getLogger(UserService.class);

    @EJB
    private UserDAO userDAO;

    @EJB
    private ItemDAO itemDAO;

    @EJB
    private GroupDAO groupDAO;

    public User getByLogin(String login) {
        if (login == null) {
            LOG.error("login is null");
            return null;
        }

        return userDAO.find(login);
    }

    public boolean doRegistration(String login, String password, String confirmPassword, String name, String surname, String email) {
        if (!password.equals(confirmPassword)) {
            return false;
        }
        User user = new User(login, password, name, surname, email);
        Group group = new Group(login, DEFAULT_GROUP);
        try {
            groupDAO.save(group);
            userDAO.save(user);
            LOG.info("successfully registered user");
            return true;
        } catch (Exception e) {
            LOG.error("fail to register user");
            e.printStackTrace();
        }

        return false;
    }

    public boolean buy(String card, String date, String secureCode, Map<Long, Integer> items) {
        boolean success = true;
        if (!pay(card, date, secureCode)) return false;
        try {
            for (Map.Entry<Long, Integer> entry : items.entrySet()) {
                Item item = itemDAO.find(entry.getKey());
                item.setCount(item.getCount() - entry.getValue());
                itemDAO.update(item);
            }
            sentBroadcastMessage("User successfully bought " + items.size() + " items");
        } catch (Exception e) {
            LOG.error("fail to buy!");
            success = false;
        }
        return success;
    }

    private boolean pay(String card, String date, String secureCode) {
        // TODO pay
        return true;
    }


    public String getGroupName(String login) {
        return groupDAO.find(login).getGroupName();
    }

    public void sentBroadcastMessage(String message) {
        try {   //Create and start connection
            InitialContext ctx = new InitialContext();
            TopicConnectionFactory f = (TopicConnectionFactory) ctx.lookup("myTopicConnectionFactory");
            TopicConnection con = f.createTopicConnection();
            con.start();
            //2) create queue session
            TopicSession ses = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            //3) get the Topic object
            Topic t = (Topic) ctx.lookup("myTopic");
            //4)create TopicPublisher object
            TopicPublisher publisher = ses.createPublisher(t);
            //5) create TextMessage object
            TextMessage msg = ses.createTextMessage();


            msg.setText(message);
            //7) send message
            publisher.publish(msg);
            //8) connection close
            con.close();
        } catch (Exception e) {
            LOG.error(e);
        }
    }

}
