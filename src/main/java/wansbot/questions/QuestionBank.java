package wansbot.questions;

import java.util.ArrayList;

public class QuestionBank {
    private ArrayList<Question> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question qn) {
        questions.add(qn);
    }
}
