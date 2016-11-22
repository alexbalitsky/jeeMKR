package jsfbeans;


import service.PositionService;
import util.Constants;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by ignatenko on 19.11.16.
 */

@ManagedBean
@ViewScoped
public class AddPositionBean {
    private String name;
    private String description;


    @EJB
    private PositionService positionService;

    public String save(){
        if (positionService.save(name, description)){
            return "index?" + Constants.REDIRECT_PARAM;
        }else {
            return "info?message=fail to add position" + Constants.REDIRECT_PARAM;
        }
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
}
