package jbot.command;

import jbot.task.EventTask;
import jbot.task.Task;
import jbot.util.TaskList;

/**
 * Represents a command that adds an Event task to the task list.
 * This command creates a new {@link EventTask} from the provided input, adds it to the task list,
 * and prints a confirmation message with the added task.
 * Extends {@link AddCommand} to provide common functionality for adding tasks.
 */
public class EventCommand extends AddCommand {

    private static final EventCommand instance = new EventCommand();

    private EventCommand() {
    }

    /**
     * Returns the singleton instance of {@link EventCommand}.
     *
     * @return The singleton instance of {@link EventCommand}.
     */
    public static EventCommand getInstance() {
        return EventCommand.instance;
    }

    /**
     * Executes the Event command using the provided input string.
     * Creates a new {@link EventTask} from the input, adds it to the task list,
     * and prints a confirmation message with the added task.
     *
     * @param input The user input string containing the command and its arguments.
     */
    @Override
    public void run(String input) {
        Task task = new EventTask(input);
        TaskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %1$s\n", task);
        super.run(input);
    }
}