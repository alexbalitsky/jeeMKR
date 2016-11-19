package dao;

import entity.Company;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by ignatenko on 19.11.16.
 */
@Stateless
@Local
public class CompanyDAO extends AbstractDAO<Company> {
    public CompanyDAO(){
        super(Company.class);
    }
}
