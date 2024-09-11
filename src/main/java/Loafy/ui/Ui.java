package loafy.ui;

import loafy.loafyexception.LoafyException;

public class Ui {

    public String showError(LoafyException exception) {
        return "Hmm...\n" +
                exception.getMessage() + "\n" +
                "Try again!";
    }

    public String showGreeting() {
        return "Hellooo, I'm Loafy! \n" +
                "What can I do for you? :D";
    }

    public String showExit() {
        return "Byeee see you soon! ;)";
    }

    public String showStartError(LoafyException exception) {
        return exception.getMessage() + "\n";
    }
}
