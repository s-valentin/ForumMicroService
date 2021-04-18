package ro.uaic.info.Forum;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ForumController {

    @GetMapping("/forum")
    public ForumList forum(){

        ForumList dummy = new ForumList("Libero");

        dummy.createForum("Diana", "mad");
        dummy.createForum("Diana", "extra mad");

        return dummy;

    }

    @GetMapping("/list")
    public List<Forum> list(){

        ForumList dummy = new ForumList("Libero");

        dummy.createForum("Diana", "mad");
        dummy.createForum("Diana", "extra mad");

        return dummy.getForums();

    }

    @GetMapping("/question")
    public Question question(){

        Question dummy = new Question("What is this", "I dont knoooow");

        return dummy;

    }

    @GetMapping("/comment")
    public List<Comment> comment(){

        Question dummy = new Question("Idk", "I don't");
        dummy.addComment("I like this");

        return dummy.getComments();

    }

}
