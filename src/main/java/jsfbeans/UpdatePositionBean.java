package jsfbeans;

import service.PositionService;

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
public class UpdatePositionBean {
    private String name;
    private String description;

    @EJB
    private PositionService positionService;

    private String positionId;

    @PostConstruct
    public void init(){
        positionId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("position");
    }

    public String update(){
        boolean success = positionService.update(positionId, name, description);
        return success ? "getPosition?faces-redirect=true" :
                "info?message=fail to update position!&faces-redirect=true";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }
}
