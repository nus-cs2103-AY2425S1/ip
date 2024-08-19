import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void makeLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        makeLine();
        System.out.println("Hello! I am Agave.\n" +
                "What can I do for you?\n");
        makeLine();
    }

    public void showBye() {
        makeLine();
        System.out.println("Bye! Hope to see you again soon!\n");
        makeLine();
    }

    public String getUserInput(String input) {
        return scanner.nextLine();
    }

    public void showEcho(String echo) {
        makeLine();
        System.out.println(echo);
        makeLine();
    }
}
