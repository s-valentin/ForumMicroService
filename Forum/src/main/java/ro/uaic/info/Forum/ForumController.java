package ro.uaic.info.Forum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping()
    public ForumList forum(){
        return forumService.forum();
    }

    @GetMapping("/list")
    public List<Forum> list(){
        return forumService.list();
    }

    @GetMapping("/question")
    public Question question(){
        return forumService.question();
    }

    @GetMapping("/comment")
    public List<Comment> comment(){
        return forumService.comment();
    }

}
