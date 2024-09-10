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

    public int getSize() {
        return questions.size();
    }

    public String matchQuestion(String userInput) {

        Question qn = getQuestion(userInput.split("answer ")[1]);

        if (qn == null) {
            return "I don't have an answer for your question!";
        }

        if (qn.toString().equalsIgnoreCase(userInput.split("answer ")[1])) {
            return qn.getAnswer();
        }

        return "";
    }
}
