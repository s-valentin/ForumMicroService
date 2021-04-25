package Model;

public class Comment extends Entity{

    private int idQuestion;

    private String content;

    private int numberOfLikes;
    private int numberOfDislikes;

    public Comment() {
        this.content = "";
        this.numberOfDislikes = 0;
        this.numberOfLikes = 0;
    }

    public Comment(String content) {
        this.content = content;
        this.numberOfDislikes = 0;
        this.numberOfLikes = 0;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
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

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public int getNumberOfDislikes() {
        return numberOfDislikes;
    }
}
