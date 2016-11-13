package service;

import dao.GroupDAO;
import dao.UserDAO;
import entity.Group;
import entity.User;
import org.apache.log4j.Logger;
import static util.Constants.DEFAULT_GROUP;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by alex on 12.11.16.
 */

@Stateless
@Local
public class UserService {
    Logger LOG = Logger.getLogger(UserService.class);

    @EJB
    private UserDAO userDAO;

    @EJB
    private GroupDAO groupDAO;

    public User getByLogin(String login){
        if (login == null){
            LOG.error("login is null");
            return null;
        }

        return userDAO.find(login);
    }

    public boolean doRegistration(String login, String password, String name, String surname, String card, String address){
        User user = new User(login, password, name, surname, card, address);
        Group group = new Group(login, DEFAULT_GROUP);
        try {
            groupDAO.save(group);
            userDAO.save(user);
            LOG.info("successfully registered user");
            return true;
        }catch (Exception e){
            LOG.error("fail to register user");
            e.printStackTrace();
        }

        return false;
    }



}
