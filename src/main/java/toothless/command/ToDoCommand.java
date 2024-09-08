package toothless.command;

import toothless.exceptions.NoDescriptionExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.task.ToDo;
import toothless.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class ToDoCommand extends Command {

    private String description;

    /**
     * Constructor for ToDoCommand.
     *
     * @param description Description of the todo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if (description.isEmpty()) {
            throw new NoDescriptionExceptions("todo", "todo <description>");
        }
        String response = taskList.addTask(new ToDo(description), ui, taskList);
        storage.saveTask(taskList.getList());
        return response;
    }
}
