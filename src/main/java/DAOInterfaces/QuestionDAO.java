package DAOInterfaces;

import Model.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {
    Question findOne(int id);

    List<Question> findAllByForum(int idForum);

    void save(Question entity);

    void updateQuestionTitle(int id, String title);

    void updateQuestionContent(int id, String content);

    void upvoteQuestion(int id);

    void downvoteQuestion(int id);

    void delete(int id);

    void deleteQuestion(int id) throws SQLException;

    void deleteAllByForum(int idForum) throws SQLException;
}
