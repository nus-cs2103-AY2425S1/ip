package wansbot.questions;

/**
 * Class which encompasses questions that also have answers.
 */
public class Question {
    private String question;
    private String answer;

    /**
     * Initialises the Question with qn as the question and ans as the answer to the question.
     */
    public Question(String qn, String ans) {
        question = qn;
        answer = ans;
    }

    /**
     * Returns the answer to the Question.
     */
    public String getAnswer() {
        return this.answer;
    }

    @Override
    public String toString() {
        return this.question;
    }
}
