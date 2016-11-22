package jsfbeans;

import dao.VacancyDAO;
import entity.Vacancy;
import service.CategoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by ignatenko on 20.11.16.
 */

@ManagedBean
@ViewScoped
public class UserGetVacancyBean {
    private String categoryId;
    private List<Vacancy> vacancies;

    @EJB
    private CategoryService categoryService;

    @PostConstruct
    public void init(){
        categoryId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("category_id");
        vacancies = categoryService.getVacanciesByCategoryId(categoryId);//TODO
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
}
