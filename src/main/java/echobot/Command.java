package echobot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import echobot.task.Deadline;
import echobot.task.Event;
import echobot.task.Task;
import echobot.task.TaskList;
import echobot.task.Todo;

/**
 * Represents a command issued by the user.
 * The command includes the action to be performed and the associated task list.
 */
public class Command {
    private final String[] inputParts;
    private final String command;
    private final TaskList tasks;

    /**
     * Constructs a Command with the specified input parts and task list.
     *
     * @param inputParts The parts of the user input.
     * @param tasks The list of tasks to be manipulated by this command.
     */
    public Command(String[] inputParts, TaskList tasks) {
        // Assert that inputParts is not null and has at least one element
        assert inputParts != null && inputParts.length > 0
                : "Input parts should not be null and must contain at least one element.";

        this.inputParts = inputParts;
        this.command = inputParts[0];
        this.tasks = tasks;

        // Assert that command is not null or empty
        assert this.command != null && !this.command.trim().isEmpty()
                : "Command should not be null or empty.";

        // Assert that tasks is not null
        assert this.tasks != null : "Task list should not be null.";
    }

    /**
     * Returns the command string.
     *
     * @return The command string.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is "bye", false otherwise.
     */
    public boolean isExit() {
        return this.command.equals("bye");
    }

    /**
     * Executes the command based on its type.
     * The command may modify the task list or provide feedback to the user.
     */
    public void run() {
        // Assert that the command is a valid one
        assert this.command != null : "Command should not be null.";

        switch (this.command) {
        case "bye":
            break;
        case "list":
            listTasks();
            break;
        case "mark": {
            handleMarkCommand(inputParts);
            Storage.saveTasksToFile(tasks);
            break;
        }
        case "unmark": {
            handleUnmarkCommand(inputParts);
            Storage.saveTasksToFile(tasks);
            break;
        }
        case "todo": {
            handleTodoCommand(inputParts);
            Storage.saveTasksToFile(tasks);
            break;
        }
        case "deadline": {
            try {
                handleDeadlineCommand(inputParts);
                Storage.saveTasksToFile(tasks);
            } catch (IllegalArgumentException e) {
                System.out.println(" " + e.getMessage());
                System.out.println(" Please enter date in the format dd/MM/yyyy?");
            }
            break;
        }
        case "event": {
            try {
                handleEventCommand(inputParts);
                Storage.saveTasksToFile(tasks);
            } catch (IllegalArgumentException e) {
                System.out.println(" " + e.getMessage());
                System.out.println(" Please enter date in the format dd/MM/yyyy?");
            }
            break;
        }
        case "delete": {
            handleDeleteCommand(inputParts);
            Storage.saveTasksToFile(tasks);
            break;
        }
        case "findbydate": {
            handleFindByDateCommand(inputParts);
            Storage.saveTasksToFile(tasks);
            break;
        }
        case "find": {
            handleFindCommand(inputParts);
            break;
        }
        default:
            System.out.println(" I'm sorry, I don't recognize that command.");
            break;
        }
    }

