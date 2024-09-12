package command;

import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import exception.ElliotException;
import task.Task;
import utility.ImList;
import utility.Storage;
import utility.TaskList;
import utility.Ui;

/**
 * Uses regex to {@link Task} with similar description as search term.
 */
public class FindCommand extends Command {
    private final Pattern searchTerm;

    /**
     * Creates a {@link FindCommand} object without any information on the search string.
     */
    public FindCommand() {
        this.searchTerm = Pattern.compile("");
    }

    private FindCommand(Pattern searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Parses the input string accordingly into a regex pattern.
     * Requires search string.
     *
     * @param unparsedArguments complete string of unparsed argument.
     * @return a new {@link FindCommand} with the correctly parsed argument.
     * @throws ElliotException If command arguments is invalid regex.
     */
    @Override
    public Command parseArguments(String unparsedArguments) throws ElliotException {
        if (unparsedArguments == "") {
            throw new ElliotException("Provide a search term\n");
        }
        try {
            return new FindCommand(Pattern.compile(unparsedArguments, Pattern.CASE_INSENSITIVE));
        } catch (PatternSyntaxException e) {
            throw new ElliotException(e);
        }
    }

    /**
     * Finds task with description containing search term and prints them out.
     *
     * @param taskList the {@link TaskList} to which the {@link Task} will be iterated through.
     * @param storage  not used in this command.
     * @return no modification to the {@link TaskList}.
     */
    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        ImList<Task> matchedTasks = new ImList<Task>();
        Iterator<Task> iter = taskList.iterator();
        while (iter.hasNext()) {
            Task currTask = iter.next();
            if (currTask.findMatch(searchTerm)) {
                matchedTasks = matchedTasks.add(currTask);
            }
        }
        if (matchedTasks.size() == 0) {
            Ui.say("No matching task!\n");
        }
        String stringsOfMatchedTasks = "Here are the " + matchedTasks.size()
            + " matching tasks in your list:\n";
        iter = matchedTasks.iterator();
        while (iter.hasNext()) {
            stringsOfMatchedTasks += iter.next().toString() + "\n";
        }
        Ui.say(stringsOfMatchedTasks);
        return taskList;
    }
}
