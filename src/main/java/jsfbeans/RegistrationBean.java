package jsfbeans;

import service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by alex on 12.11.16.
 */

@ManagedBean
@SessionScoped
public class RegistrationBean {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String card;
    private String address;

    @EJB
    private UserService userService;

    public String doRegistration(){
        if (userService.getByLogin(login) != null){
            return "info?message=This login already exists! Try another&faces-redirect=true";
        }

        boolean success = userService.doRegistration(login, password, name, surname, card, address);

        return success ? "login?faces-redirect=true" : "info?message=fail to register!&faces-redirect=true";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
