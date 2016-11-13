package jsfbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Created by obalitskyi on 10/26/16.
 */
@ManagedBean
@RequestScoped
public class InfoBean {
    private String message;

    @PostConstruct
    public void init(){
        message = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("message");
    }

    public String goToIndex(){
        return "index?faces-redirect=true";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
