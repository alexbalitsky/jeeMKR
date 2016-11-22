package dao;

import entity.Group;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by ignatenko on 19.11.16.
 */
@Stateless
@Local
public class GroupDAO extends AbstractDAO<Group>{
    public GroupDAO() {
        super(Group.class);
    }


}
