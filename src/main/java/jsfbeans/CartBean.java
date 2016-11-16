package jsfbeans;

import dao.ItemDAO;
import entity.Item;
import service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by obalitskyi on 11/15/16.
 */
@ManagedBean
@SessionScoped
public class CartBean {
    private String card;
    private String date;
    private String secureCode;

    private Map<Long, Integer> cart = new HashMap<>();
    private Map<Item, Integer> items = new HashMap<>();

    @EJB
    private ItemDAO itemDAO;
    @EJB
    private UserService userService;

    public String buy(){
       if (userService.buy(card, date, secureCode, cart)){
           cart.clear();
           return "getCatalogs?&faces-redirect=true";
       }else {
           return "/info?message=fail to buy!&faces-redirect=true";
       }
    }

    public String delete(String id){
        try {
            if (cart.containsKey(Long.valueOf(id))){
                cart.remove(Long.valueOf(id));
                return "cart?&faces-redirect=true";
            }
        }catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }
        return "/info?message=fail to delete item from cart&faces-redirect=true";
    }

    public String addToCart(String id, String countToBuy, String catalogId){
        if (countToBuy == null) return "/info?message=fail to add to cart&faces-redirect=true";
        try {
            Long lId = Long.valueOf(id);
            Integer icountToBuy = Integer.valueOf(countToBuy);

            Integer actualCount = itemDAO.find(lId).getCount();
            if(icountToBuy > actualCount) return "/info?message=fail to add to cart&faces-redirect=true";

            if (cart.containsKey(lId)){
                cart.put(lId, cart.get(lId) + icountToBuy);
            }else {
                cart.put(lId, icountToBuy);
            }
            return "getItems?catalogId=" + catalogId + "&faces-redirect=true";
        }catch (NumberFormatException nfe){
        }
        return "/info?message=fail to add to cart&faces-redirect=true";


    }

    public Map<Item, Integer> getItems() {
        Map<Item, Integer> result = new HashMap<>();
        for (Map.Entry entry : cart.entrySet()){
            result.put(itemDAO.find((Long) entry.getKey()), (Integer) entry.getValue());
        }
        return result;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    public Map<Long, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Long, Integer> cart) {
        this.cart = cart;
    }
}
