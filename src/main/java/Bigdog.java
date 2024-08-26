import java.util.Scanner;
import java.util.ArrayList;

public class Bigdog {

    // Array to store tasks
    static ArrayList<Task> toDoList = new ArrayList<>();

    // Counter to keep track of the number of tasks

    private static void editList(String str) throws BigdogException {
        if (str.isEmpty()) {
            throw new BigdogException("Please give me something to add!");
        }
        if (str.startsWith("mark")) {
            if (Integer.parseInt(str.substring(5)) <= 0 || Integer.parseInt(str.substring(5)) > toDoList.size()) {
                throw new BigdogException("That's out of your list!");
            }
            int ind = Integer.parseInt(str.substring(5)) - 1;
            toDoList.get(ind).mark();
            System.out.print("Nice! I've marked this task as done:\n" + toDoList.get(ind) + "\n");
        } else if (str.startsWith("unmark")) {
            if (Integer.parseInt(str.substring(7)) <= 0 || Integer.parseInt(str.substring(7)) > toDoList.size()) {
                throw new BigdogException("That's out of your list!");
            }
            int ind = Integer.parseInt(str.substring(7)) - 1;
            toDoList.get(ind).unmark();
            System.out.print("OK, I've marked this task as not done yet:\n" + toDoList.get(ind) + "\n");
        } else if (str.startsWith("delete")) {
            if (Integer.parseInt(str.substring(7)) <= 0 || Integer.parseInt(str.substring(7)) > toDoList.size()) {
                throw new BigdogException("That's out of your list!");
            }
            Task temp = toDoList.get(Integer.parseInt(str.substring(7)) - 1);
            toDoList.remove(Integer.parseInt(str.substring(7)) - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(temp);
            System.out.printf("Now you have %s tasks in the list.\n", toDoList.size());
        } else {
            // Add and Echo user input
            toDoList.add(Task.of(str));
            System.out.println("Got it. I've added this task:\n" + toDoList.get(toDoList.size() - 1));
            System.out.printf("Now you have %s tasks in the list.\n", toDoList.size());
        }
    }

    public static void main(String[] args) {

        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Hello! I'm Bigdog!");
        System.out.println("What can I do for you?\n");

        while (true) {

            // Take in user input
            String userInput = scanner.nextLine();

            // Terminating condition
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.print("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {

                // Print the list
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println((i + 1) + "." + toDoList.get(i));
                }
            } else {
                try {
                    editList(userInput);
                } catch (BigdogException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }
}
