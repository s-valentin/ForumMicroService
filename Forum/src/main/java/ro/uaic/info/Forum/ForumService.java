package ro.uaic.info.Forum;


import Model.Comment;
import Model.Forum;
import Model.ForumList;
import Model.Question;
import Repository.CommentRepository;
import Repository.ForumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {

    public Forum forum() {
        ForumRepository forumRepository = new ForumRepository();
        return forumRepository.findOne(1);
    }

    public ForumList list() {
        ForumRepository forumRepository = new ForumRepository();
        return null;
    }

    public Comment comment(int id) {
        CommentRepository commentRepository = new CommentRepository();
        return commentRepository.findOne(id);
    }

    public List<Comment> allComments(int idQuestion) {
        CommentRepository commentRepository = new CommentRepository();
        return commentRepository.findAllByQuestion(idQuestion);
    }

    public void addComment(int idQuestion, String content, int numberOfLikes, int numberOfDislikes) {
        Comment comment = new Comment(content);
        comment.setIdQuestion(idQuestion);
        comment.setNumberOfLikes(numberOfLikes);
        comment.setNumberOfDislikes(numberOfDislikes);

        CommentRepository commentRepository = new CommentRepository();
        commentRepository.save(comment);
    }


}
