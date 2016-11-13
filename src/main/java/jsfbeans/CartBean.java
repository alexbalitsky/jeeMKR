package jsfbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 13.11.16.
 */

@ManagedBean
@SessionScoped
public class CartBean {
    private Map<String, Boolean> checked = new HashMap<>();

    public String buy(){
        return "";
    }

    public Map<String, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<String, Boolean> checked) {
        this.checked = checked;
    }
}
