import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____  _            _                _   \n"
                + "|  _ \\| |          | |              | |  \n"
                + "| |_) | | __ _  ___| | ___ __  _   _| |_ \n"
                + "|  _ <| |/ _` |/ __| |/ / '_ \\| | | | __|\n"
                + "| |_) | | (_| | (__|   <| | | | |_| | |_ \n"
                + "|____/|_|\\__,_|\\___|_|\\_\\_| |_|\\__,_|\\__|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blacknut");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim();

            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (tasks.isEmpty()) {
                    System.out.println(" The list is empty.");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                }
            } else {
                tasks.add(input);
                System.out.println(" added: " + input);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}