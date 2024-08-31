import java.util.Scanner;

public class Ui {
    private static final String GREETING_MESSAGE = " Hello! I'm MrYapper\n"
            + " What can I do for you?";
    private static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public void say(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________");
    }

    public void showGreeting() {
        say(GREETING_MESSAGE);
    }

    public void showGoodbye() {
        say(GOODBYE_MESSAGE);
    }
}
