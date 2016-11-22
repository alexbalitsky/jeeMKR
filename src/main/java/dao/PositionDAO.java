package dao;

import entity.Position;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class PositionDAO extends AbstractDAO<Position> {
    public PositionDAO() {
        super(Position.class);
    }
}
