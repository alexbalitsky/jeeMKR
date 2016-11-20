package jsfbeans;

import entity.Category;
import service.CategoryService;
import util.Constants;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 * Created by ignatenko on 19.11.16.
 */

@ManagedBean
@RequestScoped
public class DeleteCategoryBean {
    @EJB
    private CategoryService categoryService;

    public String delete(String id){
        boolean success = categoryService.delete(id);
        return success ? "getCategory?faces-redirect=true" :
                "info?message=fail to delete category!&faces-redirect=true";
    }
}
