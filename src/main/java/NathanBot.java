import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

public class NathanBot {
    // cleaned up using Copilot
    private static final String LINE = "____________________________________________________________\n";
    private static final String GREET = """
                        Hello! I'm NathanBot
                        What can I do for you?
                       """;
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    private static final String BREAK_COMMAND = "bye";
    private static final String DISPLAY_LIST_COMMAND = "list";
    private static final String MARK_DONE_COMMAND = "mark ";
    private static final String MARK_UNDONE_COMMAND = "unmark ";
    private static final String TODO_COMMAND = "todo ";
    private static final String DEADLINE_COMMAND = "deadline ";
    private static final String EVENT_COMMAND = "event ";
    private static final String DELETE_COMMAND = "delete ";

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        System.out.println(LINE + GREET + LINE);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();
                if (input.equals(BREAK_COMMAND)) {
                    handleExit();
                    break;
                } else if (input.equals(DISPLAY_LIST_COMMAND)) {
                    handleDisplayList(taskList);
                } else if (input.startsWith(MARK_DONE_COMMAND)) {
                    handleMarkCommand(input, MARK_DONE_COMMAND, taskList, true);
                } else if (input.startsWith(MARK_UNDONE_COMMAND)) {
                    handleMarkCommand(input, MARK_UNDONE_COMMAND, taskList, false);
                } else if (input.startsWith(TODO_COMMAND)) {
                    handleTodoCommand(input, taskList);
                } else if (input.startsWith(DEADLINE_COMMAND)) {
                    handleDeadlineCommand(input, taskList);
                } else if (input.startsWith(EVENT_COMMAND)) {
                    handleEventCommand(input, taskList);
                } else if (input.startsWith(DELETE_COMMAND)) {
                    handleDeleteCommand(input, taskList);
                } else {
                    handleUnknownCommand();
                }
            }
        }
    }

    private static void handleExit() {
        System.out.println(LINE + EXIT + LINE);
    }

    private static void handleDisplayList(TaskList taskList) {
        System.out.println(LINE + taskList + LINE);
    }

    private static void handleMarkCommand(String input, String command, TaskList taskList, boolean isDone) {
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

    private static void handleDeleteCommand(String input, TaskList taskList) {
        // Assisted by Copilot
        try {
            int index = Integer.parseInt(input.substring(DELETE_COMMAND.length()));
            Task task = taskList.getTask(index - 1);
            taskList.deleteTask(index - 1);
            System.out.println(LINE + "Noted. I've removed this task:\n  " + task + "\nNow you have " + taskList.listLength() + " tasks in the list.\n" + LINE);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(LINE + "Invalid task number. To see the list of tasks, use: list\n" + LINE);
        }
    }

    private static void handleTodoCommand(String input, TaskList taskList) {
        input = input.substring(TODO_COMMAND.length());
        if (input.length() == 0) {
            System.out.println(LINE + "The description of a todo cannot be empty. Use: todo <description>\n" + LINE);
            return;
        }
        ToDo task = new ToDo(input);
        taskList.addTask(task);
        printAddTaskLine(task, taskList);
    }

    private static void handleDeadlineCommand(String input, TaskList taskList) {
        // Logic input by me, syntax suggested by Copilot
        input = input.substring(DEADLINE_COMMAND.length()).trim();

        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            System.out.println(LINE + "Invalid deadline format. Use: deadline <description> /by <date>\n" + LINE);
            return;
        }

        String description = parts[0].trim();
        String by = parts[1].trim();
        Deadline task = new Deadline(description, by);

        taskList.addTask(task);
        printAddTaskLine(task, taskList);
    }

    private static void handleEventCommand(String input, TaskList taskList) {
        // Logic input by me, syntax suggested by Copilot
        input = input.substring(EVENT_COMMAND.length()).trim();

        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3) {
            System.out.println(LINE + "Invalid event format. Use: event <description> /from <start time> /to <end time>\n" + LINE);
            return;
        }

        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();

        Event task = new Event(description, from, to);

        taskList.addTask(task);

        printAddTaskLine(task, taskList);
    }

    private static void handleUnknownCommand() {
        System.out.println(LINE + "Unknown Command, womp womp." + "\n" + LINE);
    }

    private static void printAddTaskLine(Task task, TaskList taskList) {
        System.out.println(LINE + "Got it. I've added this task: \n    " + task + "\nNow you have " + taskList.listLength() + " tasks in the list.\n" + LINE);
    }
}