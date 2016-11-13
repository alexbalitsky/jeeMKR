package service;

import dao.GroupDAO;
import dao.UserDAO;
import entity.User;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by alex on 13.11.16.
 */

@Stateless
@Local
public class LoginService {
    @EJB
    private UserDAO userDAO;
    @EJB
    private GroupDAO groupDAO;


    public String doAuthorization(String login, String password){
        try {
            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).
                    login(login, password);
        } catch (ServletException e) {
            e.printStackTrace();
            return "info?message=forbidden&faces-redirect=true";
        }

        String group = groupDAO.find(login).getGroupName();
        switch (group){
            case "admin" :
                return "admin/getCatalogs?faces-redirect=true";
            case "user" :
                return "user/getCatalogs?faces-redirect=true";
        }

        return "info?message=unknown problem&faces-redirect=true";
    }

    public String logout(){
        try {
            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).
                    logout();
        } catch (ServletException e) {
            e.printStackTrace();
            return "/info?message=something went wrong&faces-redirect=true";
        }

        return "/login?faces-redirect=true";
    }

}
