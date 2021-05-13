package ro.uaic.info.Forum;


import Model.Comment;
import Model.Forum;
import Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(path = "forum")
public class ForumController {

    private final ForumService forumService;

    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    //---------------------------------------------------------------------------------------------
    // ? Mapping Forumuri

    @GetMapping("/forum")
    public Forum forum(@RequestParam(value = "id") int id) {
        return forumService.forum(id);
    }

    @RequestMapping(method = RequestMethod.GET, value ="/allForums")
    @ResponseBody
    public List<Forum> forums() {
        return forumService.forums();
    }

    @PostMapping("/addForum")
    public void addForum(@RequestParam(value = "name") String name,
                         @RequestParam(value = "topic") String topic) {
        forumService.addForum(name, topic);
    }

    @RequestMapping(method = RequestMethod.PUT, value ="/updateForumName")
    @ResponseBody
    public void forumTitle(@RequestParam(value = "idForum") int idForum,
                           @RequestParam(value = "name") String name) {
        forumService.changeForumTitle(idForum, name);
    }

    @PutMapping("/updateForumTopic")
    public void forumTopic(@RequestParam(value = "idForum") int idForum,
                           @RequestParam(value = "topic") String topic) {
        forumService.changeForumTopic(idForum, topic);
    }

    @DeleteMapping("/deleteForum")
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

    @PostMapping("/addQuestion")
    public void addQuestion(@RequestParam(value = "idForum") int idForum,
                            @RequestParam(value = "title") String title,
                            @RequestParam(value = "content") String content) {
        forumService.addQuestion(idForum, title, content);
    }

    @PutMapping("/updateQuestionTitle")
    public void questionTitle(@RequestParam(value = "idQuestion") int idQuestion,
                              @RequestParam(value = "title") String title) {
        forumService.changeQuestionTitle(idQuestion, title);
    }

    @PutMapping("/updateQuestionContent")
    public void questionContent(@RequestParam(value = "idQuestion") int idQuestion,
                                @RequestParam(value = "content") String content) {
        forumService.changeQuestionContent(idQuestion, content);
    }

    @PutMapping("/upvoteQuestion")
    public void upvoteQuestion(@RequestParam(value = "idQuestion") int idQuestion) {
        forumService.upvoteQuestion(idQuestion);
    }

    @PutMapping("/downvoteQuestion")
    public void downvoteQuestion(@RequestParam(value = "idQuestion") int idQuestion) {
        forumService.downvoteQuestion(idQuestion);
    }

    @DeleteMapping("/deleteQuestion")
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

    @PostMapping("/addComment")
    public void addComment(@RequestParam(value = "idQuestion") int idQuestion,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "nbLikes", defaultValue = "0") int numberOfLikes,
                           @RequestParam(value = "nbDislikes", defaultValue = "0") int numberOfDislikes) {
        forumService.addComment(idQuestion, content, numberOfLikes, numberOfDislikes);
    }

    @PutMapping("/upvoteComment")
    public void upvoteComment(@RequestParam(value = "idComment") int idComment) {
        forumService.upvoteComment(idComment);
    }

    @PutMapping("/downvoteComment")
    public void downvoteComment(@RequestParam(value = "idComment") int idComment) {
        forumService.downvoteComment(idComment);
    }

    @PutMapping("/updateCommentContent")
    public void updateComment(@RequestParam(value = "idComment") int idComment,
                              @RequestParam(value = "content") String content) {
        forumService.updateComment(idComment, content);
    }

    @DeleteMapping("/deleteComment")
    public void deleteComment(@RequestParam(value = "idComment") int idComment) {
        forumService.deleteComment(idComment);
    }

    // * Sterge toate comentariile asociate unei intrebari
    // ! nu toate comentariile existente in baza de date
    @DeleteMapping("/deleteAllComments")
    public void deleteAllComments(@RequestParam(value = "idQuestion") int idQuestion) {
        forumService.deleteAllComments(idQuestion);
    }
}
