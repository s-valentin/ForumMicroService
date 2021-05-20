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

    @Override
    public Comment findOne(int id) {
        // * creez un comentariu pe care il returnez
        Comment comment = null;
        Connection connection = ConnectionSingleton.getConnection();

        // * interoghez tabela comment pentru acel id
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM heroku_f7e69bbf73fbe2a.comment WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            // * incarc obiectul comment cu informatiile interogate
            comment = new Comment(rs.getString("content"));
            comment.setId(rs.getInt("id"));
            comment.setIdQuestion(rs.getInt("idQuestion"));
            comment.setNumberOfLikes(rs.getInt("likes"));
            comment.setNumberOfDislikes(rs.getInt("dislikes"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // * returnez comentariul interogat
        return comment;
    }
    // * Aceasta metoda returneaza un singur comentariu specificat de id

    // * Aceasta metoda returneaza toate comentariile de la o anumita intrebare
    // ! De verificat daca intrebarea cu acel id exista pentru a putea fi adaugat un comentariu
    @Override
    public List<Comment> findAllByQuestion(int idQuestion) {
        // * Creez o lista de comentarii pe care o voi returna
        List<Comment> commentList = new ArrayList<>();

        Connection connection = ConnectionSingleton.getConnection();
        ResultSet resultSet;
        PreparedStatement st;

        try {
            st = connection.prepareStatement("SELECT * FROM heroku_f7e69bbf73fbe2a.comment WHERE idQuestion = ?");
            // * Interoghez tabela comments
            st.setInt(1, idQuestion);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                // * Adaug interogarile in lista de comentarii
                Comment comment = new Comment(resultSet.getString("content"));
                comment.setId(resultSet.getInt("id"));
                comment.setIdQuestion(resultSet.getInt("idQuestion"));
                comment.setNumberOfLikes(resultSet.getInt("likes"));
                comment.setNumberOfDislikes(resultSet.getInt("dislikes"));

                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {connection.close();} catch (SQLException e) {e.printStackTrace();}
//            try {resultSet.close();} catch (SQLException e) {e.printStackTrace();}
//            try {st.close();} catch (SQLException e) {e.printStackTrace();}
        }

        // * returnez lista
        return commentList;
    }

    // * aceasta metoda insereaza in tabel un comment
    @Override
    public void save(Comment entity) {
        Connection connection = ConnectionSingleton.getConnection();

        try (PreparedStatement st = connection.prepareStatement("INSERT INTO heroku_f7e69bbf73fbe2a.comment VALUES (NULL, ?, ?, ?, ?)")) {
            st.setInt(1, entity.getIdQuestion());
            st.setInt(2, entity.getNumberOfDislikes());
            st.setInt(3, entity.getNumberOfDislikes());
            st.setString(4, entity.getContent());
            st.executeUpdate();
            System.out.println("A comment has been ADDED successfully in the table");
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
    public void updateContent(int id, String newContent) {
        Connection connection = ConnectionSingleton.getConnection();

        try (PreparedStatement st = connection.prepareStatement("UPDATE heroku_f7e69bbf73fbe2a.comment SET content = ? WHERE id = ?")) {
            st.setString(1, newContent);
            st.setInt(2, id);
            st.executeUpdate();
            System.out.println("A comment's content has been updated");
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
    public void upvoteComment(int id) {
        Connection connection = ConnectionSingleton.getConnection();

        try (PreparedStatement st = connection.prepareStatement("UPDATE heroku_f7e69bbf73fbe2a.comment SET likes = likes + 1 WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A comment has been upvoted");
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
    public void downvoteComment(int id) {
        Connection connection = ConnectionSingleton.getConnection();

        try (PreparedStatement st = connection.prepareStatement("UPDATE heroku_f7e69bbf73fbe2a.comment SET dislikes = dislikes + 1 WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A comment has been downvoted");
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

    // * aceasta metoda sterge un comentariu din tabela
    @Override
    public void delete(int id) {
        Connection connection = ConnectionSingleton.getConnection();

        try (PreparedStatement st = connection.prepareStatement("DELETE FROM heroku_f7e69bbf73fbe2a.comment WHERE id=?")) {
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A comment has been DELETED successfully from the table");
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

    // * Aceasta metoda sterge toate comentariile unei intrebari
    @Override
    public void deleteAllByQuestion(int idQuestion) {
        Connection connection = ConnectionSingleton.getConnection();

        try (PreparedStatement st = connection.prepareStatement("DELETE FROM heroku_f7e69bbf73fbe2a.comment WHERE idQuestion=?")) {
            st.setInt(1, idQuestion);
            st.executeUpdate();

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
    public int numberOfComments(int id) {
        Connection connection = ConnectionSingleton.getConnection();
        int ceva = 0;
        try (PreparedStatement st = connection.prepareStatement("SELECT COUNT(*) AS number FROM heroku_f7e69bbf73fbe2a.comment WHERE idQuestion = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            rs.next();

            ceva = rs.getInt("number");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ceva;
    }

}
