package Repository;

import Model.Entity;

import java.util.List;

public interface Repository<E extends Entity> {

    public E findOne(int id);

    public List<E> findAll();

    public E save(E entity);

    public void update(E entity);

    public void delete(int id);

}
