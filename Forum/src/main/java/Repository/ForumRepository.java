package Repository;

import Model.Comment;
import Model.Forum;
import Model.ForumList;
import Model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForumRepository implements Repository<Forum> {

    // * Aceasta metoda returneaza un forum, cu toate intrebarile lui.
    @Override
    public Forum findOne(int id) {
        // * Creez un forum pe care il returnez la final
        Forum forum = null;

        try (Connection connection = Repository.getConnection()) {

            // * Pregatesc interogarea pentru baza de date
            PreparedStatement st = connection.prepareStatement("select * from Forums where id = ?");
            st.setInt(1, id);

            // * Execut interogarea
            ResultSet rs = st.executeQuery();
            rs.next();

            // * Instantiez forumul cu valori din baza de date
            forum = new Forum(rs.getString("name"), rs.getString("topic"));
            forum.setId(rs.getInt("id"));

            // * Ma folosesc de QuestionRepository pentru a scoate toate intrebarile acestui forum
            QuestionRepository questionRepository = new QuestionRepository();
            forum.setQuestions(questionRepository.findAllByForum(id));

            // * Setez numarul de intrebari dupa marimea listei de mai sus
            forum.setNumberOfQuestions(forum.getQuestions().size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return forum;

    }

    @Override
    public List<Forum> findAll() {

        List<Forum> forums = new ArrayList<>();

        try(Connection connection = Repository.getConnection()){
            // * Pregatesc interogarea pentru baza de date
            PreparedStatement st = connection.prepareStatement("SELECT * FROM Forums");

            // * Execut interogarea
            ResultSet rs = st.executeQuery();

            // * Trec prin toate randurile selectate
            while(rs.next()){

                // * Creez un forum caruia ii dau valori din baza de date
                Forum forum = new Forum(rs.getString("name"), rs.getString("topic"));
                forum.setId(rs.getInt("id"));

                // * Adaug toate intrebarile acelui forum
                QuestionRepository questions = new QuestionRepository();
                forum.setQuestions(questions.findAllByForum(forum.getId()));
                forum.setNumberOfQuestions(forum.getQuestions().size());

                // * Adaug forumul creat in lista finala.
                forums.add(forum);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return forums;

    }

    @Override
    public boolean save(Forum entity) {

        try(Connection connection = Repository.getConnection()){

            // * Pregatesc statement-ul de inserare in baza de date
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Forums Values(NULL, ?, ?)");
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getTopic());

            // * Adaug forumul in baza de date
            statement.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Forum entity) {
        //update
        return false;
    }

    // * Aceasta metoda sterge un forum cu toate intrebarile lui
    @Override
    public boolean delete(int id) {
        try(Connection connection = Repository.getConnection()){


            PreparedStatement statement = connection.prepareStatement("DELETE FROM Forums WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            // * Aici ma folosesc de functia din QuestionRepository pentru a sterge toate intrebarile si comentariile lor
            QuestionRepository questionRepository = new QuestionRepository();
            questionRepository.deleteAllByForum(id, connection);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
