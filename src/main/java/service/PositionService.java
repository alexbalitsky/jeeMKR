package service;

import dao.PositionDAO;
import entity.Position;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class PositionService {

    private final static Logger LOG = Logger.getLogger(PositionService.class);

    @EJB
    private PositionDAO positionDAO;


    public boolean save(String name, String description){
        Position position= new Position(name, description);
        try {
            positionDAO.save(position);
            LOG.info("successfully saved position");
            return true;
        }catch (Exception e){
            LOG.error("fail to save position");
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String id, String name, String description){
        try {
            Position position = positionDAO.find(Long.valueOf(id));
            position.setName(name);
            position.setDescription(description);
            positionDAO.update(position);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse position id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to update position");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id){
        try {
            positionDAO.delete(Long.valueOf(id));
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse position id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to delete category");
            e.printStackTrace();
        }
        return false;
    }

    public Set<Position> getAll(){
        return positionDAO.findAll();
    }



}
