package ro.uaic.info.Forum;


import Model.Comment;
import Model.Forum;
import Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "forum")
public class ForumController {

    private final ForumService forumService;

    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    //---------------------------------------------------------------------------------------------
    // ? Mapping Forumuri

    @GetMapping()
    public Forum forum(@RequestParam(value = "id") int id) {
        return forumService.forum(id);
    }

    @GetMapping("/allForums")
    public List<Forum> forums() {
        return forumService.forums();
    }

    @GetMapping("/addForum")
    public void addForum(@RequestParam(value = "name") String name,
                         @RequestParam(value = "topic") String topic) {
        forumService.addForum(name, topic);
    }

    @GetMapping("/updateForumName")
    public void forumTitle(@RequestParam(value = "idForum") int idForum,
                           @RequestParam(value = "name") String name) {
        forumService.changeForumTitle(idForum, name);
    }

    @GetMapping("/updateForumTopic")
    public void forumTopic(@RequestParam(value = "idForum") int idForum,
                           @RequestParam(value = "topic") String topic) {
        forumService.changeForumTopic(idForum, topic);
    }

    @GetMapping("/deleteForum")
    public void deleteForum(@RequestParam(value = "id") int id) {
        forumService.deleteForum(id);
    }

    //---------------------------------------------------------------------------------------------
    // ? Mapping Intrebari

    @GetMapping("/question")
    public Question question(@RequestParam(value = "id") int id) {
        return forumService.question(id);
    }

    @GetMapping("/allQuestions")
    public List<Question> questions(@RequestParam(value = "idForum") int idForum) {
        return forumService.questions(idForum);
    }

    @GetMapping("/addQuestion")
    public void addQuestion(@RequestParam(value = "idForum") int idForum,
                            @RequestParam(value = "title") String title,
                            @RequestParam(value = "content") String content) {
        forumService.addQuestion(idForum, title, content);
    }

    @GetMapping("/updateQuestionTitle")
    public void questionTitle(@RequestParam(value = "idQuestion") int idQuestion,
                              @RequestParam(value = "title") String title) {
        forumService.changeQuestionTitle(idQuestion, title);
    }

    @GetMapping("/updateQuestionContent")
    public void questionContent(@RequestParam(value = "idQuestion") int idQuestion,
                                @RequestParam(value = "content") String content) {
        forumService.changeQuestionContent(idQuestion, content);
    }

    @GetMapping("/upvoteQuestion")
    public void upvoteQuestion(@RequestParam(value = "idQuestion") int idQuestion) {
        forumService.upvoteQuestion(idQuestion);
    }

    @GetMapping("/downvoteQuestion")
    public void downvoteQuestion(@RequestParam(value = "idQuestion") int idQuestion) {
        forumService.downvoteQuestion(idQuestion);
    }

    @GetMapping("/deleteQuestion")
    public void deleteQuestion(@RequestParam(value = "id") int id) {
        forumService.deleteQuestion(id);
    }


    //---------------------------------------------------------------------------------------------
    // ? Mapping Comentarii

    @GetMapping("/comment")
    public Comment comment(@RequestParam(value = "id") int id) {
        return forumService.comment(id);
    }

    @GetMapping("/allComments")
    public List<Comment> comments(@RequestParam(value = "idQuestion") int idQuestion) {
        return forumService.allComments(idQuestion);
    }

    @GetMapping("/addComment")
    public void addComment(@RequestParam(value = "idQuestion") int idQuestion,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "nbLikes", defaultValue = "0") int numberOfLikes,
                           @RequestParam(value = "nbDislikes", defaultValue = "0") int numberOfDislikes) {
        forumService.addComment(idQuestion, content, numberOfLikes, numberOfDislikes);
    }

    @GetMapping("/upvoteComment")
    public void upvoteComment(@RequestParam(value = "idComment") int idComment) {
        forumService.upvoteComment(idComment);
    }

    @GetMapping("/downvoteComment")
    public void downvoteComment(@RequestParam(value = "idComment") int idComment) {
        forumService.downvoteComment(idComment);
    }

    @GetMapping("/updateCommentContent")
    public void updateComment(@RequestParam(value = "idComment") int idComment,
                              @RequestParam(value = "content") String content) {
        forumService.updateComment(idComment, content);
    }

    @GetMapping("/deleteComment")
    public void deleteComment(@RequestParam(value = "idComment") int idComment) {
        forumService.deleteComment(idComment);
    }

    // * Sterge toate comentariile asociate unei intrebari
    // ! nu toate comentariile existente in baza de date
    @GetMapping("/deleteAllComments")
    public void deleteAllComments(@RequestParam(value = "idQuestion") int idQuestion) {
        forumService.deleteAllComments(idQuestion);
    }
}
