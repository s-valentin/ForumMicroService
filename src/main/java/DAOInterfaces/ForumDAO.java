package DAOInterfaces;

import Model.Forum;

import java.util.List;

public interface ForumDAO {
    Forum findOne(int id);

    List<Forum> findAll();

    boolean save(Forum entity);

    boolean update(Forum entity);

    boolean delete(int id);
}
