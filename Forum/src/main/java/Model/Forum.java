package Model;

import java.util.ArrayList;
import java.util.List;

public class Forum extends Entity{

    private String name;
    private String topic;
    private int numberOfQuestions;
    private List<Question> questions;

    public Forum(String name, String topic) {
        this.name = name;
        this.topic = topic;
        this.numberOfQuestions = 0;
        this.questions = new ArrayList<>();
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
