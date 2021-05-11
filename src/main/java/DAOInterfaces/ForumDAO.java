package DAOInterfaces;

import Model.Forum;

import java.util.List;

public interface ForumDAO {
    Forum findOne(int id);

    List<Forum> findAll();

    void save(Forum entity);

    void updateTitle(int id, String title);

    void updateTopic(int id, String topic);

    void delete(int id);
}
