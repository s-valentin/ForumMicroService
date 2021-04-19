package ro.uaic.info.Forum;


import Model.Comment;
import Model.Forum;
import Model.ForumList;
import Model.Question;
import Repository.ForumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {

    public Forum forum(){

        ForumRepository forumRepository = new ForumRepository();
        return forumRepository.findOne(1);

    }

    public ForumList list(){

        ForumRepository forumRepository = new ForumRepository();
        return forumRepository.findAll();

    }


}
