package ro.uaic.info.Forum;

public class Comment {

    private String id;
    private String content;
    private int numberOfLikes;
    private int numberOfDislikes;

    public Comment(String content) {
        this.id = "1";
        this.content = content;
        this.numberOfDislikes = 0;
        this.numberOfLikes = 0;
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
}
