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

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(String title, String content){
        Question question = new Question(title, content);
        questions.add(question);
        numberOfQuestions++;
    }

}
