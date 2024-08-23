import java.util.Scanner;

public class Ui {
    private final static String SEPARATOR = "------------------------------";
    private final static String GREETING_MESSAGE = "Hello! " +
            "I'm your friendly ChatBot assistant called MentalHealth :)\n" +
            "What can I do for you?";
    private final static String GOODBYE_MESSAGE = "Bye. " +
            "Hope to see you again soon! " +
            "If you ever need help don't forget to reach out :)";
    public Ui() {

    }

    public void showLoadingError() {
        System.out.println("No data in file.");
    }

    public void showSeparator() {
        System.out.println(SEPARATOR);
    }

    public void greeting() {
        System.out.println(formatMessage(GREETING_MESSAGE));
    }

    public void goodbye() {
        System.out.println(formatMessage(GOODBYE_MESSAGE));
    }

    public String formatMessage(String msg) {
         return SEPARATOR + "\n" + msg + "\n" + SEPARATOR;
    }

    public String readCommand(Scanner scanner) {
        System.out.println("Enter your message:");
        return scanner.nextLine();
    }

}
