package dao;

import entity.Vacancy;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class VacancyDAO extends AbstractDAO<Vacancy> {
    public VacancyDAO() {
        super(Vacancy.class);
    }
}
