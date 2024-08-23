import java.util.Scanner;

public class Ui {
    private final static String separator = "------------------------------";
    private final static String greetingMessage = "Hello! I'm your friendly ChatBot assistant called MentalHealth :)\n" +
            "What can I do for you?";
    private final static String goodbyeMessage = "Bye. Hope to see you again soon! If you ever need help don't forget to reach out :)";
    public Ui() {

    }

    public void showLoadingError() {
        System.out.println("No data in file.");
    }

    public void showSeparator() {
        System.out.println(separator);
    }

    public void greeting() {
        System.out.println(formatMessage(greetingMessage));
    }

    public void goodbye() {
        System.out.println(formatMessage(goodbyeMessage));
    }

    public String formatMessage(String msg) {
         return separator + "\n" + msg + "\n" + separator;
    }

    public String readCommand(Scanner scanner) {
        System.out.println("Enter your message:");
        return scanner.nextLine();
    }

}
