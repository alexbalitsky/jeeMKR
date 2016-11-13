package jsfbeans;

import entity.Item;
import service.ItemService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by alex on 13.11.16.
 */

@ManagedBean
@ViewScoped
public class GetItemBean {
    @EJB
    private ItemService itemService;

    private String catalogId;
    private List<Item> items;

    @PostConstruct
    public void init(){
        catalogId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("catalogId");
    }

    public List<Item> getItems() {
        items = itemService.getByCatalog(catalogId);
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
}
