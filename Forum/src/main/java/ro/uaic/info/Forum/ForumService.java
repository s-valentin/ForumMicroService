package ro.uaic.info.Forum;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {

    public ForumList forum(){

        ForumList dummy = new ForumList("Libero");

        dummy.createForum("Diana", "mad");
        dummy.createForum("Diana", "extra mad");

        return dummy;

    }

    public List<Forum> list(){

        ForumList dummy = new ForumList("Libero");

        dummy.createForum("Diana", "mad");
        dummy.createForum("Diana", "extra mad");

        return dummy.getForums();

    }

    public Question question(){

        Question dummy = new Question("What is this", "I dont knoooow");

        return dummy;

    }

    public List<Comment> comment(){

        Question dummy = new Question("Idk", "I don't");
        dummy.addComment("I like this");

        return dummy.getComments();

    }


}
