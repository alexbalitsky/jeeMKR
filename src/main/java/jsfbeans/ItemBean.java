package jsfbeans;

import entity.Item;
import service.ItemService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Created by alex on 13.11.16.
 */

@ManagedBean
@ViewScoped
public class ItemBean {
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
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
