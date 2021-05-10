package DAOInterfaces;

import Model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {
    Comment findOne(int id);

    List<Comment> findAllByQuestion(int idQuestion);

    boolean save(Comment entity);

    boolean update(Comment entity);

    void updateContent(int id, String newContent);

    void upvoteComment(int id);

    void downvoteComment(int id);

    boolean delete(int id);

    boolean deleteAllByQuestion(int idQuestion) throws SQLException;
}
