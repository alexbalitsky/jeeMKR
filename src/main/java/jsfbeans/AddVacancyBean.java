package jsfbeans;

import entity.Category;
import entity.Company;
import entity.Vacancy;
import service.VacancyService;
import util.Constants;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by ignatenko on 19.11.16.
 */

@ManagedBean
@ViewScoped
public class AddVacancyBean {
    private String date;
    private String salary;
    private String requirement;

    private List<Company> companies;
    private List<Category> categories;

    private String company;
    private String category;

    @EJB
    private VacancyService vacancyService;

    @PostConstruct
    public void init(){
        this.companies = vacancyService.getAllCompanies();
        this.categories = vacancyService.getAllCategories();
    }

    public String addVacancy(){
        if (vacancyService.addVacancy(date, salary, requirement, company, category)){
            return "index?" + Constants.REDIRECT_PARAM;
        }else {
            return "info?message=fgregr&" + Constants.REDIRECT_PARAM;
        }
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
