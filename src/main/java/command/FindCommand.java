package command;

import utility.ImList;
import utility.Storage;
import utility.Ui;
import utility.TaskList;
import task.Task;
import command.Command;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Iterator;
import exception.ElliotException;

public class FindCommand extends Command {
    private final Pattern searchTerm;

    public FindCommand() {
        this.searchTerm = Pattern.compile("");
    }

    private FindCommand(Pattern searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public Command parseArguments(String unparsedArguments) throws ElliotException {
        try {
            if (unparsedArguments == "") {
                Ui.say("Provide a search term\n");
                throw new ElliotException();
            }
            return new FindCommand(Pattern.compile(unparsedArguments, Pattern.CASE_INSENSITIVE));
        } catch (PatternSyntaxException e) {
            throw new ElliotException(e);
        }
    }

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
        if (matchedTasks.size() > 0) {
            String stringsOfMatchedTasks = "Here are the " + matchedTasks.size() 
                + " matching tasks in your list:\n";
            iter = matchedTasks.iterator();
            while (iter.hasNext()) {
                stringsOfMatchedTasks += iter.next().toString() + "\n";
            }
            Ui.say(stringsOfMatchedTasks);
        } else {
            Ui.say("No matching task!\n");
        }
        return taskList;
    }
}
