import java.util.ArrayList;
import java.util.Scanner;

public class Joe {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        System.out.println("\t" + "Hello! I'm Joe");
        System.out.println("\t" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String EXIT_WORD = "bye";
        String LIST_TASKS = "list";
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals(EXIT_WORD)) {
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                return;
            }
            if (userInput.equals(LIST_TASKS)) {
                for (int i = 0; i < tasks.size(); i++) {
                    String msg = String.format("%d. %s", i + 1, tasks.get(i));
                    System.out.println("\t" + msg);
                }
            }
            else {
                tasks.add(userInput);
                System.out.println("\t" + "added: " + userInput);
            }
        }
    }
}
