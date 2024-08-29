package revir.user.command;

import java.io.IOException;
import revir.tasks.*;
import revir.user.Ui;
import java.time.LocalDateTime;

public class Create extends Command {
    private String taskDescription;
    private Task task;

    public Create(String taskDescription) {
        super(false);
        this.taskDescription = taskDescription;
        this.task = new Todo(taskDescription);
    }

    public Create(String taskDescription, LocalDateTime deadline) {
        super(false);
        this.taskDescription = taskDescription;
        this.task = new Deadline(taskDescription, deadline);
    }

    public Create(String taskDescription, LocalDateTime start, LocalDateTime end) {
        super(false);
        this.taskDescription = taskDescription;
        this.task = new Event(taskDescription, start, end);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws IOException {
        taskList.add(this.task);
        ui.showResult("Task added: " + this.task);
    }
}
