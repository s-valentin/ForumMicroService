package Repository;

import ConnectionToDB.ConnectionSingleton;
import DAOInterfaces.QuestionDAO;
import Model.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository implements QuestionDAO {

    private final Connection connection;

    public QuestionRepository() {
        connection = ConnectionSingleton.getConnection();
    }

    // * Aceasta metoda returneaza o singura intrebare, cu toate comentariile ei.
    @Override
    public Question findOne(int id) {

        // * Creez o intrebare pe care o returnez la final
        Question question = null;

        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM Questions WHERE id = ?")) {
            // * Pregatesc interogarea pentru baza de date
            st.setInt(1, id);

            // * Execut interogarea
            ResultSet rs = st.executeQuery();
            rs.next();

            // * Instantiez intrebarea si ii dau parametrii din baza de date
            question = new Question(rs.getString("title"),
                    rs.getString("content"));
            question.setId(id);
            question.setNumberOfLikes(rs.getInt("likes"));
            question.setNumberOfDislikes(rs.getInt("dislikes"));
            question.setIdForum(rs.getInt("idForum"));

            // * Ma folosesc de CommentRepository pentru a scoate toate comentariile acestei intrebari
            CommentRepository comments = new CommentRepository();
            question.setComments(comments.findAllByQuestion(id));

            // * Setez numarul de comentarii dupa lista de mai sus
            question.setNumberOfComments(question.getComments().size());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    // * Aceasta metoda returneaza toate intrebarile unui forum
    public List<Question> findAllByForum(int idForum) {

        // * Creez lista pe care o returnez la final
        List<Question> questions = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM Questions WHERE idForum = ?")) {
            // * Pregatesc interogarea pentru baza de date
            st.setInt(1, idForum);

            // * Execut interogarea
            ResultSet rs = st.executeQuery();

            // * Parcurg toate randurile selectate
            while (rs.next()) {

                // * Creez o noua intrebare careia ii dau valori din baza de date
                Question question = new Question(rs.getString("title"), rs.getString("content"));
                question.setId(rs.getInt("id"));
                question.setNumberOfLikes(rs.getInt("likes"));
                question.setNumberOfDislikes(rs.getInt("dislikes"));
                question.setIdForum(idForum);

                // * Adaug toate comentariile acelei intrebari
                CommentRepository commentRepository = new CommentRepository();
                question.setComments(commentRepository.findAllByQuestion(question.getId()));
                question.setNumberOfComments(question.getComments().size());

                // * Adaug intrebarea creata in lista de intrebari pe care o returnez
                questions.add(question);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    // * Aceasta metoda adauga o intrebare in baza de date
    @Override
    public void save(Question entity) {
        try (PreparedStatement checkForum = connection.prepareStatement("SELECT * FROM Forums WHERE id = ? ")) {
            // * Verific daca forumul in care adaug intrebarea exista.
            checkForum.setInt(1, entity.getIdForum());
            ResultSet rs = checkForum.executeQuery();
            boolean val = rs.next();
            // * Returnez false daca interogarea de mai sus imi returneaza 0 randuri
            if (!val)
                return;

            // * Pregatesc statement-ul de inserare in baza de date
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Questions VALUES (NULL, ?, ?, ?, ?, ?)");
            statement.setInt(1, entity.getIdForum());
            statement.setString(2, entity.getTitle());
            statement.setString(3, entity.getContent());
            statement.setInt(4, 0);
            statement.setInt(5, 0);

            // * Adaug intrebarea in baza de date si returnez true
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestionTitle(int id, String title) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE questions SET title = ? WHERE id = ?")) {
            st.setString(1, title);
            st.setInt(2, id);
            st.executeUpdate();
            System.out.println("A question's title has been updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestionContent(int id, String content) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE questions SET content = ? WHERE id = ?")) {
            st.setString(1, content);
            st.setInt(2, id);
            st.executeUpdate();
            System.out.println("A question's content has been updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upvoteQuestion(int id) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE questions SET likes = likes + 1 WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A question has been upvoted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downvoteQuestion(int id) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE questions SET dislikes = dislikes + 1 WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A question has been downvoted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // * Aceasta metoda sterge o intrebare si toate comentariile ei
    // * Face conexiunea cu baza de date
    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionSingleton.getConnection()) {
            deleteQuestion(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // * Aici se intampla stergerea propriu zisa
    public void deleteQuestion(int id) throws SQLException {
        PreparedStatement checkQuestion = connection.prepareStatement("SELECT * FROM Questions WHERE id=?");
        checkQuestion.setInt(1, id);

        ResultSet rs = checkQuestion.executeQuery();
        boolean val = rs.next();
        if (!val)
            return;

        PreparedStatement statement = connection.prepareStatement("DELETE FROM Questions WHERE id=?");
        statement.setInt(1, id);
        statement.executeUpdate();

        // * Ma folosesc de functia din commentRepository pentru a sterge toate comentariile
        CommentRepository commentRepository = new CommentRepository();
        commentRepository.deleteAllByQuestion(id);

    }

    // * Aceasta metoda sterge toate intrebarile unui forum
    public void deleteAllByForum(int idForum) throws SQLException {

        // * Interoghez baza de date pentru toate intrebarile unui forum
        PreparedStatement databaseQuestion = connection.prepareStatement("SELECT * FROM Questions WHERE idForum = ?");
        databaseQuestion.setInt(1, idForum);

        ResultSet rs = databaseQuestion.executeQuery();

        // * Pentru fiecare intrebare gasita cu idForum-ul specificat, o sterg apeland functia de mai sus.
        while (rs.next()) {
            deleteQuestion(rs.getInt("id"));
        }

    }
}