    /**
     * Lists all tasks in the task list.
     */
    private void listTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Handles the command to mark a task as done.
     *
     * @param inputParts The parts of the user input, including the task number.
     */
    private void handleMarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println(" Please specify the task number to mark as done.");
            return;
        }

        int taskNumber = Integer.parseInt(inputParts[1]) - 1;
        if (taskNumber < tasks.size() && taskNumber >= 0) {
            tasks.get(taskNumber).markAsDone();
            Storage.saveTasksToFile(tasks);
            System.out.println(" Great! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskNumber));
            return;
        }

        System.out.println(" Oops! That task number doesn't exist.");
    }

    /**
     * Handles the command to unmark a task as not done.
     *
     * @param inputParts The parts of the user input, including the task number.
     */
    private void handleUnmarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println(" Please specify the task number to unmark.");
            return;
        }

        int taskNumber = Integer.parseInt(inputParts[1]) - 1;
        if (taskNumber < tasks.size() && taskNumber >= 0) {
            tasks.get(taskNumber).markAsNotDone();
            Storage.saveTasksToFile(tasks);
            System.out.println(" OK! I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskNumber));
            return;
        }

        System.out.println(" Oops! That task number doesn't exist.");
    }

    /**
     * Handles the command to add a new todo task.
     *
     * @param inputParts The parts of the user input, including the task description.
     */
    private void handleTodoCommand(String[] inputParts) {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            System.out.println(" The description of a todo cannot be empty.");
            return;
        }

        tasks.addTask(new Todo(inputParts[1]));
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Handles the command to add a new deadline task.
     *
     * @param inputParts The parts of the user input, including the task description and due date.
     */
    private void handleDeadlineCommand(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/by ")) {
            System.out.println(" The description of a deadline must include a due date.");
            return;
        }

        String[] details = inputParts[1].split(" /by ");

        if (details.length < 2 || details[0].trim().isEmpty()) {
            System.out.println(" The description of a deadline cannot be empty.");
            return;
        }

        tasks.addTask(new Deadline(details[0], details[1]));
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Handles the command to add a new event task.
     *
     * @param inputParts The parts of the user input, including the task description, start time, and end time.
     */
    private void handleEventCommand(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/from ") || !inputParts[1].contains("/to ")) {
            System.out.println(" The description of an event must include start and end times.");
            return;
        }

        String[] details = inputParts[1].split(" /from | /to ");

        if (details.length < 3 || details[0].trim().isEmpty()) {
            System.out.println(" The description of an event cannot be empty.");
            return;
        }

        tasks.addTask(new Event(details[0], details[1], details[2]));
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Handles the command to delete a task.
     *
     * @param inputParts The parts of the user input, including the task number to be deleted.
     */
    private void handleDeleteCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println(" Please specify the task number to delete.");
            return;
        }
        try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (taskNumber < tasks.size() && taskNumber >= 0) {
                Task removedTask = tasks.removeTask(taskNumber);
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removedTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                return;
                }
            System.out.println(" Oops! That task number doesn't exist.");
        } catch (NumberFormatException e) {
                System.out.println("Please enter an item number to delete!");
            }
    }

    /**
     * Handles the command to find tasks by date.
     *
     * @param inputParts The parts of the user input, including the date to search for.
     */
    private void handleFindByDateCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("Please specify a date in the format yyyy-mm-dd.");
            return;
        }

        String dateString = inputParts[1]; // Assuming the date is the second element
        try {
            LocalDate queryDate = LocalDate.parse(dateString);
            System.out.println("Tasks occurring on "
                    + queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            boolean hasTasks = false;
            int i = 0;
            for (Task task : tasks.getTasks()) {
                if (task instanceof Deadline && ((Deadline) task).getBy().equals(queryDate)) {
                    System.out.println(" " + i + ". " + task);
                    hasTasks = true;
                } else if (task instanceof Event && (((Event) task).getFrom().equals(queryDate)
                        || ((Event) task).getTo().equals(queryDate))) {
                    System.out.println(task);
                    hasTasks = true;
                }
            }
            if (!hasTasks) {
                System.out.println("No tasks found on this date.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
        }
    }

    /**
     * Handles the command to find tasks by keyword.
     *
     * @param inputParts The parts of the user input, including the keyword to search for.
     */
    private void handleFindCommand(String[] inputParts) {
        // Check if the command includes a keyword to search
        if (inputParts.length < 2) {
            System.out.println("Please specify a keyword to search for.");
            return;
        }

        // Extract the keyword, assuming it is the second element
        String keyword = inputParts[1].trim();
        System.out.println("Searching for tasks with keyword: " + keyword);

        // Flag to check if any tasks match the keyword
        boolean hasMatchingTasks = false;
        int i = 0;

        // Iterate over the tasks to find matches
        for (Task task : tasks.getTasks()) {
            // Check if the task description contains the keyword
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(" " + i + ". " + task);
                hasMatchingTasks = true;
            }
        }

        // If no tasks match the keyword, display an appropriate message
        if (!hasMatchingTasks) {
            System.out.println("No tasks found containing the keyword: " + keyword);
        }
    }
}
