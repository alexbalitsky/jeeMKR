package jsfbeans.bck;

import entity.bck.Catalog;
import service.bck.CatalogService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by alex on 13.11.16.
 */

@ManagedBean
@ViewScoped
public class GetCatalogBean {
    private List<Catalog> catalogs;

    @EJB
    private CatalogService catalogService;


    public List<Catalog> getCatalogs() {
        catalogs = catalogService.getAll();
        return catalogs;
    }

    public void setCatalogs(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }
}
