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
    private String vacancy_id;

    private String vacancy_id_request;

    private Vacancy vacancy;

    @EJB
    private VacancyService vacancyService;

    @PostConstruct
    public void init(){
        vacancy_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("vacancy_id");
        vacancy = vacancyService.get(vacancy_id);
    }

    public String sendRequest(){
        //vacancyService.sendBroadcastMessage();
        return "request?" + Constants.REDIRECT_PARAM+"&"+"vacancy_id="+vacancy_id;
    }

    public String getVacancy_id() {
        return vacancy_id;
    }

    public void setVacancy_id(String vacancy_id) {
        this.vacancy_id = vacancy_id;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public String getVacancy_id_request() {
        return vacancy_id_request;
    }

    public void setVacancy_id_request(String vacancy_id_request) {
        this.vacancy_id_request = vacancy_id_request;
    }
}
