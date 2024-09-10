package elysia.command;

import elysia.storage.Storage;
import elysia.task.Task;
import elysia.task.ToDos;
import elysia.ui.Ui;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ToDoCommand extends Command {
    String description;
    public ToDoCommand(String description) {
        this.description = description;
    }


    public String execute(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        Task task = new ToDos(this.description);
        tasks.add(task);
        try {
            Storage storage = new Storage(tasks);
            Storage.appendToFile(task);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getAddedMessage(tasks, task);
    }
}
