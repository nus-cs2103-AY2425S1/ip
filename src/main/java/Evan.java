import java.util.Scanner;
import java.util.ArrayList;

public class Evan {
    public static void main(String[] args) {
        String divider = "_".repeat(50);
        Scanner scanner = new Scanner(System.in);
        String input;

        ArrayList<String> tasks = new ArrayList<>();

        System.out.println(divider);
        System.out.println("Hello! I'm Evan!");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        while (true) {
            input = scanner.nextLine();
            System.out.println(divider);

            switch (input) {
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                    }
                    System.out.println(divider);
                    break;
                case "bye":
                    // Exit the program
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(divider);
                    scanner.close(); // Close the scanner before exiting
                    return;
                default:
                    // Default behaviour is to add the user's input as a task
                    tasks.add(input);
                    System.out.println("added: " + input);
                    System.out.println(divider);
                    break;
            }

        }
    }

}
