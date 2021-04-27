package Repository;

import Model.Comment;
import Model.ForumList;
import Model.Question;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository implements Repository<Comment> {



    // * Aceasta metoda returneaza un singur comentariu specificat de id
    @Override
    public Comment findOne(int id) {
        // * creez un comentariu pe care il returnez
        Comment comment = null;

        try (Connection connection = Repository.getConnection()) {
            // * interoghez tabela comment pentru acel id
            PreparedStatement st = connection.prepareStatement("SELECT * FROM comment WHERE id = ?");
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

    @Override
    public List<Comment> findAll() {
        return null;
    }

    // * Aceasta metoda returneaza toate comentariile de la o anumita intrebare
    // ! De verificat daca intrebarea cu acel id exista pentru a putea fi adaugat un comentariu
    public List<Comment> findAllByQuestion(int idQuestion) {
        // * Creez o lista de comentarii pe care o voi returna
        List<Comment> commentList = new ArrayList<>();

        try (Connection connection = Repository.getConnection()) {
            // * Interoghez tabela comments
            PreparedStatement st = connection.prepareStatement("SELECT * FROM comment WHERE idQuestion = ?");
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
        try (Connection connection = Repository.getConnection()) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO comment VALUES (NULL, ?, ?, ?, ?)");
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
        try (Connection connection = Repository.getConnection()) {
            PreparedStatement st = connection.prepareStatement("UPDATE comment SET likes=?, dislikes=?, content=?");
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

    public void updateContent(int id, String newContent){
        try(Connection connection = Repository.getConnection())
        {
            PreparedStatement st = connection.prepareStatement("UPDATE comment (content) SET content = ? WHERE id = ?");
            st.setString(1, newContent);
            st.setInt(2, id);
            st.executeUpdate();
            System.out.println("A comment's content has been updated");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void upvoteComment(int id){
        try(Connection connection = Repository.getConnection())
        {
            PreparedStatement st = connection.prepareStatement("UPDATE comment SET likes = likes + 1 WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A comment has been upvoted");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void downvoteComment(int id){
        try(Connection connection = Repository.getConnection())
        {
            PreparedStatement st = connection.prepareStatement("UPDATE comment SET dislikes = dislikes + 1 WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A comment has been downvoted");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



    // * aceasta metoda sterge un comentariu din tabela
    // ! de implementat request-ul
    @Override
    public boolean delete(int id) {
        try (Connection connection = Repository.getConnection()) {
            PreparedStatement st = connection.prepareStatement("DELETE FROM comment WHERE id=?");
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("A comment has been DELETED successfully from the table");
        }
     catch (SQLException e) {
        e.printStackTrace();
    }
        return false;
    }

    // * Aceasta metoda sterge toate comentariile unei intrebari
    public boolean deleteAllByQuestion(int idQuestion, Connection connection) throws SQLException{

            PreparedStatement st = connection.prepareStatement("DELETE FROM comment WHERE idQuestion=?");
            st.setInt(1, idQuestion);
            st.executeUpdate();

        return true;
    }

}
