package Repository;

import ConnectionToDB.ConnectionSingleton;
import DAOInterfaces.CommentDAO;
import Model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository implements CommentDAO {

    public Connection connection;

    public CommentRepository() throws SQLException {
        connection = ConnectionSingleton.getConnection();
    }

    // * Aceasta metoda returneaza un singur comentariu specificat de id
    @Override
    public Comment findOne(int id) {
        // * creez un comentariu pe care il returnez
        Comment comment = null;

        // * interoghez tabela comment pentru acel id
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM comment WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            // * incarc obiectul comment cu informatiile interogate
            comment = new Comment(rs.getString("content"));
            comment.setId(rs.getInt("id"));
            comment.setNumberOfLikes(rs.getInt("likes"));
            comment.setNumberOfDislikes(rs.getInt("dislikes"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // * returnez comentariul interogat
        return comment;
    }

    // * Aceasta metoda returneaza toate comentariile de la o anumita intrebare
    // ! De verificat daca intrebarea cu acel id exista pentru a putea fi adaugat un comentariu
    @Override
    public List<Comment> findAllByQuestion(int idQuestion) {
        // * Creez o lista de comentarii pe care o voi returna
        List<Comment> commentList = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM comment WHERE idQuestion = ?")) {
            // * Interoghez tabela comments
            st.setInt(1, idQuestion);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // * Adaug interogarile in lista de comentarii
                Comment comment = new Comment(rs.getString("content"));
                comment.setId(rs.getInt("id"));
                comment.setNumberOfLikes(rs.getInt("likes"));
                comment.setNumberOfDislikes(rs.getInt("dislikes"));

                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // * returnez lista
        return commentList;
    }

    // * aceasta metoda insereaza in tabel un comment
    @Override
    public boolean save(Comment entity) {
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO comment VALUES (NULL, ?, ?, ?, ?)")) {
            st.setInt(1, entity.getIdQuestion());
            st.setInt(2, entity.getNumberOfDislikes());
            st.setInt(3, entity.getNumberOfDislikes());
            st.setString(4, entity.getContent());
            st.executeUpdate();
            System.out.println("A comment has been ADDED successfully in the table");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // * aceasta metoda face update in tabela comment
    // ! de modificat
    @Override
    public boolean update(Comment entity) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE comment SET likes=?, dislikes=?, content=?")) {
            st.setInt(1, entity.getNumberOfDislikes());
            st.setInt(2, entity.getNumberOfDislikes());
            st.setString(3, entity.getContent());
            st.executeUpdate();
            System.out.println("A comment has been UPDATED successfully in the table");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateContent(int id, String newContent) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE comment (content) SET content = ? WHERE id = ?")) {
            st.setString(1, newContent);
            st.setInt(2, id);
            st.executeUpdate();
            System.out.println("A comment's content has been updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upvoteComment(int id) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE comment SET likes = likes + 1 WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A comment has been upvoted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downvoteComment(int id) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE comment SET dislikes = dislikes + 1 WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A comment has been downvoted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // * aceasta metoda sterge un comentariu din tabela
    // ! de implementat request-ul
    @Override
    public boolean delete(int id) {
        try (PreparedStatement st = connection.prepareStatement("DELETE FROM comment WHERE id=?")) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A comment has been DELETED successfully from the table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // * Aceasta metoda sterge toate comentariile unei intrebari
    @Override
    public boolean deleteAllByQuestion(int idQuestion){

        try (PreparedStatement st = connection.prepareStatement("DELETE FROM comment WHERE idQuestion=?")) {
            st.setInt(1, idQuestion);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}
