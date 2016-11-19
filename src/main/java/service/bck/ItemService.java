package service.bck;

import dao.bck.CatalogDAO;
import dao.bck.ItemDAO;
import entity.bck.Catalog;
import entity.bck.Item;
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
public class ItemService {
    Logger LOG = Logger.getLogger(ItemService.class);

    @EJB
    private ItemDAO itemDAO;
    @EJB
    private CatalogDAO catalogDAO;

    public List<Item> getByCatalog(String id){
        List<Item> result = null;
        try {
            Catalog catalog = catalogDAO.find(Long.valueOf(id));
            result = toList(catalog.getItems());
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse catalog id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to get catalog");
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(String id, String catalogId){
        try {
            Item item = itemDAO.find(Long.valueOf(id));
            Catalog catalog = catalogDAO.find(Long.valueOf(catalogId));
            catalog.getItems().remove(item);
            itemDAO.delete(Long.valueOf(id));
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse item id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to delete item");
            e.printStackTrace();
        }
        return false;
    }

    public boolean save(String catalogId, String name, String price, String count){
        try {
            Item item = new Item(name, Integer.valueOf(price), Integer.valueOf(count));
            Catalog catalog = catalogDAO.find(Long.valueOf(catalogId));
            item.setCatalog(catalog);
            itemDAO.save(item);
            LOG.info("successfully saved item");
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse item price, item count or catalog id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to save item");
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String id, String name, String price, String count){
        try {
            Item item = itemDAO.find(Long.valueOf(id));
            item.setName(name);
            item.setPrice(Integer.valueOf(price));
            item.setCount(Integer.valueOf(count));
            itemDAO.update(item);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse item id or price or count");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to update item");
            e.printStackTrace();
        }
        return false;
    }

    public Item get(String id){
        Item result = null;
        try {
            result = itemDAO.find(Long.valueOf(id));
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse item id");
            nfe.printStackTrace();
        }
        return result;
    }
}
