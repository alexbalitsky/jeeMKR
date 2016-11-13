package jsfbeans;

import service.CatalogService;
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
public class DeleteCatalogBean {
    @EJB
    private CatalogService catalogService;

    public String delete(String id){
        boolean success = catalogService.delete(id);
        return success ? "getCatalogs?faces-redirect=true" :
                "info?message=fail to delete catalog!&faces-redirect=true";
    }
}
