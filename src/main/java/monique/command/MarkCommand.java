package monique.command;

import monique.exception.MarkException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

/**
 * Represents a command to mark a specific task as complete in the task list.
 * This command updates the status of a task to completed.
 */
public class MarkCommand extends Command {
    public static final int INDEX_OFFSET = 1;
    private final int taskNum;
    private String markMessage = "";

    /**
     * Constructs a <code>MarkCommand</code> instance with the specified task number.
     * This constructor initializes the command with the task number to be marked.
     *
     * @param taskNum the index of the task to be marked as complete
     */
    public MarkCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * Executes the mark command, which involves marking a specific task as complete.
     * If the specified task number is invalid (i.e., out of range), a <code>MarkException</code> is thrown.
     * After marking the task, a message indicating the completion status is displayed using
     * the <code>Ui</code> instance
     *
     * @param tasks the <code>TaskList</code> containing tasks to be marked
     * @param ui the <code>Ui</code> instance used to display the mark message
     * @param storage the <code>Storage</code> instance (not used in this command)
     * @throws MarkException if the task number is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MarkException {

        if (this.taskNum > tasks.getNumItems() - INDEX_OFFSET || this.taskNum < 0) {
            throw new MarkException();
        }
        assert (this.taskNum < tasks.getNumItems());
        tasks.markTask(this.taskNum);
        this.markMessage = ui.markMessage(tasks.getTask(this.taskNum));
    }

    /**
     * Returns whether this chatbot will be active after the command executes.
     * @return true since bot should remain active after the list command
     */
    @Override
    public boolean isActive() {
        return true;
    }

    /**
     * Retrieves the response message from the execution of the MarkCommand.
     *
     * @param ui the user interface instance used to format the mark message (not used in this method)
     * @return a string containing the mark message from the command execution
     */
    @Override
    public String getResponse(Ui ui) {
        return this.markMessage;
    }
}
