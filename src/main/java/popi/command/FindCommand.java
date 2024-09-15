package popi.command;

import popi.exception.EmptyDescriptionException;
import popi.exception.PopiException;
import popi.exception.TaskNotFoundException;
import popi.task.TaskList;
import popi.task.TaskManager;
import popi.ui.Ui;

/**
 * Represents a command to find tasks that match a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param command The command string that was entered by the user.
     * @throws EmptyDescriptionException If the keyword is empty.
     */
    public FindCommand(String command) throws EmptyDescriptionException {
        String[] parts = command.split(" ", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyDescriptionException("The keyword cannot be empty.");
        }

        this.keyword = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskManager taskManager) throws PopiException {
        TaskList matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            throw new TaskNotFoundException("Aww, I couldn't find anything that matches. " +
                    "Maybe try checking again? Or let me know what else I can search for!");
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }
}
