package jsfbeans;



import entity.Company;
import entity.Position;
import entity.Vacancy;
import service.PositionService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ignatenko on 19.11.16.
 */
@ManagedBean
@RequestScoped
public class GetPositionBean {
    @EJB
    private PositionService positionService;


    private List<Position> positions= new ArrayList<>();


    @PostConstruct
    public void init(){
        positions.addAll(positionService.getAll());
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}

