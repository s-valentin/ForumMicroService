package ro.uaic.info.Forum;

import Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final ForumService forumService;

    @Autowired
    public QuestionController(ForumService forumService) {
        this.forumService = forumService;
    }

    //---------------------------------------------------------------------------------------------
    // ? Mapping Intrebari

    @GetMapping("{id}")
    public ResponseEntity<Question> question(@PathVariable(value = "id") int id) {
        Question question = forumService.question(id);

        return ResponseEntity.ok(question);
    }

    @GetMapping("/all/{idForum}")
    public ResponseEntity<List<Question>> questions(@PathVariable(value = "idForum") int idForum) {
        List<Question> questionList = new ArrayList<>();

        questionList = forumService.questions(idForum);

        return ResponseEntity.ok(questionList);
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody Map<String, String> questionMap) {
        int idForum = 0;
        String title = null;
        String content = null;

        try {
            idForum = Integer.parseInt(questionMap.get("idForum"));
            title = questionMap.get("title");
            content = questionMap.get("content");
        } catch (Exception e) {
            e.printStackTrace();
        }

        forumService.addQuestion(idForum, title, content);

        return ResponseEntity.ok("Add Question Success");
    }

    @PutMapping
    public ResponseEntity<String> modifyQuestion(@RequestBody Map<String, String> questionMap) {
        int idQuestion = 0;
        String title = null;
        String content = null;

        try {
            idQuestion = Integer.parseInt(questionMap.get("idQuestion"));
            title = questionMap.get("title");
            content = questionMap.get("content");
        } catch (Exception e) {
            e.printStackTrace();
        }

        forumService.modifyQuestion(idQuestion, title, content);

        return ResponseEntity.ok("Update Question Success");
    }

    @PutMapping("/upvote/{idQuestion}")
    public ResponseEntity<String> upvoteQuestion(@PathVariable(value = "idQuestion") int idQuestion) {
        forumService.upvoteQuestion(idQuestion);

        return ResponseEntity.ok("Upvote Question Success");
    }

    @PutMapping("/downvote/{idQuestion}")
    public void downvoteQuestion(@PathVariable(value = "idQuestion") int idQuestion) {
        forumService.downvoteQuestion(idQuestion);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable(value = "id") int id) {
        forumService.deleteQuestion(id);

        return ResponseEntity.ok("Delete Question Success");
    }


}