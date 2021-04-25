package ro.uaic.info.Forum;


import Model.Comment;
import Model.Forum;
import Model.ForumList;
import Model.Question;
import Repository.CommentRepository;
import Repository.ForumRepository;
import Repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {

    private final ForumRepository forumRepository = new ForumRepository();
    private final QuestionRepository questionRepository = new QuestionRepository();
    private final CommentRepository commentRepository = new CommentRepository();


    public Forum forum(int id) {
        return forumRepository.findOne(id);
    }

    public List<Forum> forums() {
        return forumRepository.findAll();
    }

    public void addForum(String name, String topic) {
        Forum forum = new Forum(name, topic);

        forumRepository.save(forum);
    }

    public void deleteForum(int id) {
        forumRepository.delete(id);
    }

    public Question question(int id) {
        return questionRepository.findOne(id);
    }

    public List<Question> questions() {
        return questionRepository.findAllByForum(3);
    }

    public void addQuestion(int idForum, String title, String content){
        Question question = new Question(title, content);
        question.setIdForum(idForum);

        questionRepository.save(question);
    }

    public void deleteQuestion(int id) {
        questionRepository.delete(id);
    }

    public Comment comment(int id) {
        return commentRepository.findOne(id);
    }

    public List<Comment> allComments(int idQuestion) {
        return commentRepository.findAllByQuestion(idQuestion);
    }

    public void addComment(int idQuestion, String content, int numberOfLikes, int numberOfDislikes) {
        Comment comment = new Comment(content);
        comment.setIdQuestion(idQuestion);
        comment.setNumberOfLikes(numberOfLikes);
        comment.setNumberOfDislikes(numberOfDislikes);

        commentRepository.save(comment);
    }
}
