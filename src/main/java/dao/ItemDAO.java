package dao;

import entity.Item;
import entity.User;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by alex on 12.11.16.
 */
@Stateless
@Local
public class ItemDAO extends AbstractDAO<Item>{
    public ItemDAO() {
        super(Item.class);
    }


}
