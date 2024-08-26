import java.util.Scanner;

public class Ui {
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
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

    public void showError(String message) {
        System.out.println("\t " + message);
    }
}
