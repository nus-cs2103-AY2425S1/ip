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
public class Processor {
    private final Scanner s;

    /**
     * Constructs a new Ui instance.
     * Sets the scanner to react to the System.in input stream
     */
    public Processor() {
        s = new Scanner(System.in);
    }

    /**
     * Returns the exit message when the "bye" command is used.
     */
    public String getExitMessage() {
        return "See you soon :3";
    }

    /**
    * Returns a string to the GUI depending on the command
    */
    public String processCommand(Command c, TaskList tasks, Storage storage) {
        String args = c.getArgs();
        StringBuilder sb = new StringBuilder();
        Task t;
        int index;
        String[] input;
        switch (c.getCmd()) {
        case "help":
            return "Welcome! Here are the commands you can use:\n"
                    + "help: lists all commands\n"
                    + "list: lists all current tasks\n"
                    + "mark: marks task at index <int> as done\n"
                    + "unmark: marks task at index <int> as not done\n"
                    + "todo: adds a ToDo type task with given description to list\n"
                    + "deadline: adds a Deadline task with given description and deadline to list\n"
                    + "event: adds an Event task with specified time interval\n"
                    + "remove: removes task at index <int> from list\n"
                    + "find: lists tasks with descriptions that contain keywords";
        case "list":
            if (tasks.getTaskCount() == 0) {
                return "Good for you, nothing to do :D";
            }
            for (int i = 1; i <= tasks.getTaskCount(); i++) {
                sb.append(String.format("%d.%s", i, tasks.getTask(i - 1)));
                if (i != tasks.getTaskCount()) {
                    sb.append("\n");
                }
            }
            return sb.toString();
        case "mark":
            if (!args.matches("\\d+")) {
                c.setErrorFlag();
                return "Please use \"mark <int>\"";
            } else if ((index = Integer.parseInt(args)) - 1 >= tasks.getTaskCount() || index <= 0) {
                c.setErrorFlag();
                return "Invalid task index";
            } else {
                t = tasks.getTask(index - 1);
                t.markAsDone();
                sb.append("Alrighty, marked the following task as done:\n")
                        .append(t);
            }
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                c.setErrorFlag();
                return e.getMessage();
            }
            return sb.toString();
        case "unmark":
            if (!args.matches("\\d+")) {
                c.setErrorFlag();
                return "Please use \"unmark <int>\"";
            } else if ((index = Integer.parseInt(args)) - 1 >= tasks.getTaskCount() || index <= 0) {
                c.setErrorFlag();
                return "Invalid task index";
            } else {
                t = tasks.getTask(index - 1);
                t.markAsNotDone();
            }

            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                c.setErrorFlag();
                return e.getMessage();
            }
            sb.append("Unmarked the following for you:\n")
                    .append(t);
            return sb.toString();
        case "todo":
            if (!args.matches(".+")) {
                c.setErrorFlag();
                return "Please use \"todo <description>\"";
            } else {
                ToDo td = new ToDo(c.getArgs().stripIndent());
                tasks.addToTaskList(td);

                sb.append(getTaskAddedMessage(td, tasks.getTaskCount()));
            }

            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                c.setErrorFlag();
                return e.getMessage();
            }

            return sb.toString();
        case "deadline":
            if (!args.matches(".+ /by .+")) {
                c.setErrorFlag();
                return "Please use \"deadline <description> /by <deadline>\"";
            } else {
                input = args.split(" /by ");
                Deadline dl = new Deadline(input[0].stripIndent(), input[1]);
                tasks.addToTaskList(dl);

                sb.append(getTaskAddedMessage(dl, tasks.getTaskCount()));
            }

            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                c.setErrorFlag();
                return e.getMessage();
            }

            return sb.toString();
        case "event":
            if (!args.matches(".+ /from .+ /to .+")) {
                c.setErrorFlag();
                return "Please use \"event <description> /from <time> /to <time>\"";
            } else {
                input = args.split(" /from ");
                String[] interval = input[1].split(" /to ");
                Event e = new Event(input[0].stripIndent(), interval[0], interval[1]);
                tasks.addToTaskList(e);

                sb.append(getTaskAddedMessage(e, tasks.getTaskCount()));
            }

            try {
                storage.writeToFile(tasks);
            } catch (IOException err) {
                c.setErrorFlag();
                return err.getMessage();
            }

            return sb.toString();
        case "remove":
            if (!args.matches("\\d+")) {
                c.setErrorFlag();
                return "Please use \"remove <index>\"";
            } else {
                t = tasks.removeFromTaskList(Integer.parseInt(args));
                if (t == null) {
                    c.setErrorFlag();
                    return "Invalid task index";
                } else {
                    sb.append(getTaskRemovedMessage(t, tasks.getTaskCount()));
                }
            }

            try {
                storage.writeToFile(tasks);
            } catch (IOException err) {
                c.setErrorFlag();
                return err.getMessage();
            }

            return sb.toString();
        case "find":
            // No pattern specified
            if (args.isEmpty()) {
                c.setErrorFlag();
                return "Please use \"find <pattern>\"";
            } else {
                sb.append("Here's what we found: \n");
                int count = tasks.findTaskByPattern(args, sb);
                if (count == 0) {
                    sb = new StringBuilder();
                    sb.append("No relevant results");
                }
            }

            return sb.toString();
        default:
            assert false : "Should not reach Processor class default";
            return null;
        }
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
    public String getTaskRemovedMessage(Task t, int size) {
        return "You will never see this task ever again >:(\n"
                + String.format("Removed %s from task list%n", t.toString())
                + String.format("You now have %d task(s) to do%n", size);
    }
}
