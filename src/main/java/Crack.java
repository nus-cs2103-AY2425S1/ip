import java.util.Scanner;
import java.util.ArrayList;

public class Crack {
    public static void main(String[] args) {
        String divider = "____________________________________________________________\n";
        String greeting = divider
                + " Hello! I'm Crack\n"
                + " What can I do for you?\n"
                + divider;
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equals("list")) {
                System.out.println(divider + " Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                System.out.println(divider);
            } else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(index).markAsDone();
                    System.out.println(divider + " Nice! I've marked this task as done:\n   " + tasks.get(index) + "\n" + divider);
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(index).unmark();
                    System.out.println(divider + " OK, I've marked this task as not done yet:\n   " + tasks.get(index) + "\n" + divider);
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
            } else {
                tasks.add(new Task(input));
                System.out.println(divider + "\nadded: " + input + "\n" + divider);
            }
        }

        scanner.close();
    }
}
