package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String args;

    /**
     * Constructs a {@code DeleteCommand} with the specified arguments.
     *
     * @param args The string containing the task index to be deleted.
     */
    public DeleteCommand(String args) {
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MorganaException {
        int index = Parser.parseTaskIndex(args, tasks);
        Task task = tasks.remove(index);
        storage.save(tasks);
        return """
                Noted. I've removed this task:
                %d. %s
                Now you have %d task%s in the list.
                """.formatted(index + 1, task, tasks.size(), tasks.size() > 1 ? "s" : "");
    }

    @Override
    public String getStyleClass() {
        return "delete-label";
    }
}
