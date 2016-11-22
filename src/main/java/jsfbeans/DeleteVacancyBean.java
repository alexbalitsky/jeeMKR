package jsfbeans;

import entity.Vacancy;
import service.VacancyService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by ignatenko on 20.11.16.
 */
@ManagedBean
@RequestScoped
public class DeleteVacancyBean {
    @EJB
    private VacancyService vacancyService;

    public String delete(String id){
        boolean success = vacancyService.delete(id);
        return success ? "getVacancy?faces-redirect=true" :
                "info?message=fail to delete vacancy!&faces-redirect=true";
    }
}