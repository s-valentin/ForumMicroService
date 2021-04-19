package Repository;

import Model.Entity;
import Model.ForumList;

import java.util.List;

public interface Repository<E extends Entity> {

    //principiile CRUD.

    public E findOne(int id); // read

    public E findAll(); // read

    public E save(E entity); // create

    public void update(E entity); // update

    public void delete(int id); // delete

}
