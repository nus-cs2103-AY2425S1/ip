package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final String args;

    /**
     * Constructs a {@code MarkCommand} with the specified arguments.
     *
     * @param args The string containing the task index to be marked as done.
     */
    public MarkCommand(String args) {
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MorganaException {
        int index = Parser.parseTaskIndex(args, tasks);
        Task task = tasks.get(index);
        task.markAsDone(true);
        storage.save(tasks);
        return """
                Nice! I've marked this task as done:
                %d. %s
                """.formatted(index + 1, task);
    }

    @Override
    public String getStyleClass() {
        return "marked-label";
    }
}
