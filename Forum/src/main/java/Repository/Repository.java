package Repository;

import Model.Entity;
import Model.ForumList;

import java.util.List;

public interface Repository<E extends Entity> {

    //principiile CRUD.

    public E findOne(int id); // read

    public E findAll(); // read

    public boolean save(E entity); // create

    public boolean update(E entity); // update

    public boolean delete(int id); // delete

}
