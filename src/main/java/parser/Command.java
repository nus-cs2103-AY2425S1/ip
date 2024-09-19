package parser;

import java.util.List;

import tasks.Task;

/**
 * Command interface where each command needs to have an execute method
 */
public interface Command {
    /**
     * Executes the command
     * @param input the input string
     * @param tasks the list of tasks
     * @return the output string
     */
    String execute(String input, List<Task> tasks);
}
