package Model;

import java.util.ArrayList;
import java.util.List;

public class ForumList {

    private String name;
    private int numberOfForums;
    private final List<Forum> forums;

    public ForumList(String name) {
        this.name = name;
        this.numberOfForums = 0;
        this.forums = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getNumberOfForums() {
        return numberOfForums;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void createForum(String name, String topic) {

        Forum forum = new Forum(name, topic);
        forums.add(forum);
        numberOfForums++;

    }

    public void addForum(Forum forum) {
        forums.add(forum);
        numberOfForums++;
    }

}
