package service;

import dao.CompanyDAO;
import entity.Company;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Set;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class CompanyService {

    private final static Logger LOG = Logger.getLogger(CompanyService.class);

    @EJB
    private CompanyDAO companyDAO;



    public boolean save(String name, String email, String phone, String address){
        Company company = new Company(name,email,phone, address);
        try {
            LOG.info("successfully saved company");
            companyDAO.save(company);
            return true;
        }catch (Exception e){
            LOG.error("fail to save company");
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String id,String name, String email, String phone, String address){
        try {
            Company company = companyDAO.find(Long.valueOf(id));
            company.setName(name);
            company.setEmail(email);
            company.setPhone(phone);
            company.setAddress(address);
            companyDAO.update(company);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse company id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to update company");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id){
        try {
            companyDAO.delete(Long.valueOf(id));
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse company id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to delete category");
            e.printStackTrace();
        }
        return false;
    }

    public Set<Company> getAll(){
        return companyDAO.findAll();
    }
}
