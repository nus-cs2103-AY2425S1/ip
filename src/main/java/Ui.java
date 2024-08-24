import java.util.Scanner;

public class Ui {

    private static final String separator = "____________________________________________________________";
    private static final String greeting = "Hello! I'm Dook\nWhat can I do for you?\n" + separator;
    private static final String exit = "Bye. Hope to see you again soon!\n" + separator;

    private final Scanner scanner = new Scanner(System.in);

    public Ui() {
    }

    public void separate() {
        System.out.println(separator);
    }

    public void greet() {
        System.out.println(separator);
        System.out.println(greeting);
    }

    public void exit() {
        System.out.println(separator);
        System.out.println(exit);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void errorMessage(String errorMessage) {
        System.out.println(separator);
        System.out.println(errorMessage);
        System.out.println(separator);
    }

}
