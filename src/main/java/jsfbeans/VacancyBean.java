package jsfbeans;

import entity.Vacancy;
import service.VacancyService;
import util.Constants;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Created by ignatenko on 20.11.16.
 */

@ManagedBean
@ViewScoped
public class VacancyBean {
    private String id;
    private Vacancy vacancy;

    @EJB
    private VacancyService vacancyService;

    @PostConstruct
    public void init(){
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("vacancy_id");
        vacancy = vacancyService.get(id);
    }

    public String sendRequest(){
        vacancyService.sendBroadcastMessage();
        return "request?" + Constants.REDIRECT_PARAM;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}
