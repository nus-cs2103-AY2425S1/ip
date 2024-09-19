package chatbot.command;

import chatbot.exception.InputException;
import chatbot.logic.TaskList;

/**
 * Represents the "remove" command that removes a task from the tasklist
 */
public class RemoveCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;
    /** Index of the task to be removed */
    private int idx;

    /**
     * Constructs the RemoveCommand object
     *
     * @param taskList The TaskList instance of the chatbot
     * @param num The task's number, as indicated by the string representation of the tasklist
     */
    public RemoveCommand(TaskList taskList, int num) {
        this.taskList = taskList;
        this.idx = num - 1;
    }

    /**
     * Executes the Deadline command, adds a Deadline task to the tasklist
     *
     * @return String representation of the task that is deleted
     * @throws InputException A potential Input Exception
     */
    @Override
    public String run() throws InputException {
        return taskList.remove(idx);
    }
}
