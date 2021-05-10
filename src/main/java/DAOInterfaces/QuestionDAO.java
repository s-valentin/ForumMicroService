package DAOInterfaces;

import Model.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {
    Question findOne(int id);

    List<Question> findAllByForum(int idForum);

    boolean save(Question entity);

    boolean update(Question entity);

    boolean delete(int id);

    boolean deleteQuestion(int id) throws SQLException;

    boolean deleteAllByForum(int idForum) throws SQLException;
}
