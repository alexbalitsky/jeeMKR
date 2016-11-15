package service;

import dao.ItemDAO;
import entity.Item;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by obalitskyi on 11/15/16.
 */

@Stateful
@Local
public class CartService {
    Logger LOG = Logger.getLogger(CartService.class);

    @EJB
    private ItemDAO itemDAO;

    private Map<Long, Integer> cart = new HashMap<>();

    public boolean addToCart(String id, String countToBuy){
        if (countToBuy == null) return false;
        try {
            Long lId = Long.valueOf(id);
            Integer icountToBuy = Integer.valueOf(countToBuy);

            Integer actualCount = itemDAO.find(lId).getCount();
            if(icountToBuy > actualCount) return false;

            if (cart.containsKey(lId)){
                cart.put(lId, cart.get(lId) + icountToBuy);
            }else {
                cart.put(lId, icountToBuy);
            }
            LOG.info("put to cart item id: " + id + ", count: " + countToBuy);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse item id or count to buy");
        }
        return false;
    }

    public Map<Item, Integer> getItemsToBuy(){
        Map<Item, Integer> result = new HashMap<>();
        for (Map.Entry entry : cart.entrySet()){
            result.put(itemDAO.find((Long) entry.getKey()), (Integer) entry.getValue());
        }
        return result;
    }

    public boolean delete(String id){
        try {
            if (cart.containsKey(Long.valueOf(id))){
                cart.remove(Long.valueOf(id));
                return true;
            }
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse id");
            nfe.printStackTrace();
        }
        return false;
    }


    public Map<Long, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Long, Integer> cart) {
        this.cart = cart;
    }
}
