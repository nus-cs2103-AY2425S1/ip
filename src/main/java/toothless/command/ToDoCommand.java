package toothless.command;

import toothless.command.Command;
import toothless.exceptions.NoDescriptionExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.task.ToDo;
import toothless.ui.Ui;

public class ToDoCommand extends Command {

    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if(description.isEmpty()) {
            throw new NoDescriptionExceptions("todo", "todo <description>");
        }
        taskList.addTask(new ToDo(description));
        storage.saveTask(taskList.getList());
        ui.addTaskMessage(taskList.getList().getLast(), taskList.getList().size());
    }
}
