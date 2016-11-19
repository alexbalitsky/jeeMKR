package jsfbeans;

import entity.Company;
import entity.Vacancy;
import service.CompanyService;
import util.Constants;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by ignatenko on 19.11.16.
 */

@ManagedBean
@ViewScoped
public class AddCompanyBean {
    private String email;
    private String phone;
    private String address;

    @EJB
    private CompanyService companyService;

    public String addCompany(){
        if (companyService.addCompany(email, phone, address)){
            return "index?" + Constants.REDIRECT_PARAM;
        }else {
            return "info?message=fail to add company" + Constants.REDIRECT_PARAM;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
