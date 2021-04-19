package ro.uaic.info.Forum;


import Model.Comment;
import Model.Forum;
import Model.ForumList;
import Model.Question;
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
    public Forum forum(){
        return forumService.forum();
    }

    @GetMapping("/list")
    public ForumList list(){
        return forumService.list();
    }

}
