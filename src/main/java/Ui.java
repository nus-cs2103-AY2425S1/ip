import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("""
                \t Hello! I'm Delta, your favourite Task Management Chatbot!
                \t What can I do for you?""");
        showLine();
    }

    public void showCommand(String command) {
        System.out.println("\t " + command);
    }

    public void showError(String message) {
        System.out.println("\t " + message);
    }
}
