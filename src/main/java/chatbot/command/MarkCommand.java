package chatbot.command;

import chatbot.exception.InputException;
import chatbot.logic.TaskList;

/**
 * Represents the "mark" command that marks a task as either done or not done
 */
public class MarkCommand extends Command {
    /** TaskList object that represents the Task List of the current chatbot instance */
    private TaskList taskList;
    /** Index of the task to be marked */
    private int idx;
    /** Boolean for whether the task should be marked as done*/
    private boolean isDone;

    /**
     * Constructs the MarkCommand object
     *
     * @param taskList The TaskList instance of the chatbot
     * @param num The task's number, as indicated by the string representation of the tasklist
     * @param isDone The boolean representing if the task should be marked as done or undone
     */
    public MarkCommand(TaskList taskList, int num, boolean isDone) {
        this.taskList = taskList;
        this.idx = num - 1;
        this.isDone = isDone;
    }

    /**
     * Executes the Deadline command, adds a Deadline task to the tasklist
     *
     * @return A string representation of the task that has been marked
     * @throws InputException A potential Input Exception
     */
    @Override
    public String run() throws InputException {
        return this.taskList.mark(this.idx, this.isDone);
    }
}
