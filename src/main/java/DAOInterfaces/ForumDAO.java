package DAOInterfaces;

import Model.Forum;

import java.util.List;

public interface ForumDAO {
    Forum findOne(int id);

    List<Forum> findAll();

    void save(Forum entity);

    boolean updateTitle(int id, String title);

    boolean updateTopic(int id, String topic);

    void delete(int id);
}
