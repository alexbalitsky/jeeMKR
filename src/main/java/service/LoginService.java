package service;

import dao.GroupDAO;
import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;

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
    Logger LOG = Logger.getLogger(LoginService.class);

    @EJB
    private UserDAO userDAO;
    @EJB
    private GroupDAO groupDAO;


    public String doAuthorization(String login, String password){
        try {
            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).
                    login(login, password);
            LOG.info("Successfully authorized user: " + login);
        } catch (ServletException e) {
            LOG.error("Fail to authorize user: " + login);
            e.printStackTrace();
            return "/info?message=forbidden";
        }

        String group = groupDAO.find(login).getGroupName();
        switch (group){
            case "admin" :
                return "/admin/getCatalogs";
            case "user" :
                return "/user/getCatalogs";
        }

        return "/info?message=unknown problem";
    }

    public String logout(){
        try {
            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).
                    logout();
            LOG.info("Logged out user: " + FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        } catch (ServletException e) {
            e.printStackTrace();
            return "/info?message=something went wrong";
        }

        return "/login";
    }

}
