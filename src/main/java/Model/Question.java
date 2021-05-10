package Model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private final String title;
    private final String content;

    private int numberOfLikes;
    private int numberOfDislikes;
    private int numberOfComments;

    private int id;
    private int idForum;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumberOfDislikes() {
        return numberOfDislikes;
    }

    public void setNumberOfDislikes(int numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public int getIdForum() {
        return idForum;
    }

    public void setIdForum(int idForum) {
        this.idForum = idForum;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(String content) {
        Comment comment = new Comment(content);
        comments.add(comment);
        numberOfComments++;
    }
}
