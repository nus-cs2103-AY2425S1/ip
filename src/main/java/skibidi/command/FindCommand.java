package skibidi.command;

import skibidi.exceptions.SkibidiException;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private String keyword;

    /**
     * Creates a find command.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public FindCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        validateInput(input);
        this.keyword = everythingAfterCommand(input);
    }

    /**
     * Validates the input.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public void validateInput(String[] input) throws SkibidiException {
        if (input.length == 1) {
            throw new SkibidiException("Error: find keyword must not be empty. "
                    + "\nTo find a task, use the format: find <keyword>");
        }
    }

    private String everythingAfterCommand(String[] input) {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            description.append(input[i]).append(" ");
        }
        return description.toString().trim();
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        TaskList matchingTasks = taskList.findTask(keyword);
        if (matchingTasks.size() == 0) {
            return "Minus aura. Zero mogging tasks found.";
        } else {
            return "Here are the mogging tasks in your list:\n" + matchingTasks;
        }
    }

}
