import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bean {
    private static final String NAME = "Bean";
    private Scanner scanner;
    private List<String> tasks = new ArrayList<>();

    public Bean() {
        this.scanner = new Scanner(System.in);
    }

    public void startConversation() {
        greetAndAsk();
        while (true) {
            String input = scanner.nextLine();

            if (isBye(input)) {
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                echo(input);
            }
        }
        exit();

    }

    private void greetAndAsk() {
        System.out.println("______________________________");
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println("______________________________");
    }

    private void exit() {
        System.out.println("______________________________");
        System.out.println("Bye. Hope to see you again.");
        System.out.println("______________________________");
        scanner.close();
    }

    private boolean isBye(String s) {
        return s.equalsIgnoreCase("bye");  // Ignoring case sensitivity for better user experience
    }

    private void echo(String s) {
        tasks.add(s);
        System.out.println("______________________________");
        System.out.println("added: "+ s);
        System.out.println("______________________________");
    }

    private void printList() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
        System.out.println("______________________________");
    }

    public static void main(String[] args) {
        Bean bean = new Bean();
        bean.startConversation();
    }
}
