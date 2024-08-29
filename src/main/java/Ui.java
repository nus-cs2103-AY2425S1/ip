import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    private void showDivider() {
        System.out.println("------------------------------------------------------------------------------------");
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        showDivider();
    }

    public void showWelcomeMessage() {
        System.out.println("Hey, Alisa here! What do you need help with?");
        System.out.println("BTW Say the word bye to get out of this conversation");
        showDivider();
    }

    public void showByeMessage() {
        System.out.println("Since you technically said bye, see ya next time!");
        showDivider();
    }

    public void showMessage(String message) {
        System.out.println(message);
        showDivider();
    }

}
