import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        Task[] list = new Task[100];
        int listSize = 0;
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));

        // Initiate the chat
        System.out.println("Hello I'm " + name);
        System.out.println("What can I do for you, poyo?");

        while (true) {
            String userInput;
            Parser parser;

            // Iterate receiving until the valid input
            try {
                userInput = bi.readLine();
            } catch (IOException ie) {
                System.out.println("IO operation was failed or interrupted. Please try again, poyo...");
                continue;
            }

            parser = new Parser(userInput);
            if (!parser.getIsValid()) {
                System.out.println("Invalid command was provided. Please try again, poyo...");
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
                for (int i = 0; i < listSize; i++) {
                    System.out.printf("%d. %s\n", (i + 1), list[i].toStringDetails());
                }
                continue;
            }
            String[] descriptions = parser.getDescriptions();

            // Check if the command is "mark"
            if (command.equals("mark")) {
                // Get the valid index of the list with the given number
                int index;

                try {
                    index = Integer.parseInt(descriptions[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("The command for mark should be followed by a number. Please try again, poyo");
                    continue;
                }
                if (index >= listSize || index < 0) {
                    System.out.println("The task number provided is not valid."
                            + " Please provide your instruction again., poyo...");
                    continue;
                }

                // Mark the task as done
                Task task = list[index];
                task.markAsDone();
                System.out.printf("Poyo! I've marked this task as done:\n%s\n", task.toStringDetails());
                continue;

                // Check if the command is "unmark"
            } else if (command.equals("unmark")) {
                // Get the valid index of the list with the given number
                int index;
                try {
                    index = Integer.parseInt(descriptions[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("The command for unmark should be followed by a number. Please try again, poyo");
                    continue;
                }

                if (index >= listSize || index < 0) {
                    System.out.println("The task number provided is not valid."
                            + " Please provide your instruction again, poyo...");
                    continue;
                }

                // Unmark the task as done
                Task task = list[index];
                task.unmarkAsDone();
                System.out.printf("Poyo! I've unmarked this task as not done:\n%s\n", task.toStringDetails());
                continue;
            }

            // Add a task into the list
            Task task;
            if (command.equals("todo")) {
                task = new Todo(descriptions[0]);
            } else if (command.equals("deadline")) {
                task = new Deadline(descriptions[0], descriptions[1]);
            } else if (command.equals("event")) {
                task = new Event(descriptions[0], descriptions[1], descriptions[2]);
                // Invalid input
            } else {
                continue;
            }
            list[listSize++] = task;
            System.out.println("Poyo! added: " + task);
            continue;
        }

        // Terminate the chat
        System.out.println("Poyo. Hope to see you again soon!");
    }
}


