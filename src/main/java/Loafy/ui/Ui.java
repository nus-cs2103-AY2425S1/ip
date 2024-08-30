package loafy.ui;

import loafy.loafyexception.LoafyException;

public class Ui {
    public void reply(String str) {
        System.out.println("Loafy: " + str + "\n");
    }

    public void showError(LoafyException exception) {
        this.reply("Hmm...\n" +
                exception.getMessage() +
                "\n   Try again!");
    }

    public void showGreeting() {
        this.reply("Hellooo, I'm Loafy!");
        this.reply("What can I do for you? :D");
    }

    public void showExit() {
        this.reply("Byeee see you soon! ;)");
    }

    public void showStartError(LoafyException exception) {
        System.out.println(exception.getMessage() + "\n");
    }
}
