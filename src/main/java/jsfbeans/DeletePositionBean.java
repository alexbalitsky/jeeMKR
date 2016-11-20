package jsfbeans;


import service.PositionService;
import util.Constants;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 * Created by ignatenko on 19.11.16.
 */

@ManagedBean
@RequestScoped
public class DeletePositionBean {
    @EJB
    private PositionService positionService;

    public String delete(String id){
        boolean success = positionService.delete(id);
        return success ? "getPosition?faces-redirect=true" :
                "info?message=fail to delete position!&faces-redirect=true";
    }
}
