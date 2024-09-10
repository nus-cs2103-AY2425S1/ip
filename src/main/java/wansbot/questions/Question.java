package wansbot.questions;

public class Question {
    private String question;
    private String answer;

    public Question(String qn, String ans) {
        question = qn;
        answer = ans;
    }

    public String getAnswer() {
        return this.answer;
    }

    @Override
    public String toString() {
        return this.question;
    }
}
