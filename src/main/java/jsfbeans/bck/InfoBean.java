package jsfbeans.bck;

import dao.GroupDAO;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static util.Utils.getCurrentUser;

/**
 * Created by obalitskyi on 10/26/16.
 */
@ManagedBean
@RequestScoped
public class InfoBean {
    private String message;

    @EJB
    private UserService userService;

    @PostConstruct
    public void init(){
        message = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("message");
    }

    public String goToIndex(){
        String groupName = userService.getGroupName(getCurrentUser());
        return groupName + "/index?faces-redirect=true";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
