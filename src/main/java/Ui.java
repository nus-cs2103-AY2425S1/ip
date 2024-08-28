import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private final String NAME_OF_CHATBOT = "Stobberi";
    private final String HELLO_GREETING =
            "Hello! I'm " + NAME_OF_CHATBOT + ".\n"
                    + "What can I do for you?";
    private final String GOODBYE_GREETING = "Bye. Hope to see you again soon! :)\n";
    public static void displayForm(String phrase) {
        System.out.println("_________________________________________________________\n"
                        + phrase
                        + "\n_________________________________________________________\n");
    }

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        Ui.displayForm(HELLO_GREETING);
    }

    public void goodbye() {
        Ui.displayForm(GOODBYE_GREETING);
    }
    public String readCommand() {
        return scanner.nextLine();
    }
}