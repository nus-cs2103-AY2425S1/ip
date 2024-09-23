package rizzler.command;

import java.util.stream.Stream;

import rizzler.RizzlerException;
import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;

/**
 * Represents the user's command to find a task based on a search term.
 */
public class FindCommand extends Command {
    public static final String COMMAND_FORMAT = """
            Correct Usage:
            find {term in task description}
            Examples:
            find dinner
            find meeting tonight""";
    private static final String NO_MATCHES_RESPONSE = "sorry, none of the tasks in the list match your search term!";
    private static final int TASK_LIST_START_INDEX = 1;
    private final String taskListHeader = "here are the tasks that match \"" + getTextInput() + "\".";

    /**
     * Constructs a FindCommand object.
     *
     * @param strToMatch String that the user wants to search for within all tasks.
     */
    public FindCommand(String strToMatch) throws RizzlerException {
        super(strToMatch);
        checkInputValidity(strToMatch);
    }

    /**
     * Executes the search throughout all tasks in taskLog for those with descriptions that match the search term.
     *
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Multiple lines of input of a numbered list of all tasks that satisfy the user's search.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        Task[] tasks = taskLog.getLog();
        Task[] tasksMatching = Stream.of(tasks)
                .filter(task -> task.getDesc().contains(getTextInput()))
                .toArray(Task[]::new);

        if (tasksMatching.length == 0) {
            return new String[] {NO_MATCHES_RESPONSE};
        }

        StringBuilder output = new StringBuilder();
        output.append(taskListHeader).append("\n");

        for (int i = 0; i < tasksMatching.length; i++) {
            output.append(i + TASK_LIST_START_INDEX).append(". ").append(tasksMatching[i]);
            output.append("\n");
        }
        return output.toString().split("\n");
    }

    private void checkInputValidity(String stringToMatch) throws RizzlerException {
        if (stringToMatch.isEmpty()) {
            throw new RizzlerException("missing argument");
        }
    }
}
