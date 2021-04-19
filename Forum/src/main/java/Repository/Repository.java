package Repository;

import Model.Entity;
import Model.ForumList;

import java.util.List;

public interface Repository<E extends Entity> {

    public E findOne(int id);

    public ForumList findAll();

    public E save(E entity);

    public void update(E entity);

    public void delete(int id);

}
