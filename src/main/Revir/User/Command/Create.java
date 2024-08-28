package Revir.User.Command;

import java.io.IOException;
import java.time.LocalDateTime;

import Revir.Tasks.*;
import Revir.User.Ui;

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
    public void execute(Ui ui, TaskList taskList) throws IOException{
        taskList.add(this.task);
        ui.showResult("Task added: " + this.task);
    }
}
