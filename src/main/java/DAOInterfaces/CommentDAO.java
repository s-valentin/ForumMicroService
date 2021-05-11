package DAOInterfaces;

import Model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {
    Comment findOne(int id);

    List<Comment> findAllByQuestion(int idQuestion);

    void save(Comment entity);

    void updateContent(int id, String newContent);

    void upvoteComment(int id);

    void downvoteComment(int id);

    void delete(int id);

    void deleteAllByQuestion(int idQuestion) throws SQLException;
}
