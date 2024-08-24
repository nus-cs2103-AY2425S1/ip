import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Jude, the personal assistant chatbot.
 *
 * This class helps a person to keep track of various things.
 *
 * @author Judy Park
 **/
public class Jude {
    public static void main(String[] args) {

        // Initialize the variables
        String name = "Jude";
        List<Task> list = new ArrayList<>();
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser();
        String userInput;

        // Initiate the chat
        System.out.println("Hello I'm " + name);
        System.out.println("What can I do for you, poyo?");

        while (true) {
            // Iterate receiving until the valid input

            // Handle I/OException.
            try {
                userInput = bi.readLine();
            } catch (IOException ie) {
                System.out.println("IO operation was failed or interrupted. Please try again, poyo...");
                continue;
            }

            // Handle Invalid Input Exception.
            try {
                parser.setUpUserInput(userInput);
            } catch (JudeException je) {
                System.out.println(je.getMessage());
                continue;
            }

            String command = parser.getCommand();

            // Check if the command is bye
            if (command.equals("bye")) {
                break;
            }
            // Check if the command is "list"
            else if (command.equals("list")) {
                // Print the list of tasks saved
                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%d. %s\n", (i + 1), list.get(i).toStringDetails());
                }
                continue;
            }

            String[] descriptions = parser.getDescriptions();

            // Check if the command is "mark"
            if (command.equals("mark")) {
                // Get the valid index of the list with the given number
                int index;

                // Handle if number input is not valid.
                try {
                    index = Integer.parseInt(descriptions[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("The command for mark should be followed by a number. Please try again, poyo");
                    continue;
                }
                // Handle if index is within the valid list size.
                if (index >= list.size() || index < 0) {
                    System.out.println("The task number provided is not valid."
                            + " Please provide your instruction again., poyo...");
                    continue;
                }

                // Mark the task as done
                Task task = list.get(index);
                task.markAsDone();
                System.out.printf("Poyo! I've marked this task as done:\n%s\n", task.toStringDetails());
                continue;

            // Check if the command is "unmark"
            } else if (command.equals("unmark")) {
                // Get the valid index of the list with the given number
                int index;

                // Handle if number input is not valid.
                try {
                    index = Integer.parseInt(descriptions[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("The command for unmark should be followed by a number. Please try again, poyo");
                    continue;
                }

                // Handle if index is within the valid list size.
                if (index >= list.size() || index < 0) {
                    System.out.println("The task number provided is not valid."
                            + " Please provide your instruction again, poyo...");
                    continue;
                }

                // Unmark the task as done
                Task task = list.get(index);
                task.unmarkAsDone();
                System.out.printf("Poyo! I've unmarked this task as not done:\n%s\n", task.toStringDetails());

            } else if (command.equals("delete")) {
                int index;

                try {
                    index = Integer.parseInt(descriptions[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("The command for delete should be followed by a number. Please try again, poyo");
                    continue;
                }

                if (index >= list.size() || index < 0) {
                    System.out.println("The task number provided is not valid."
                            + " Please provide your instruction again, poyo...");
                    continue;
                }

                Task task = list.get(index);
                list.remove(index);
                System.out.printf("Poyo! I've deleted this task:\n%s\n", task.toStringDetails());

            // Check if the command is to add a task.
            } else {
                Task task;
                if (command.equals("to-do")) {
                    task = new Todo(descriptions[0]);
                } else if (command.equals("deadline")) {
                    task = new Deadline(descriptions[0], descriptions[1]);
                } else if (command.equals("event")) {
                    task = new Event(descriptions[0], descriptions[1], descriptions[2]);
                    // Invalid input
                } else {
                    continue;
                }
                list.add(task);
                System.out.println("Poyo! added: " + task.toStringDetails());
            }
        }

        // Terminate the chat
        System.out.println("Poyo. Hope to see you again soon!");
    }
}


