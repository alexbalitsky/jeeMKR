package jsfbeans;

import entity.Category;
import entity.Vacancy;
import service.CategoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ignatenko on 19.11.16.
 */
@ManagedBean
@RequestScoped
public class GetCategoryBean {
    @EJB
    private CategoryService categoryService;


    private List<Category> categories = new ArrayList<>();
    private Map<Long, List<Vacancy>> map = new HashMap();

    @PostConstruct
    public void init(){
        categories.addAll(categoryService.getAll());
        for (Category category : categories){
            map.put(category.getId(), new ArrayList<Vacancy>(category.getVacancies()));
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Map<Long, List<Vacancy>> getMap() {
        return map;
    }

    public void setMap(Map<Long, List<Vacancy>> map) {
        this.map = map;
    }
}

