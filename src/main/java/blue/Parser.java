package blue;

import java.util.List;
import java.util.Scanner;
import blue.Exceptions.EmptyDescriptionException;
import blue.Exceptions.InputErrorException;
import blue.Exceptions.WrongNumberOfItemException;
import blue.task.Task;
import blue.task.TaskList;






/**
 * The {@code Parser} class handles the processing of user commands in the Blue application.
 * It interprets user inputs and triggers the appropriate actions on the {@link TaskList}.
 */
public class Parser {
    /** Scanner to read user input from the console. */
    private Scanner scanner;

    /**
     * Constructs a Parser object and initializes the scanner for reading user input.
     */
    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Parses and processes user commands to manipulate the task list.
     *
     * The method continues to read user input until the "bye" command is received, at which point it exits.
     *
     * @param taskList The {@link TaskList} object that contains the user's tasks.
     */
    public void parse(TaskList taskList) {
        String input = "";

        while (true) {
            input = scanner.nextLine();

            // Exit the loop if "bye" is typed
            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            // Print the task list if "list" is typed
            if (input.equalsIgnoreCase("list")) {
                taskList.printList();
                continue;
            }

            // Mark a task as done
            if (input.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(5));
                    taskList.mark(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number format! Please use 'mark <number>'.");
                } catch (WrongNumberOfItemException e) {
                    System.out.println("Please check the number you input.");
                }
                continue;
            }

            // Unmark a task (mark it as not done)
            if (input.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(7));
                    taskList.unmark(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number format! Please use 'unmark <number>'.");
                } catch (WrongNumberOfItemException e) {
                    System.out.println("Please check the number you input.");
                }
                continue;
            }

            // Delete a task from the list
            if (input.startsWith("delete ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(7));
                    taskList.delete(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number format! Please use 'delete <number>'.");
                } catch (WrongNumberOfItemException e) {
                    System.out.println("Please check the number you input.");
                }
                continue;
            }

            if (input.startsWith("find ")) {
                String keyword = input.substring(5);
                List<Task> matchingTasks = taskList.find(keyword);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the matching tasks in your list:");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    System.out.println("     " + (i + 1) + "." + matchingTasks.get(i));
                }
                System.out.println("    ____________________________________________________________");
                continue;
            }

            // Add a new task to the list
            try {
                taskList.addToList(input);
                String temp = "Now you have " + taskList.getNumberOfTask() + " tasks in the list.";
                System.out.println(temp);
                System.out.println("--------------------------------------------");
            } catch (EmptyDescriptionException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (InputErrorException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                // Optional: Catch any other exceptions not explicitly handled above
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
