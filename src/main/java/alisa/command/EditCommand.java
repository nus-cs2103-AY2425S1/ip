package alisa.command;

import alisa.Storage;
import alisa.Ui;
import alisa.exception.AlisaException;
import alisa.task.TaskList;

public class EditCommand extends Command {
    private int index;
    private String featureToEdit;
    private String editedContent;

    /**
     * Constructs an instance of EditCommand.
     *
     * @param index Index of the task to edit.
     * @param featureToEdit The feature of the task to be edited.
     * @param editedContent The content to edit the task to.
     */
    public EditCommand(int index, String featureToEdit, String editedContent) {
        this.index = index;
        this.featureToEdit = featureToEdit;
        this.editedContent = editedContent;
    }

    /**
     * {@inheritDoc}
     *
     * Edits a task from the list of tasks.
     *
     * @param taskList List of all the tasks.
     * @param ui Ui that displays messages and interacts with user.
     * @param storage Storage that saves data into a file.
     * @return String that confirms the task's details has been edited.
     * @throws AlisaException If the index is out of bounds or the storage file can't be updated.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        return taskList.editTask(index, featureToEdit, editedContent);
    }

    /**
     * {@inheritDoc}
     *
     * Indicates that the program should not terminate.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

