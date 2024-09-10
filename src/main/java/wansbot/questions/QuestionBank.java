package wansbot.questions;

import java.util.ArrayList;

/**
 * Class which encompasses a bank of questions WansBot can learn.
 */
public class QuestionBank {
    private ArrayList<Question> questions;

    /**
     * Initialises ArrayList of questions.
     */
    public QuestionBank() {
        questions = new ArrayList<>();
    }

    /**
     * Adds a question to bank of questions
     */
    public void addQuestion(Question qn) {
        questions.add(qn);
    }

    /**
     * Iterates through question bank to find Question with a matching question to qn
     *
     * @param qn Question input by User.
     * @return return a matching Question or null.
     */
    public Question getQuestion(String qn) {

        System.out.println(qn);
        System.out.println(questions.get(0));
        for (int i = 0; i < questions.size(); i++) {
            if (qn.equalsIgnoreCase(questions.get(i).toString())) {
                System.out.println("got the question!");
                return questions.get(i);
            }
        }

        return null;
    }

    /**
     * Returns the current size of bank of questions.
     */
    public int getSize() {
        return questions.size();
    }
}
