package elysia.command;

import elysia.storage.Storage;
import elysia.task.Task;
import elysia.task.ToDos;
import elysia.ui.Ui;

import java.awt.*;
import java.util.ArrayList;

public class ToDoCommand extends Command {
    String description;
    public ToDoCommand(String description) {
        this.description = description;
    }


    public void execute(ArrayList<Task> tasks, Storage storage) {
        Ui ui = new Ui();
        Task task = new ToDos(this.description);
        tasks.add(task);
        ui.showAddedMessage(tasks, task);
    }
}
