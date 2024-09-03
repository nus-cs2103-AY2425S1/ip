package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;
import bottle.task.Todo;

public class addTodoTask extends Command {
    private String description;

    public addTodoTask(String description){
        this.description = description;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(new Todo(description));
        ui.printTaskAddedMessage(taskList.getTaskList());
        storage.saveTasks(taskList.getTaskList());
    }
}
