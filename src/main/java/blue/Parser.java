package blue;

import java.util.List;
import java.util.Scanner;

import blue.exceptions.EmptyDescriptionException;
import blue.exceptions.InputErrorException;
import blue.exceptions.WrongNumberOfItemException;
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
    public String parse(String input, TaskList taskList) {

        // Ensure taskList is not null
        assert taskList != null : "TaskList should not be null";

        // Exit the application
        if (input.equalsIgnoreCase("bye")) {
            return UI.farewell();
        }

        // Print the task list if "list" is typed
        if (input.equalsIgnoreCase("list")) {
            return taskList.printList(); // You should implement TaskList's toString to print all tasks
        }

        // Mark a task as done
        if (input.startsWith("mark ")) {
            try {

                // Ensure input has enough length to parse the task number
                assert input.length() > 5 : "Invalid input length for mark command";

                int taskNumber = Integer.parseInt(input.substring(5));
                taskList.mark(taskNumber);
                return UI.displayAfterMark(taskList.getTask(taskNumber - 1));
            } catch (NumberFormatException | WrongNumberOfItemException e) {
                return "Invalid task number or format.";
            }
        }

        // Unmark a task
        if (input.startsWith("unmark ")) {
            try {
                assert input.length() > 7 : "Invalid input length for unmark command";
                int taskNumber = Integer.parseInt(input.substring(7));
                taskList.unmark(taskNumber);
                return UI.displayAfterUnMark(taskList.getTask(taskNumber - 1));
            } catch (NumberFormatException | WrongNumberOfItemException e) {
                return "Invalid task number or format.";
            }
        }

        // Delete a task
        if (input.startsWith("delete ")) {
            try {
                assert input.length() > 7 : "Invalid input length for delete command";
                int taskNumber = Integer.parseInt(input.substring(7));
                Task deletedTask = taskList.getTask(taskNumber - 1);
                taskList.delete(taskNumber);
                return UI.displayAfterDelete(deletedTask, taskList.getNumberOfTask());
            } catch (NumberFormatException | WrongNumberOfItemException e) {
                return "Invalid task number or format.";
            }
        }

        // Find tasks by keyword
        if (input.startsWith("find ")) {
            String keyword = input.substring(5);
            List<Task> matchingTasks = taskList.find(keyword);

            StringBuilder result = new StringBuilder();
            result.append("Here are the matching tasks in your list:\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            return result.toString();
        }

        // Add a new task
        try {
            taskList.addToList(input);
            return "Task added: " + input + ". Now you have " + taskList.getNumberOfTask() + " tasks.";
        } catch (EmptyDescriptionException | InputErrorException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    public String parse(TaskList taskList) {
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
                    return "Invalid task number format! Please use 'mark <number>'.";
                } catch (WrongNumberOfItemException e) {
                    return "Please check the number you input.";
                }
                continue;
            }

            // Unmark a task (mark it as not done)
            if (input.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(7));
                    taskList.unmark(taskNumber);
                } catch (NumberFormatException e) {
                    return "Invalid task number format! Please use 'unmark <number>'.";
                } catch (WrongNumberOfItemException e) {
                    return "Please check the number you input.";
                }
                continue;
            }

            // Delete a task from the list
            if (input.startsWith("delete ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(7));
                    taskList.delete(taskNumber);
                } catch (NumberFormatException e) {
                    return "Invalid task number format! Please use 'delete <number>'.";
                } catch (WrongNumberOfItemException e) {
                    return "Please check the number you input.";
                }
                continue;
            }

            if (input.startsWith("find ")) {
                String keyword = input.substring(5);
                List<Task> matchingTasks = taskList.find(keyword);

                // Use StringBuilder to construct the response
                StringBuilder result = new StringBuilder();
                result.append("Here are the matching tasks in your list:\n");

                for (int i = 0; i < matchingTasks.size(); i++) {
                    result.append("     ").append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
                }
                // Return the result as a string
                return result.toString();
            }


            // Add a new task to the list
            try {
                taskList.addToList(input);
                String temp = "Now you have " + taskList.getNumberOfTask() + " tasks in the list.";
                return temp;
            } catch (EmptyDescriptionException e) {
                return e.getMessage();
            } catch (InputErrorException e) {
                return e.getMessage();
            } catch (Exception e) {
                // Optional: Catch any other exceptions not explicitly handled above
                return "An unexpected error occurred: " + e.getMessage();
            }
        }

        scanner.close();
        return "";
    }
}
