package chatbot.command;

import chatbot.exception.InputException;
import chatbot.exception.InvalidArgsException;
import chatbot.logic.TaskList;

/**
 * Represents the "sort" command that sorts the tasklist in a certain order
 */
public class SortCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;
    /** A string representing the order that the tasklist should be sorted */
    private String arg;

    /**
     * Constructs the SortCommand object
     *
     * @param taskList The TaskList instance of the chatbot
     * @param arg The order that the tasklist should be sorted in
     */
    public SortCommand(TaskList taskList, String arg) {
        this.taskList = taskList;
        this.arg = arg;
    }

    /**
     * Executes the Sort command, sorts the tasklist according to the order specified
     *
     * @return A success message to indicate that the list is sorted
     * @throws InputException A potential Input Exception
     */
    @Override
    public String run() throws InputException {
        if (arg.equals("asc")) {
            return taskList.sort(TaskList.SortOrder.ASC);
        } else if (arg.equals("desc")) {
            return taskList.sort(TaskList.SortOrder.DESC);
        } else {
            throw new InvalidArgsException();
        }
    }
}
