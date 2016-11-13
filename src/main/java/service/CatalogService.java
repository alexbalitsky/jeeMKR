package service;

import dao.CatalogDAO;
import entity.Catalog;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;
import static util.Utils.toList;

/**
 * Created by alex on 13.11.16.
 */

@Stateless
@Local
public class CatalogService {
    Logger LOG = Logger.getLogger(CatalogService.class);

    @EJB
    private CatalogDAO catalogDAO;


    public List<Catalog> getAll(){
        return toList(catalogDAO.findAll());
    }

    public boolean save(String name){
        Catalog catalog = new Catalog(name);
        try {
            catalogDAO.save(catalog);
            LOG.info("successfully saved catalog");
            return true;
        }catch (Exception e){
            LOG.error("fail to save catalog");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id){
        try {
            catalogDAO.delete(Long.valueOf(id));
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse catalog id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to delete catalog");
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String id, String name){
        try {
            Catalog catalog = catalogDAO.find(Long.valueOf(id));
            catalog.setName(name);
            catalogDAO.update(catalog);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse catalog id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to update catalog");
            e.printStackTrace();
        }
        return false;
    }
}
