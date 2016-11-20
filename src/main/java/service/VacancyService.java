package service;

import dao.CategoryDAO;
import dao.CompanyDAO;
import dao.PositionDAO;
import dao.VacancyDAO;
import entity.Category;
import entity.Company;
import entity.Position;
import entity.Vacancy;
import org.apache.log4j.Logger;

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

    private final static Logger LOG = Logger.getLogger(CompanyService.class);

    @EJB
    private VacancyDAO vacancyDAO;
    @EJB
    private CompanyDAO companyDAO;
    @EJB
    private CategoryDAO categoryDAO;
    @EJB
    private PositionDAO positionDAO;

    public boolean save(String title, String date, String salary, String requirement, String companyID, String categoryID, String positionID) {
        try {
            Vacancy vacancy = new Vacancy(title, date, salary, requirement);
            //TODO check ID for not nulls!
            Company newCompany =  (companyID != null)? companyDAO.find(Long.valueOf(companyID)):null;
            Category newCategory =  (categoryID != null)? categoryDAO.find(Long.valueOf(categoryID)):null;
            Position newPosition =  (positionID != null)? positionDAO.find(Long.valueOf(positionID)):null;

            vacancy.setCompany(newCompany);
            vacancy.setCategory(newCategory);
            vacancy.setPosition(newPosition);
            vacancyDAO.save(vacancy);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String vacancyId, String title, String date, String salary, String requirement, String companyID, String categoryID, String positionID){
        try {
            Vacancy vacancy= vacancyDAO.find(Long.valueOf(vacancyId));
            vacancy.setTitle(title);
            vacancy.setDate(date);
            vacancy.setSalary(salary);
            vacancy.setRequirement(requirement);

            Company chosenCompany =  (companyID != null)? companyDAO.find(Long.valueOf(companyID)):null;
            Category chosenCategory =  (categoryID != null)? categoryDAO.find(Long.valueOf(categoryID)):null;
            Position chosenPosition =  (positionID != null)? positionDAO.find(Long.valueOf(positionID)):null;


            vacancy.setCompany(chosenCompany);
            vacancy.setCategory(chosenCategory);
            vacancy.setPosition(chosenPosition);



            vacancyDAO.update(vacancy);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse vacancy id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to update vacancy");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id){
        try {
            Vacancy vacancy = vacancyDAO.find(Long.valueOf(id));
            Category category = vacancy.getCategory();
            if (category != null){
                category.getVacancies().remove(vacancy);
            }
            Company company = vacancy.getCompany();
            if (company != null){
                company.getVacancies().remove(vacancy);
            }
            vacancyDAO.delete(vacancy);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse vacancy id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to delete vacancy");
            e.printStackTrace();
        }
        return false;
    }



    public List<Vacancy> getAllVacancies() {
        return util.Utils.toList(vacancyDAO.findAll());
    }

    public List<Company> getAllCompanies() {
        return util.Utils.toList(companyDAO.findAll());
    }
    public List<Category> getAllCategories() {
        return util.Utils.toList(categoryDAO.findAll());
    }

    public List<Position> getAllPositions() {
        return util.Utils.toList(positionDAO.findAll());
    }
}
