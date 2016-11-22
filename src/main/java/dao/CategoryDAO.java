package dao;

import entity.Category;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class CategoryDAO extends AbstractDAO<Category> {
    public CategoryDAO() {
        super(Category.class);
    }
}
