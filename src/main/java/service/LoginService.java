package service;

import dao.GroupDAO;
import dao.UserDAO;
import org.apache.log4j.Logger;
import util.Constants;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ignatenko on 19.11.16.
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

        // end hardcore!
        String group = groupDAO.find(login).getGroupName();
        switch (group){
            case "admin" :
                return "/admin/index?" + Constants.REDIRECT_PARAM;
            case "user" :
                return "/user/getCategory?" + Constants.REDIRECT_PARAM;
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
