package jsfbeans.bck;

import service.bck.CatalogService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
