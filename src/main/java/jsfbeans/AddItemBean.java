package jsfbeans;

import service.ItemService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Created by alex on 13.11.16.
 */

@ManagedBean
@ViewScoped
public class AddItemBean {
    private String name;
    private String price;
    private String count;

    private String catalogId;

    @EJB
    private ItemService itemService;

    @PostConstruct
    public void init(){
        catalogId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("catalogId");
    }

    public String save(){
        boolean success = itemService.save(catalogId, name, price, count);
        return success ? "getItems?catalogId=" + catalogId + "&faces-redirect=true" : "info?message=fail to save item!&faces-redirect=true";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
}
