package jsfbeans.bck;

import service.bck.CatalogService;

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
public class UpdateCatalogBean {
    private String name;

    @EJB
    private CatalogService catalogService;

    private String catalogId;

    @PostConstruct
    public void init(){
        catalogId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("catalogId");
    }

    public String update(){
        boolean success = catalogService.update(catalogId, name);
        return success ? "getCatalogs?faces-redirect=true" :
                "info?message=fail to update catalog!&faces-redirect=true";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
}
