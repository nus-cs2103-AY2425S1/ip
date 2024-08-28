import java.util.Scanner;

public class Ui {
    private final String welcome;
    private Scanner inputScanner;

    Ui(String botName) { // Starting of bot programme
        this.welcome = showLine() + "\n"
                + "Hello! I'm " + botName + "\n"
                + "What can I do for you?\n"
                + showLine() + "\n";
        this.inputScanner = new Scanner(System.in);
    }

    public String showLine() {
        return "_____________";
    }

    public void showWelcome() {
        System.out.println(this.welcome);
    }

    public void showBye() {
        System.out.println(showLine() + "\n" + "Bye. Hope to see you again soon!" + "\n" + showLine());
    }

    public String readCommand() {
        return inputScanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("File cannot be loaded. Create taskList instead.");
    }

}
