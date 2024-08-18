import java.util.Scanner;

public class BingBongUI {
    private final Scanner scanner;

    public BingBongUI() {
        this.scanner = new Scanner(System.in);
    }

    public void showHorizontalLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void showGreeting() {
        showHorizontalLine();
        System.out.println("BingBong: " + "Hello! I'm BingBong\n"
                + "What can I do for you?");
        showHorizontalLine();
    }

    public void showGoodbye() {
        showHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        showHorizontalLine();
    }

    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void showResponse(String response) {
        showHorizontalLine();
        System.out.println(response);
        showHorizontalLine();
    }
}
