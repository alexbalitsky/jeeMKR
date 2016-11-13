package jsfbeans;

import service.CatalogService;
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
public class AddCatalogBean {
    private String name;

    @EJB
    private CatalogService catalogService;


    public String save(){
        boolean success = catalogService.save(name);
        return success ? "getCatalogs?faces-redirect=true" : "info?message=fail to save catalog!&faces-redirect=true";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
