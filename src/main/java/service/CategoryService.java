package service;

import dao.CategoryDAO;
import dao.VacancyDAO;
import entity.Category;
import entity.Vacancy;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class CategoryService {

    private final static Logger LOG = Logger.getLogger(CategoryService.class);

    @EJB
    private CategoryDAO categoryDAO;
    @EJB
    private VacancyDAO vacancyDAO;

    public boolean save(String title){
        Category category = new Category(title);
        try {
            LOG.info("successfully saved category");
            categoryDAO.save(category);
            return true;
        }catch (Exception e){
            LOG.error("fail to save category");
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String id, String title){
        try {
            Category category = categoryDAO.find(Long.valueOf(id));
            category.setTitle(title);
            categoryDAO.update(category);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse category id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to update category");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id){
        try {
            Category category = categoryDAO.find(Long.valueOf(id));
            Set<Vacancy> vacancies = category.getVacancies();
            for (Vacancy vacancy : vacancies){
                vacancyDAO.delete(vacancy);
            }
            categoryDAO.delete(category);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse category id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to delete category");
            e.printStackTrace();
        }
        return false;
    }

    public Set<Category> getAll(){
        return categoryDAO.findAll();
    }

    public List<Vacancy> getVacanciesByCategoryId(String id){
        try {
            return util.Utils.toList(categoryDAO.find(Long.valueOf(id)).getVacancies());
        }catch (Exception e){
            LOG.error("fail to get category by id");
        }
        return null;
    }

//    public Set<Vacancy> getVacanciesByIds(List<String> ids){
//        Set<Vacancy> result;
//        try {
//            result = vacancyDAO.getByIDs(ids);
//        }catch (NumberFormatException nfe){
//            throw new RuntimeException("id is not Long!", nfe);
//        }catch (NullPointerException npe){
//            throw new RuntimeException("there is no object with such id!", npe);
//        }
//
//        return result;
//    }


//    public Set<Vacancy> getVacanciesWithoutCategories(){
//        return new HashSet<>(vacancyDAO.getVacanciesWithoutCategories());
//    }

//    public Set<Vacancy> getVacanciesWithoutCategories(String categoryId){
//        Set<Vacancy> result;
//        try {
//            Category category= categoryDAO.find(Long.valueOf(categoryId));
//            result = new HashSet<>(vacancyDAO.getVacanciesWithoutCategories(category));
//        }
//        catch (NumberFormatException nfe){
//            throw new RuntimeException("id is not Long!", nfe);
//        }catch (NullPointerException npe){
//            throw new RuntimeException("there is no object with such id!", npe);
//        }
//        return result;
//    }


}
