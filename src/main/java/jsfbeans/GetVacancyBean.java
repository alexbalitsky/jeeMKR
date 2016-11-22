package jsfbeans;

import entity.Category;
import entity.Company;
import entity.Position;
import entity.Vacancy;
import service.VacancyService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ignatenko on 20.11.16.
 */
@ManagedBean
@ViewScoped
public class GetVacancyBean {

    @EJB
    private VacancyService vacancyService;


    private List<Vacancy> vacancies = new ArrayList<>();
    private Map<Long, Company> mapCompany = new HashMap<>();
    private Map<Long, Category> mapCategory = new HashMap<>();
    private Map<Long, Position> mapPosition = new HashMap<>();

    @PostConstruct
    public void init(){

        vacancies.addAll(vacancyService.getAllVacancies());
        for (Vacancy vacancy: vacancies){
            mapCompany.put(vacancy.getId(), vacancy.getCompany());
            mapCategory.put(vacancy.getId(), vacancy.getCategory());
            mapPosition.put(vacancy.getId(), vacancy.getPosition());
        }
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public Map<Long, Company> getMapCompany() {
        return mapCompany;
    }

    public void setMapCompany(Map<Long, Company> mapCompany) {
        this.mapCompany = mapCompany;
    }

    public Map<Long, Category> getMapCategory() {
        return mapCategory;
    }

    public void setMapCategory(Map<Long, Category> mapCategory) {
        this.mapCategory = mapCategory;
    }

    public Map<Long, Position> getMapPosition() {
        return mapPosition;
    }

    public void setMapPosition(Map<Long, Position> mapPosition) {
        this.mapPosition = mapPosition;
    }
}
