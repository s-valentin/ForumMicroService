package Repository;

import Model.Comment;
import Model.ForumList;
import Model.Question;

import java.sql.*;

public class QuestionRepository implements Repository<Question>{
    private Connection getConnection() {

        String url = System.getProperty("spring.datasource.url");
        String username = System.getProperty("spring.datasource.username");
        String password = System.getProperty("spring.datasource.password");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public Question findOne(int id) {
        Question question = null;

        try (Connection connection = getConnection()) {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM comment WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            question = new Question(rs.getString("title"),rs.getString("content"));
            question.setId(rs.getInt("id"));
            question.setNumberOfLikes(rs.getInt("likes"));
            question.setNumberOfDislikes(rs.getInt("dislikes"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return question;
    }

    @Override
    public Question findAll() {
        return null;
    }

    @Override
    public boolean save(Question entity) {
        try (Connection connection = getConnection()) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO question VALUES (NULL, ?, ?, ?, ?)");
            st.setInt(1, entity.getIdForum());
            st.setInt(2, entity.getNumberOfDislikes());
            st.setInt(3, entity.getNumberOfDislikes());
            st.setString(4, entity.getContent());
            st.executeUpdate();
            System.out.println("A question has been ADDED successfully in the table");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Question entity) {

        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
