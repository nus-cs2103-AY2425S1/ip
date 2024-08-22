import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Froggy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        List<String> tasks = new ArrayList<>();

        String greeting = "Hello! I'm Froggy!\n"
                + "Enter tasks and I will store it.\n"
                + "Type 'list' to view tasks or 'bye' to exit.\n"
                + "_______________________________";
        String exit = "Bye. Hope to see you again soon!\n"
                + "_______________________________";

        System.out.println(greeting);

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Task List:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i - 1));
                }
                System.out.println("_______________________________");
            } else {
                tasks.add(input);
                System.out.println("Added: " + input + "\n"
                        + "_______________________________");
            }
        }
        scanner.close();
    }
}
