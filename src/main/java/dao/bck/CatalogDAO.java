package dao.bck;

import dao.AbstractDAO;
import entity.bck.Catalog;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by alex on 12.11.16.
 */
@Stateless
@Local
public class CatalogDAO extends AbstractDAO<Catalog> {
    public CatalogDAO() {
        super(Catalog.class);
    }


}
