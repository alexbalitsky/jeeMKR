package jsfbeans;



import entity.Category;
import entity.Company;
import entity.Position;
import service.VacancyService;

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
public class UpdateVacancyBean {

    private String title;
    private String date;
    private String salary;
    private String requirement;

    private List<Company> companies;
    private List<Category> categories;
    private List<Position> positions;

    @EJB
    private VacancyService vacancyService;

    private String vacancyId;
    private String company;
    private String position;
    private String category;

    @PostConstruct
    public void init(){
        this.companies = vacancyService.getAllCompanies();
        this.categories = vacancyService.getAllCategories();
        this.positions = vacancyService.getAllPositions();
        vacancyId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("vacancy");
    }

    public String update(){
        boolean success = vacancyService.update(vacancyId, title, date, salary, requirement, company, category, position);
        return success ? "getVacancy?faces-redirect=true" :
                "info?message=fail to update vacancy!&faces-redirect=true";
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public String getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
