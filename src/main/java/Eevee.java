import java.util.ArrayList;
import java.util.Scanner;

public class Eevee {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        String divider = "____________________________________________________________\n";
        String greeting = "Hello! I'm Eevee\nWhat can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        System.out.print(divider + greeting + divider);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            System.out.print(divider);

            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are your tasks:");
                tasks.forEach((task) ->
                    System.out.println((tasks.indexOf(task) + 1) + ". " + task)
                );
            } else {
                tasks.add(input);
                System.out.println("Added '" + input + "' to your task list");
            }

            System.out.print(divider);
        }
    }
}
