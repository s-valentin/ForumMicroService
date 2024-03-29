package Repository;

import ConnectionToDB.ConnectionSingleton;
import DAOInterfaces.ForumDAO;
import Model.Forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumRepository implements ForumDAO {

    // * Aceasta metoda returneaza un forum, cu toate intrebarile lui.
    @Override
    public Forum findOne(int id) {
        // * Creez un forum pe care il returnez la final
        Forum forum = null;
        Connection connection = ConnectionSingleton.getConnection();

        try (PreparedStatement st = connection.prepareStatement("select * from heroku_f7e69bbf73fbe2a.forums where id = ?")) {

            // * Pregatesc interogarea pentru baza de date

            st.setInt(1, id);

            // * Execut interogarea
            ResultSet rs = st.executeQuery();
            rs.next();

            // * Instantiez forumul cu valori din baza de date
            forum = new Forum(rs.getString("title"), rs.getString("topic"));
            forum.setId(rs.getInt("id"));

            // * Ma folosesc de QuestionRepository pentru a scoate toate intrebarile acestui forum
            QuestionRepository questionRepository = new QuestionRepository();
            forum.setQuestions(questionRepository.findAllByForum(id));

            // * Setez numarul de intrebari dupa marimea listei de mai sus
            forum.setNumberOfQuestions(forum.getQuestions().size());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {connection.close();} catch (SQLException e) {e.printStackTrace();}
        }

        return forum;

    }

    @Override
    public List<Forum> findAll() {

        Connection connection = ConnectionSingleton.getConnection();
        List<Forum> forums = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM heroku_f7e69bbf73fbe2a.forums")) {
            // * Pregatesc interogarea pentru baza de date


            // * Execut interogarea
            ResultSet rs = st.executeQuery();

            // * Trec prin toate randurile selectate
            while (rs.next()) {

                // * Creez un forum caruia ii dau valori din baza de date
                Forum forum = new Forum(rs.getString("title"), rs.getString("topic"));
                forum.setId(rs.getInt("id"));

                // * Adaug toate intrebarile acelui forum
//                QuestionRepository questions = new QuestionRepository();
//                forum.setQuestions(questions.findAllByForum(forum.getId()));
//                forum.setNumberOfQuestions(forum.getQuestions().size());

                // * Adaug forumul creat in lista finala.
                forums.add(forum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return forums;

    }

    @Override
    public void save(Forum entity) {
        Connection connection = ConnectionSingleton.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO heroku_f7e69bbf73fbe2a.forums Values(NULL, ?, ?)")) {

            // * Pregatesc statement-ul de inserare in baza de date

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getTopic());

            // * Adaug forumul in baza de date
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, String name, String topic) {
        Connection connection = ConnectionSingleton.getConnection();
        try (PreparedStatement st = connection.prepareStatement("UPDATE heroku_f7e69bbf73fbe2a.forums SET title = ?, topic = ? WHERE id = ?")) {
            st.setString(1, name);
            st.setString(2, topic);
            st.setInt(3, id);
            st.executeUpdate();
            System.out.println("A forums' title has been updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // * Aceasta metoda sterge un forum cu toate intrebarile lui
    @Override
    public void delete(int id) {
        Connection connection = ConnectionSingleton.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM heroku_f7e69bbf73fbe2a.forums WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();

            // * Aici ma folosesc de functia din QuestionRepository pentru a sterge toate intrebarile si comentariile lor
            QuestionRepository questionRepository = new QuestionRepository();
            questionRepository.deleteAllByForum(id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
