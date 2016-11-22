package jsfbeans;



import service.CompanyService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ignatenko on 19.11.16.
 */
@ManagedBean
@ViewScoped
public class UpdateCompanyBean {
    private String name;
    private String email;
    private String address;
    private String phone;

    @EJB
    private CompanyService companyService;

    private String compnayId;

    @PostConstruct
    public void init(){
        compnayId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("company");
    }

    public String update(){
        boolean success = companyService.update(compnayId,name,email,phone,address);
        return success ? "getCompany?faces-redirect=true" :
                "info?message=fail to update company!&faces-redirect=true";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompnayId() {
        return compnayId;
    }

    public void setCompnayId(String compnayId) {
        this.compnayId = compnayId;
    }
}
