package jsfbeans;

import entity.Company;
import entity.Vacancy;
import service.CategoryService;
import service.CompanyService;
import util.Constants;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by ignatenko on 19.11.16.
 */

@ManagedBean
@ViewScoped
public class AddCategoryBean {
    private String title;


    @EJB
    private CategoryService categoryService;

    public String save(){
        if (categoryService.save(title)){
            return "index?" + Constants.REDIRECT_PARAM;
        }else {
            return "info?message=fail to add category" + Constants.REDIRECT_PARAM;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
