package service;

import dao.CategoryDAO;
import dao.CompanyDAO;
import dao.VacancyDAO;
import entity.Category;
import entity.Company;
import entity.Vacancy;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class VacancyService {
    @EJB
    private VacancyDAO vacancyDAO;
    @EJB
    private CompanyDAO companyDAO;
    @EJB
    private CategoryDAO categoryDAO;

    public boolean addVacancy(String date, String salary, String requirement, String company, String category){
        try {
            Vacancy vacancy = new Vacancy(date, salary, requirement);
            Company newCompany = companyDAO.find(Long.valueOf(company));
            Category newCategory = categoryDAO.find(Long.valueOf(category));
            vacancy.setCompany(newCompany);
            vacancy.setCategory(newCategory);
            vacancyDAO.save(vacancy);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Company> getAllCompanies(){
        return util.Utils.toList(companyDAO.findAll());
    }

    public List<Category> getAllCategories(){
        return util.Utils.toList(categoryDAO.findAll());
    }
}
