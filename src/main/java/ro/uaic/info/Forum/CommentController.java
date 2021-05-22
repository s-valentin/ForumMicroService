package ro.uaic.info.Forum;

import Model.Comment;
import Model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/forum/comment")
public class CommentController {

    private final ForumService forumService;

    public CommentController(ForumService forumService) {
        this.forumService = forumService;
    }

    //---------------------------------------------------------------------------------------------
    // ? Mapping Comentarii

    @GetMapping("{id}")
    public ResponseEntity<Comment> comment(@PathVariable(value = "id") int id) {
        Comment comment = forumService.comment(id);

        return ResponseEntity.ok(comment);
    }

    @GetMapping("/all/{idQuestion}")
    public ResponseEntity<List<Comment>> comments(@PathVariable(value = "idQuestion") int idQuestion) {
        List<Comment> commentList = new ArrayList<>();

        commentList = forumService.allComments(idQuestion);

        return ResponseEntity.ok(commentList);
    }

    @GetMapping("/number/{id}")
    public ResponseEntity<Integer> nbOfComments(@PathVariable(value = "id") int id){
        Integer number = null;

        number = forumService.nbOfComments(id);

        return ResponseEntity.ok(number);
    }

    @PostMapping
    public ResponseEntity<Response> addComment(@RequestBody Map<String, String> commentMap) {
        int idQuestion = 0;
        String content = null;

        try {
            idQuestion = Integer.parseInt(commentMap.get("idQuestion"));
            content = commentMap.get("content");
        } catch (Exception e) {
            e.printStackTrace();
        }

        forumService.addComment(idQuestion, content, 0, 0);

        return ResponseEntity.ok(new Response("Add Comment Success"));
    }

    @PutMapping("/upvote/{idComment}")
    public ResponseEntity<Response> upvoteComment(@PathVariable(value = "idComment") int idComment) {
        forumService.upvoteComment(idComment);
        return ResponseEntity.ok(new Response("Upvote Comment Success"));
    }

    @PutMapping("/downvote/{idComment}")
    public ResponseEntity<Response> downvoteComment(@PathVariable(value = "idComment") int idComment) {
        forumService.downvoteComment(idComment);
        return ResponseEntity.ok(new Response("Downvote Comment Success"));
    }

    @PutMapping
    public ResponseEntity<Response> updateComment(@RequestBody Map<String, String> commentMap) {
        int idComment = 0;
        String content = null;

        try {
            idComment = Integer.parseInt(commentMap.get("idComment"));
            content = commentMap.get("content");
        } catch (Exception e) {
            e.printStackTrace();
        }

        forumService.updateComment(idComment, content);

        return ResponseEntity.ok(new Response("Update Comment Success"));
    }

    @DeleteMapping("/delete/{idComment}")
    public ResponseEntity<Response> deleteComment(@PathVariable(value = "idComment") int idComment) {
        forumService.deleteComment(idComment);

        return ResponseEntity.ok(new Response("Delete Comment Success"));
    }

    // * Sterge toate comentariile asociate unei intrebari
    // ! nu toate comentariile existente in baza de date
    @DeleteMapping("/deleteAll/{idQuestion}")
    public ResponseEntity<Response> deleteAllComments(@PathVariable(value = "idQuestion") int idQuestion) {
        forumService.deleteAllComments(idQuestion);

        return ResponseEntity.ok(new Response("Delete ALL Comments Success"));
    }
}
