package jbot.command;

import jbot.task.DeadlineTask;
import jbot.task.Task;
import jbot.util.TaskList;

/**
 * Represents a command that adds a Deadline task to the task list.
 * This command creates a new {@link DeadlineTask} from the provided input, adds it to the task list,
 * and prints a confirmation message with the added task.
 * Extends {@link AddCommand} to provide common functionality for adding tasks.
 */
public class DeadlineCommand extends AddCommand {
    private static final DeadlineCommand instance = new DeadlineCommand();
    private DeadlineCommand() {
    }

    /**
     * Returns the singleton instance of {@link DeadlineCommand}.
     *
     * @return The singleton instance of {@link DeadlineCommand}.
     */
    public static DeadlineCommand getInstance() {
        return DeadlineCommand.instance;
    }

    /**
     * Executes the Deadline command using the provided input string.
     * Creates a new {@link DeadlineTask} from the input, adds it to the task list,
     * and prints a confirmation message with the added task.
     *
     * @param input The user input string containing the command and its arguments.
     */
    @Override
    public void run(String input) {
        Task task = new DeadlineTask(input);
        TaskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %1$s\n", task);
        super.run(input);
    }
}