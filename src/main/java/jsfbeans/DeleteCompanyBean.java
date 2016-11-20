package jsfbeans;

import service.CompanyService;
import util.Constants;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 * Created by ignatenko on 19.11.16.
 */

@ManagedBean
@RequestScoped
public class DeleteCompanyBean {
    @EJB
    private CompanyService companyService;

    public String delete(String id){
        boolean success = companyService.delete(id);
        return success ? "getCompany?faces-redirect=true" :
                "info?message=fail to delete company!&faces-redirect=true";
    }
}
