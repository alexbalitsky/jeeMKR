package jsfbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by ignatenko on 20.11.16.
 */

@ManagedBean
@ViewScoped
public class RequestBean {
    private String text;

    public String send(){

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
