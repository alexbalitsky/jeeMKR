package dao;

import entity.User;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by ignatenko on 19.11.16.
 */
@Stateless
@Local
public class UserDAO extends AbstractDAO<User>{
    public UserDAO() {
        super(User.class);
    }

}
