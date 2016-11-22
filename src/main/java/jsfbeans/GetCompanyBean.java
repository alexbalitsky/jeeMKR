package jsfbeans;



import entity.Company;
import entity.Vacancy;
import service.CompanyService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ignatenko on 19.11.16.
 */
@ManagedBean
@RequestScoped
public class GetCompanyBean {
    @EJB
    private CompanyService companyService;


    private List<Company> companies= new ArrayList<>();
    private Map<Long, List<Vacancy>> map = new HashMap();

    @PostConstruct
    public void init(){
        companies.addAll(companyService.getAll());
        for (Company company: companies){
            map.put(company.getId(), new ArrayList<Vacancy>(company.getVacancies()));
        }
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies= companies;
    }

    public Map<Long, List<Vacancy>> getMap() {
        return map;
    }

    public void setMap(Map<Long, List<Vacancy>> map) {
        this.map = map;
    }
}

