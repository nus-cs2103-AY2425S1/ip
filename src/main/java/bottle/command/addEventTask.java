package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Event;
import bottle.task.TaskList;
import bottle.task.Todo;

import java.time.LocalDateTime;

public class addEventTask extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public addEventTask(String description, LocalDateTime from, LocalDateTime to){
        this.description = description;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(new Event(description, from, to));
        ui.printTaskAddedMessage(taskList.getTaskList());
        storage.saveTasks(taskList.getTaskList());
    }
}
