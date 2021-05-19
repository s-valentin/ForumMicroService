package ro.uaic.info.Forum;


import Model.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    @GetMapping("{id}")
    public ResponseEntity<Forum> forum(@PathVariable(value = "id") int id) {
        Forum forum = forumService.forum(id);

        return ResponseEntity.ok(forum);
    }

    @GetMapping
    public ResponseEntity<List<Forum>> forums() {
        List<Forum> forumList = new ArrayList<>();
        forumList = forumService.forums();

        return ResponseEntity.ok(forumList);
    }

    @PostMapping()
    public ResponseEntity<String> createForum(@RequestBody Map<String, String> forumMap) {
        String name = null;
        String topic = null;

        try {
            name = forumMap.get("name");
            topic = forumMap.get("topic");
        } catch (Exception e) {
            e.printStackTrace();
        }

        forumService.addForum(name, topic);

        return ResponseEntity.ok("Add Forum Success");
    }

    @PutMapping()
    public ResponseEntity<String> updateForum(@RequestBody Map<String, String> forumMap) {
        int idForum = 0;
        String name = null;
        String topic = null;

        try {
            idForum = Integer.parseInt(forumMap.get("idForum"));
            name = forumMap.get("name");
            topic = forumMap.get("topic");
        } catch (Exception e) {
            e.printStackTrace();
        }

        forumService.change(idForum, name, topic);

        return ResponseEntity.ok("Update Forum Success");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteForum(@PathVariable(value = "id") int id) {
        forumService.deleteForum(id);
        return ResponseEntity.ok("Delete Forum Success");
    }

    //---------------------------------------------------------------------------------------------
//    // ? Mapping Intrebari
//
//    @GetMapping("/question")
//    public Question question(@RequestParam(value = "id") int id) {
//        return forumService.question(id);
//    }
//
//    @GetMapping("/allQuestions")
//    public List<Question> questions(@RequestParam(value = "idForum") int idForum) {
//        return forumService.questions(idForum);
//    }
//
//    @PostMapping("/addQuestion")
//    public void addQuestion(@RequestParam(value = "idForum") int idForum,
//                            @RequestParam(value = "title") String title,
//                            @RequestParam(value = "content") String content) {
//        forumService.addQuestion(idForum, title, content);
//    }
//
//    @PutMapping("/updateQuestionTitle")
//    public void questionTitle(@RequestParam(value = "idQuestion") int idQuestion,
//                              @RequestParam(value = "title") String title) {
//        forumService.changeQuestionTitle(idQuestion, title);
//    }
//
//    @PutMapping("/updateQuestionContent")
//    public void questionContent(@RequestParam(value = "idQuestion") int idQuestion,
//                                @RequestParam(value = "content") String content) {
//        forumService.changeQuestionContent(idQuestion, content);
//    }
//
//    @PutMapping("/upvoteQuestion")
//    public void upvoteQuestion(@RequestParam(value = "idQuestion") int idQuestion) {
//        forumService.upvoteQuestion(idQuestion);
//    }
//
//    @PutMapping("/downvoteQuestion")
//    public void downvoteQuestion(@RequestParam(value = "idQuestion") int idQuestion) {
//        forumService.downvoteQuestion(idQuestion);
//    }
//
//    @DeleteMapping("/deleteQuestion")
//    public void deleteQuestion(@RequestParam(value = "id") int id) {
//        forumService.deleteQuestion(id);
//    }
//
//
//    //---------------------------------------------------------------------------------------------
//    // ? Mapping Comentarii
//
//    @GetMapping("/comment")
//    public Comment comment(@RequestParam(value = "id") int id) {
//        return forumService.comment(id);
//    }
//
//    @GetMapping("/allComments")
//    public List<Comment> comments(@RequestParam(value = "idQuestion") int idQuestion) {
//        return forumService.allComments(idQuestion);
//    }
//
//    @PostMapping("/addComment")
//    public void addComment(@RequestParam(value = "idQuestion") int idQuestion,
//                           @RequestParam(value = "content") String content,
//                           @RequestParam(value = "nbLikes", defaultValue = "0") int numberOfLikes,
//                           @RequestParam(value = "nbDislikes", defaultValue = "0") int numberOfDislikes) {
//        forumService.addComment(idQuestion, content, numberOfLikes, numberOfDislikes);
//    }
//
//    @PutMapping("/upvoteComment")
//    public void upvoteComment(@RequestParam(value = "idComment") int idComment) {
//        forumService.upvoteComment(idComment);
//    }
//
//    @PutMapping("/downvoteComment")
//    public void downvoteComment(@RequestParam(value = "idComment") int idComment) {
//        forumService.downvoteComment(idComment);
//    }
//
//    @PutMapping("/updateCommentContent")
//    public void updateComment(@RequestParam(value = "idComment") int idComment,
//                              @RequestParam(value = "content") String content) {
//        forumService.updateComment(idComment, content);
//    }
//
//    @DeleteMapping("/deleteComment")
//    public void deleteComment(@RequestParam(value = "idComment") int idComment) {
//        forumService.deleteComment(idComment);
//    }
//
//    // * Sterge toate comentariile asociate unei intrebari
//    // ! nu toate comentariile existente in baza de date
//    @DeleteMapping("/deleteAllComments")
//    public void deleteAllComments(@RequestParam(value = "idQuestion") int idQuestion) {
//        forumService.deleteAllComments(idQuestion);
//    }
}
