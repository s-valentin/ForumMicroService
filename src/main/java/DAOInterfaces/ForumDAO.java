package DAOInterfaces;

import Model.Forum;

import java.util.List;

public interface ForumDAO {
    Forum findOne(int id);

    List<Forum> findAll();

    void save(Forum entity);

    void update(int id, String title, String topic);

//    void updateTopic(int id, String topic);

    void delete(int id);
}
