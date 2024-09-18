package mryapper.command;

import mryapper.storage.Storage;
import mryapper.task.Task;
import mryapper.task.TaskField;
import mryapper.task.TaskList;

/**
 * A command which edits a task's field or attribute to a new given string.
 */
public class EditTask extends Command {
    public static final String SYNTAX = "Syntax: edit <taskNumber> <parameters>\n"
            + "e.g. edit 2 /description new description";
    private final int taskNumber;
    private final TaskField field;
    private final String newString;

    public EditTask(int taskNumber, TaskField field, String newString) {
        this.taskNumber = taskNumber;
        this.field = field;
        this.newString = newString;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response;
        try {
            Task taskToEdit = tasks.get(taskNumber);
            Task updatedTask = taskToEdit.edit(field, newString);
            tasks.saveToStorage(storage);
            response = String.format("Task edited successfully!\n  %s\n", updatedTask);
        } catch (IndexOutOfBoundsException e) {
            response = String.format("There is no such task!\n"
                    + "You currently have %d tasks in your list", tasks.count());
        }
        return response;
    }
}
