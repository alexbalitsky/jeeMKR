package jsfbeans;

import entity.User;
import entity.Vacancy;
import org.apache.log4j.Logger;
import service.UserService;
import service.VacancyService;
import util.Constants;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ignatenko on 20.11.16.
 */

@ManagedBean
@ViewScoped
public class RequestBean {
    private String text;
    private String vacancyId;
    private String userId;

    private Vacancy vacancy;

    private User user;

    @EJB
    private VacancyService vacancyService;

    @EJB
    private UserService userService;

    private final static Logger LOG = Logger.getLogger(RequestBean.class);

    @PostConstruct
    public void init(){
        try {
            vacancyId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("vacancy_id");
            vacancy = vacancyService.get(vacancyId);
            userId = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
            user = userService.getByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        } catch (Exception e){
            LOG.error("cant` handle request page", e);
        }
    }

    public String send(){
        try {
            //TODO check if this user has already send vacancy for it
            //add to Table;
            if (!vacancyService.isVacancyhaveUser(vacancy, user)){
                //Set<Vacancy> vacanciesToAdd = new HashSet<>();

                userService.addVacancy(userId, vacancy);
            }
            //send email
            vacancyService.sendEmailWithGmailSMTP(vacancy.getCompany().getEmail(), user.getEmail(), vacancy.getCompany().getName());
            return "getCategory?" + Constants.REDIRECT_PARAM;
        }catch(Exception e){
            LOG.warn("fail to send email to company", e);
        }
       return  "info?message=fail to send email to company!&faces-redirect=true";
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
