package jsfbeans;

import com.sun.org.apache.xpath.internal.operations.Bool;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 13.11.16.
 */

@ManagedBean
@ViewScoped
public class CartBean {
    @EJB
    private UserService userService;

    private Map<String, Boolean> checked = new HashMap<>();
    private Map<String, String> count = new HashMap<>();

    public String buy(){
        boolean success = userService.buy(checked, count);
        return success ? "getCatalogs?&faces-redirect=true" : "/info?message=something wrong&faces-redirect=true";
    }

    public Map<String, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<String, Boolean> checked) {
        this.checked = checked;
    }

    public Map<String, String> getCount() {
        return count;
    }

    public void setCount(Map<String, String> count) {
        this.count = count;
    }
}
