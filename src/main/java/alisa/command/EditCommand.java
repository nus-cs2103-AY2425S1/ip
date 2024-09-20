package alisa.command;

import alisa.Storage;
import alisa.Ui;
import alisa.exception.AlisaException;
import alisa.task.TaskList;

public class EditCommand extends Command {
    private int index;
    private String featureToEdit;
    private String editedContent;

    public EditCommand(int index, String featureToEdit, String editedContent) {
        this.index = index;
        this.featureToEdit = featureToEdit;
        this.editedContent = editedContent;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        return taskList.editTask(index, featureToEdit, editedContent);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

