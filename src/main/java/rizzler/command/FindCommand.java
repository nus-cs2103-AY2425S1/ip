package rizzler.command;

import java.util.stream.Stream;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerException;

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
    private static final String NO_MATCHES_RESPONSE = "our list is empty right now dear, no tasks to search through!";
    private final String LIST_OF_TASKS_HEADER = "here are the tasks that match \"" + getTextInput() + "\".";

    /**
     * Constructs a FindCommand object.
     * @param strToMatch String that the user wants to search for within all tasks.
     */
    public FindCommand(String strToMatch) throws RizzlerException {
        super(strToMatch);
        checkInputValidity(strToMatch);
    }

    /**
     * Executes the search throughout all tasks in taskLog for those with descriptions that match the search term.
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
        int matchId = 1;
        output.append(LIST_OF_TASKS_HEADER);
        output.append("\n");
        for (int i = 0; i < tasksMatching.length; i++) {
            output.append(matchId);
            output.append(". ");
            output.append(tasksMatching[i]);
            output.append("\n");
            matchId++;
        }
        return output.toString().split("\n");
    }

    private void checkInputValidity(String stringToMatch) throws RizzlerException {
        if (stringToMatch.isEmpty()) {
            throw new RizzlerException("missing argument");
        }
    }
}
