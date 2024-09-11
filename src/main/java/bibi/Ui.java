package bibi;

import java.io.IOException;
import java.util.Scanner;

import bibi.task.Deadline;
import bibi.task.Event;
import bibi.task.Task;
import bibi.task.TaskList;
import bibi.task.ToDo;

/**
 * Represents an object that handles inputs and outputs to the console.
 */
public class Ui {
    private final Scanner s;

    /**
     * Constructs a new Ui instance.
     * Sets the scanner to react to the System.in input stream
     */
    public Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when the chat bot is launched.
     */
    public void printWelcomeMessage() {
        String logo =
                          """
                          ########   #######   ########   #######\s
                          #       #     #      #       #     #   \s
                          ########      #      ########      #   \s
                          #       #     #      #       #     #   \s
                          #       #     #      #       #     #   \s
                          ########   #######   ########   #######\s
                          """;

        System.out.println("Hello from\n" + logo + "\n"
                + "How can I help you?");
    }

    /**
     * Returns the inputs entered into the console as a String.
     * String ends when a newline character is encountered.
     *
     * @return input as String.
     */
    public String readInput() {
        return s.nextLine();
    }

    /**
     * Prints the exit message when the "bye" command is used.
     */
    public void printExitMessage() {
        printHorizontalLine();
        System.out.println("See you soon :3");
        printHorizontalLine();
    }

    /**
     * Prints all tasks currently in the TaskList.
     *
     * @param tasks TaskList containing the tasks to be printed.
     */
    public void printListMessage(TaskList tasks) {
        printHorizontalLine();
        tasks.printTaskList();
        printHorizontalLine();
    }

    /**
    * Returns a string to the GUI depending on the command
    */
    public String processCommand(Command c, TaskList tasks, Storage storage, int index) {
        StringBuilder sb = new StringBuilder();
        Task t;
        String[] input;
        switch (c.getCmd()) {
        case "list":
            if (tasks.getTaskCount() == 0) {
                return "Good for you, nothing to do :D";
            }
            for (int i = 1; i <= tasks.getTaskCount(); i++) {
                sb.append(String.format("%d.%s%n", i, tasks.getTask(i - 1)));
            }
            return sb.toString();
        case "mark":
            t = tasks.getTask(index - 1);
            t.markAsDone();
            sb.append("Alrighty, marked the following task as done:\n")
                 .append(t);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return sb.toString();
        case "unmark":
            t = tasks.getTask(index - 1);
            t.markAsNotDone();

            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            sb.append("Unmarked the following for you:\n")
                    .append(t);
            return sb.toString();
        case "todo":
            ToDo td = new ToDo(c.getArgs().stripIndent());
            tasks.addToTaskList(td);

            sb.append(getTaskAddedMessage(td, tasks.getTaskCount()));

            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            return sb.toString();
        case "deadline":
            input = c.getArgs().split(" /by ");
            Deadline dl = new Deadline(input[0].stripIndent(), input[1]);
            tasks.addToTaskList(dl);

            sb.append(getTaskAddedMessage(dl, tasks.getTaskCount()));

            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            return sb.toString();
        case "event":
            input = c.getArgs().split(" /from ");
            String[] interval = input[1].split(" /to ");
            Event e = new Event(input[0].stripIndent(), interval[0], interval[1]);
            tasks.addToTaskList(e);

            sb.append(getTaskAddedMessage(e, tasks.getTaskCount()));

            try {
                storage.writeToFile(tasks);
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }

            return sb.toString();
        case "remove":
            t = tasks.removeFromTaskList(Integer.parseInt(c.getArgs()));
            if (t == null) {
                return "Invalid task index";
            } else {
                sb.append(getTaskRemovedMessage(t, tasks.getTaskCount()));
            }

            try {
                storage.writeToFile(tasks);
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }

            return sb.toString();
        case "find":
            int count = 0;
            sb.append("Here's what we found: \n");
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                String[] words = c.getArgs().split(" ");
                for (int j = 0; j < words.length; j++) {
                    if (tasks.getTask(i).getDescription().contains(words[j])) {
                        sb.append(String.format("%d.%s%n", i + 1, tasks.getTask(i)));
                        count++;
                    }
                }
            }

            if (count == 0) {
                sb = new StringBuilder();
                sb.append("No relevant results");
            }

            return sb.toString();
        default:
            return "Should not reach Ui Default";
        }
    }
    /**
     * Prints the acknowledgement message for when the "mark" command is encountered.
     *
     * @param t The Task to be marked.
     */
    public void printTaskMarkedMessage(Task t) {
        System.out.printf("Alrighty, marked the following task as done:%n");
        System.out.println(t);
    }

    /**
     * Prints the acknowledgement message for when the "unmark" command is encountered.
     *
     * @param t The Task to be unmarked.
     */
    public void printTaskUnmarkedMessage(Task t) {
        System.out.printf("Oops, we'll get 'em next time:%n");
        System.out.println(t);
    }

    /**
     * Prints the acknowledgement message for when any Task and its subclass
     * is added to the TaskList.
     *
     * @param t The Task to be added.
     * @param size The remaining size of the TaskList.
     */
    public void printTaskAddedMessage(Task t, int size) {
        System.out.printf("Added: \"%s\" to task list%n", t);
        System.out.printf("You now have %d task(s) to do%n", size);
    }

    /**
     * Returns the acknowledgement message for when any Task and its subclass
     * is added to the TaskList.
     *
     * @param t The Task to be added.
     * @param size The remaining size of the TaskList.
     */
    public String getTaskAddedMessage(Task t, int size) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Added: \"%s\" to task list%n", t))
                .append(String.format("You now have %d task(s) to do%n", size));

        return sb.toString();
    }

    /**
     * Prints the acknowledgement message for when any Task and its subclass
     * is removed from the TaskList.
     *
     * @param t The Task to be removed.
     * @param size The remaining size of the TaskList .
     */
    public void printTaskRemovedMessage(Task t, int size) {
        System.out.println("You will never see this task ever again >:(");
        System.out.printf("Removed %s from task list%n", t.toString());
        System.out.printf("You now have %d task(s) to do%n", size);
    }
    /**
     * Prints the acknowledgement message for when any Task and its subclass
     * is removed from the TaskList.
     *
     * @param t The Task to be removed.
     * @param size The remaining size of the TaskList .
     */
    public String getTaskRemovedMessage(Task t, int size) {
        return "You will never see this task ever again >:(\n"
                + String.format("Removed %s from task list%n", t.toString())
                + String.format("You now have %d task(s) to do%n", size);
    }

    /**
     * Prints the warning message for when a known command is used incorrectly.
     *
     * @param message The correct command and format to use the command with.
     */
    public void printInvalidSyntaxMessage(String message) {
        System.out.printf("Invalid command syntax: %s%n", message);
    }

    /**
     * Prints the warning message for when an unknown command is detected.
     *
     * @param cmd The unknown command detected.
     */
    public void printUnknownCommandMessage(String cmd) {
        printHorizontalLine();
        System.out.printf("%s is an unknown command%n", cmd);
        printHorizontalLine();
    }

    /**
     * Prints a sequence of dashes that act as a divider
     */
    public void printHorizontalLine() {
        System.out.println("------------------------------------------------------------");
    }
}
