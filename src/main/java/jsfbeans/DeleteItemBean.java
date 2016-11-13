package jsfbeans;

import service.ItemService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 * Created by alex on 13.11.16.
 */

@ManagedBean
@RequestScoped
public class DeleteItemBean {
    @EJB
    private ItemService itemService;

    public String delete(String itemId, String catalogId){
        boolean success = itemService.delete(itemId, catalogId);
        return success ? "getItems?catalogId=" + catalogId + "&faces-redirect=true" :
                "info?message=fail to delete item!&faces-redirect=true";
    }
}
