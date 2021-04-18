package Model;

public class Comment extends Entity{

    private String content;
    private int numberOfLikes;
    private int numberOfDislikes;

    public Comment(String content) {
        this.content = content;
        this.numberOfDislikes = 0;
        this.numberOfLikes = 0;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public void setNumberOfDislikes(int numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
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
