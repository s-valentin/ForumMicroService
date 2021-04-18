package Repository;

import Model.Comment;
import Model.Forum;
import Model.Question;

import java.sql.*;
import java.util.List;

public class ForumRepository implements Repository<Forum>{

    private Connection getConnection(){

        String url = System.getProperty("spring.datasource.url");
        String username = System.getProperty("spring.datasource.username");
        String password = System.getProperty("spring.datasource.password");

        Connection connection = null;

        try{

            connection = DriverManager.getConnection(url, username, password);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;

    }


    @Override
    public Forum findOne(int id) {
        Forum forum = null;
        try(Connection connection = getConnection()){

            PreparedStatement st = connection.prepareStatement("SELECT * FROM Forums WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            forum = new Forum(rs.getString("name"), rs.getString("topic"));
            forum.setId(rs.getInt("id"));

            PreparedStatement st2 = connection.prepareStatement("SELECT * FROM Questions WHERE idForum = ?");
            st2.setInt(1, id);
            ResultSet rs2 = st2.executeQuery();
            while(rs2.next()){

                Question question = new Question(rs2.getString("title"), rs2.getString("content"));
                question.setId(rs2.getInt("id"));
                question.setNumberOfLikes(rs2.getInt("likes"));
                question.setNumberOfDislikes(rs2.getInt("dislikes"));

                PreparedStatement st3 = connection.prepareStatement("SELECT * FROM Comment WHERE id = ?");
                st3.setInt(1, question.getId());
                ResultSet rs3 = st3.executeQuery();
                while(rs3.next()){

                    Comment comment = new Comment(rs3.getString("content"));
                    comment.setId(rs3.getInt("id"));
                    comment.setNumberOfLikes(rs3.getInt("likes"));
                    comment.setNumberOfDislikes(rs3.getInt("dislikes"));

                    question.getComments().add(comment);
                }

                forum.getQuestions().add(question);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return forum;
    }



    @Override
    public List<Forum> findAll() {
        return null;
    }

    @Override
    public Forum save(Forum entity) {
        return null;
    }

    @Override
    public void update(Forum entity) {

    }

    @Override
    public void delete(int id) {

    }
}
