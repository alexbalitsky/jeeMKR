package service;

import dao.GroupDAO;
import dao.ItemDAO;
import dao.UserDAO;
import entity.Group;
import entity.Item;
import entity.User;
import org.apache.log4j.Logger;
import static util.Constants.DEFAULT_GROUP;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import java.util.Map;

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
    private ItemDAO itemDAO;

    @EJB
    private GroupDAO groupDAO;

    public User getByLogin(String login){
        if (login == null){
            LOG.error("login is null");
            return null;
        }

        return userDAO.find(login);
    }

    public boolean doRegistration(String login, String password, String confirmPassword, String name, String surname, String card, String address){
        if (!password.equals(confirmPassword)){
            return false;
        }
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

    public boolean buy(String card, String date, String secureCode, Map<Long, Integer> items){
        boolean success = true;
        if (!pay(card, date, secureCode)) return false;
        try {
            for (Map.Entry<Long, Integer> entry : items.entrySet()){
                Item item = itemDAO.find(entry.getKey());
                item.setCount(item.getCount() - entry.getValue());
                itemDAO.update(item);
            }
        }catch (Exception e){
            LOG.error("fail to buy!");
            success = false;
        }
        return success;
    }

    private boolean pay(String card, String date, String secureCode){
        // TODO pay
        return true;
    }


    public String getGroupName(String login){
        return groupDAO.find(login).getGroupName();
    }

}
