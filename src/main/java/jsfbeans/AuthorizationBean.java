package jsfbeans;

import service.LoginService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by alex on 12.11.16.
 */
@ManagedBean
@SessionScoped
public class AuthorizationBean {
    private String login;
    private String password;

    @EJB
    private LoginService loginService;

    public String doLogin(){
        return loginService.doAuthorization(login, password);
    }

    public String doLogout(){
        return loginService.logout();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
