package service;

import dao.CompanyDAO;
import entity.Company;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class CompanyService {
    @EJB
    private CompanyDAO companyDAO;


    public boolean addCompany(String email, String phone, String address){
        try {
            Company company = new Company(email, phone, address);
            companyDAO.save(company);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
