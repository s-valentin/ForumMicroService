package Model;

import java.util.ArrayList;
import java.util.List;

public class Question extends Entity {
    private int idForum;

    private String title;
    private String content;
    private int numberOfLikes;
    private int numberOfDislikes;
    private int numberOfComments;
    private List<Comment> comments;

    public Question() {
        this.title = "";
        this.content = "";
        this.numberOfLikes = 0;
        this.numberOfDislikes = 0;
        this.numberOfComments = 0;
        this.comments = new ArrayList<>();
    }

    public Question(String title, String content) {
        this.title = title;
        this.content = content;
        this.numberOfLikes = 0;
        this.numberOfDislikes = 0;
        this.numberOfComments = 0;
        this.comments = new ArrayList<>();
    }

    public int getIdForum() {
        return idForum;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public void setNumberOfDislikes(int numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public int getNumberOfDislikes() {
        return numberOfDislikes;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(String content){
        Comment comment = new Comment(content);
        comments.add(comment);
        numberOfComments++;
    }
}
