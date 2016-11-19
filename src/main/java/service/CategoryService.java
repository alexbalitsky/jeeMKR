package service;

import dao.CategoryDAO;
import entity.Category;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class CategoryService {
    @EJB
    private CategoryDAO categoryDAO;

    public boolean addCategory(String title){
        try {
            Category category = new Category(title);
            categoryDAO.save(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
