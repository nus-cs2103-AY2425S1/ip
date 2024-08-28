package nathanbot.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nathanbot.tasks.Deadline;
import nathanbot.tasks.Event;
import nathanbot.tasks.Task;
import nathanbot.tasks.TaskList;
import nathanbot.tasks.ToDo;

/**
 * Cleaned up using Copilot to follow Google's Java Style Guide,
 * while keeping indentations to be 4 spaces.
 */
public abstract class CommandHandler {
    private static final String LINE = "____________________________________________________________\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    private static final String GREET = """
                        Hello! I'm NathanBot
                        What can I do for you?
                       """;

    /**
     * Handles the greet command by printing 
     * Hello! I'm NathanBot
     * What can I do for you?.
     */
    public static void handleGreet() {
        System.out.println(LINE + GREET + LINE);
    }

    /**
     * Handles the exit command by printing 
     * Bye. Hope to see you again soon!.
     */
    public static void handleExit() {
        System.out.println(LINE + EXIT + LINE);
    }

    /**
     * Handles the display list command by printing the task list.
     *
     * @param taskList The task list to display.
     */
    public static void handleDisplayList(TaskList taskList) {
        System.out.println(LINE + taskList + LINE);
    }

    /**
     * Handles the mark command, marking a task as done or undone. 
     * 
     * @param input The user input containing the command and task number.
     * @param command The command string (e.g., "mark" or "unmark").
     * @param taskList The list of tasks.
     * @param isDone True if the task should be marked as done, false otherwise.
     */
    public static void handleMarkCommand(String input, String command, TaskList taskList, boolean isDone) {
        // Assisted by Copilot
        try {
            int index = Integer.parseInt(input.substring(command.length()));
            if (isDone) {
                taskList.markAsDone(index - 1);
                System.out.println(LINE + "Nice! I've marked this task as done:\n  " + taskList.getTask(index - 1) + "\n" + LINE);
            } else {
                taskList.markAsUndone(index - 1);
                System.out.println(LINE + "OK, I've marked this task as not done yet:\n  " + taskList.getTask(index - 1) + "\n" + LINE);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(LINE + "Invalid task number. To see the list of tasks, use: list\n" + LINE);
        }
    }

    /**
     * Handles the delete command by removing a task from the task list.
     * 
     * @param input The user input containing the command and task number.
     * @param taskList The list of tasks.
     */
    public static void handleDeleteCommand(String input, TaskList taskList) {
        // Assisted by Copilot
        try {
            int index = Integer.parseInt(input.substring(CommandType.DELETE.getCommand().length()));
            Task task = taskList.getTask(index - 1);
            taskList.deleteTask(index - 1);
            System.out.println(LINE + "Noted. I've removed this task:\n  " + task + "\nNow you have " + taskList.listLength() + " tasks in the list.\n" + LINE);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(LINE + "Invalid task number. To see the list of tasks, use: list\n" + LINE);
        }
    }

    /**
     * Handles the todo command by adding a todo task to the task list.
     * 
     * @param input The user input containing the command.
     * @param taskList The list of tasks.
     */
    public static void handleTodoCommand(String input, TaskList taskList) {
        input = input.substring(CommandType.TODO.getCommand().length());
        if (input.length() == 0) {
            System.out.println(LINE + "The description of a todo cannot be empty. Use: todo <description>\n" + LINE);
            return;
        }
        ToDo task = new ToDo(input);
        taskList.addTask(task);
        printAddTaskLine(task, taskList);
    }

    /**
     * Handles the deadline command by adding a deadline task to the task list.
     * It has to be in this format: deadline <description> /by <date>.
     * 
     * @param input The user input containing the command.
     * @param taskList The list of tasks.
     */
    public static void handleDeadlineCommand(String input, TaskList taskList) {
        // Logic input by me, syntax suggested by Copilot
        input = input.substring(CommandType.DEADLINE.getCommand().length()).trim();

        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            System.out.println(LINE + "Invalid deadline format. Use: deadline <description> /by <date>\n" + LINE);
            return;
        }

        String description = parts[0].trim();
        String by = parts[1].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime deadline = LocalDateTime.parse(by, formatter);
            Deadline task = new Deadline(description, deadline);

            taskList.addTask(task);
            printAddTaskLine(task, taskList);
        } catch (DateTimeParseException e) {
            System.out.println(LINE + "Invalid date format. Please use dd/MM/yyyy HHmm.\n" + LINE);
        }
    }

    /**
     * Handles the event command by adding a event task to the task list.
     * It has to be in this format: event <description> /from <start time> /to <end time>.
     * 
     * @param input The user input containing the command.
     * @param taskList The list of tasks.
     */
    public static void handleEventCommand(String input, TaskList taskList) {
        // Logic input by me, syntax suggested by Copilot
        input = input.substring(CommandType.EVENT.getCommand().length()).trim();

        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3) {
            System.out.println(LINE + "Invalid event format. Use: event <description> /from <start time> /to <end time>\n" + LINE);
            return;
        }

        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime fromDateTime = LocalDateTime.parse(from, formatter);
            LocalDateTime toDateTime = LocalDateTime.parse(to, formatter);
            System.out.println(fromDateTime);
            System.out.println(toDateTime);
            Event task = new Event(description, fromDateTime, toDateTime);

            taskList.addTask(task);
            printAddTaskLine(task, taskList);
        } catch (DateTimeParseException e) {
            System.out.println(LINE + "Invalid date format. Please use dd/MM/yyyy HHmm.\n" + LINE);
        }
    }

    /**
     * Handles unknown commands.
     * Prints Unknown Command, womp womp..
     */
    public static void handleUnknownCommand() {
        System.out.println(LINE + "Unknown Command, womp womp." + "\n" + LINE);
    }

    private static void printAddTaskLine(Task task, TaskList taskList) {
        System.out.println(LINE + "Got it. I've added this task: \n    " + task + "\nNow you have " + taskList.listLength() + " tasks in the list.\n" + LINE);
    }
}