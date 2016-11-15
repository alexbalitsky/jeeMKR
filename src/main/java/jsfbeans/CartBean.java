package jsfbeans;

import entity.Item;
import service.CartService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.Map;

/**
 * Created by obalitskyi on 11/15/16.
 */
@ManagedBean
@ViewScoped
public class CartBean {
    private String card;
    private String date;
    private String secureCode;

    private Map<Item, Integer> items;

    @EJB
    private CartService cartService;

    public void buy(){

    }

    @PostConstruct
    public void init(){
        items = cartService.getItemsToBuy();
    }

    public String delete(String id){
        boolean success = cartService.delete(id);
        return success ? "cart?&faces-redirect=true" : "/info?message=fail to delete item from cart&faces-redirect=true";
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

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }
}
