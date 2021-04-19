package ro.uaic.info.Forum;


import Model.Comment;
import Model.Forum;
import Model.ForumList;
import Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "forum")
public class ForumController {

    private final ForumService forumService;

    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @GetMapping()
    public Forum forum() {
        return forumService.forum();
    }

    @GetMapping("/list")
    public ForumList list() {
        return forumService.list();
    }

    /*
     * returneaza un json o intrebare cu un id specific
     * id
     * content
     * numberOfLikes
     * numberOfDislikes
     */
    @GetMapping("/comment")
    public Comment comment(@RequestParam(value = "id") int id) {
        return forumService.comment(id);
    }

    /*
     * returneaza un json cu toate comentariile de la o intrebare
     * id
     * content
     * numberOfLikes
     * numberOfDislikes
     */
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

    // * daca ceva nu trebuie modificat atunci se transmite ca parametru valoarea pe care o are la momentul update ului
    // ! de gandit cum se face update
//    @GetMapping("/updateComment")
//    public void updateComment(@RequestParam(value = "content") String content,
//                              @RequestParam(value = "nbLikes") int numberOfLikes,
//                              @RequestParam(value = "nbDislikes") int numberOfDislikes) {
//        forumService.updateContent(content, numberOfLikes, numberOfDislikes);
//    }
}
