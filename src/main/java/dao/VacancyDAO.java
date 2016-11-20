package dao;

import entity.Category;
import entity.Company;
import entity.Vacancy;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.*;
import java.sql.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class VacancyDAO extends AbstractDAO<Vacancy> {
    public VacancyDAO() {
        super(Vacancy.class);
    }

    public List<Vacancy> getVacanciesWithoutCategories(){
        Query query = em.createQuery("from Vacancy c where c.category is null ");
        return query.getResultList();
    }

    public List<Vacancy> getVacanciesWithoutCategories(Category entity){
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Vacancy> vacancyRoot = cq.from(Vacancy.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        Expression<Category> category = vacancyRoot.get("category");

        if (entity != null) {
            predicates.add(qb.or(qb.equal(category, entity), qb.isNull(category)));
        }
        cq.select(vacancyRoot)
                .where(predicates.toArray(new Predicate[]{}));
        List<Vacancy> vacancies = em.createQuery(cq).getResultList();

        return vacancies;
    }


    public List<Vacancy> getVacanciesWithoutCompanies(){
        Query query = em.createQuery("from Vacancy c where c.company is null ");
        return query.getResultList();
    }

    public List<Vacancy> getVacanciesWithoutCompanies(Company entity){
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Vacancy> vacancyRoot = cq.from(Vacancy.class);

        List<Predicate> predicates = new ArrayList<Predicate>();

        Expression<Company> company = vacancyRoot.get("company");

        if (entity != null) {
            predicates.add(qb.or(qb.equal(company, entity), qb.isNull(company)));
        }
        cq.select(vacancyRoot)
                .where(predicates.toArray(new Predicate[]{}));
        List<Vacancy> vacancies = em.createQuery(cq).getResultList();

        return vacancies;
    }

}
