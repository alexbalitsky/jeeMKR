package jsfbeans;

import entity.Vacancy;
import service.CategoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ignatenko on 19.11.16.
 */
@ManagedBean
@ViewScoped
public class UpdateCategoryBean {
    private String title;

    @EJB
    private CategoryService categoryService;

    private String categoryId;

    @PostConstruct
    public void init(){
        categoryId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("category");
    }

    public String update(){
        boolean success = categoryService.update(categoryId, title);
        return success ? "getCategory?faces-redirect=true" :
                "info?message=fail to update category!&faces-redirect=true";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
