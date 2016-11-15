package jsfbeans;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.Item;
import entity.User;
import service.CartService;
import service.ItemService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 13.11.16.
 */

@ManagedBean
@SessionScoped
public class ItemBean {
    @EJB
    private CartService cartService;
    @EJB
    private ItemService itemService;

    private String catalogId;

    private String id;
    private String name;
    private Integer price;
    private Integer count;

    private String countToBuy;

    @PostConstruct
    public void init(){
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemId");
        catalogId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("catalogId");
        Item item = itemService.get(getId());
        this.name = item.getName();
        this.price = item.getPrice();
        this.count = item.getCount();
    }

    public String addToCart(){
        boolean success = cartService.addToCart(id, countToBuy);
        return success ? "getItems?catalogId=" + getCatalogId() + "&faces-redirect=true" : "/info?message=fail to add to cart&faces-redirect=true";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCountToBuy() {
        return countToBuy;
    }

    public void setCountToBuy(String countToBuy) {
        this.countToBuy = countToBuy;
    }

    public String getCatalogId() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("catalogId");
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("catalogId");
    }

    public String getId() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemId");
    }

    public void setId(String id) {
        this.id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemId");
    }
}
