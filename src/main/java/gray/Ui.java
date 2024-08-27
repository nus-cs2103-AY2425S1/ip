package gray;

import java.util.Scanner;

public class Ui {

    private final Scanner reader;

    public Ui() {
        this.reader = new Scanner(System.in);
    }

    public void say(String text) {
        System.out.println("\t____________________________________________________________");
        System.out.print("\t");
        System.out.println(text.replace("\n", "\n\t"));
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public void showWelcome() {
        say("""
                Hello! I'm Gray.
                What can I do for you?""");
    }

    public void showLoadingError() {
        say("Error loading save file.");
    }

    public void showError(String message) {
        say(message);
    }

    public String readCommand() {
        return reader.nextLine();
    }
}
