package dao;

import entity.User;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by alex on 12.11.16.
 */
@Stateless
@Local
public class UserDAO extends AbstractDAO<User>{
    public UserDAO() {
        super(User.class);
    }

}
