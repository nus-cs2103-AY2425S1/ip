package elysia.command;

import java.io.IOException;
import java.util.ArrayList;

import elysia.storage.Storage;
import elysia.task.Task;
import elysia.task.ToDos;
import elysia.ui.Ui;

/**
 * Represents a create-todo item command.
 */
public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }


    /**
     * Creates a Todo item and add into the tasks list.
     *
     * @param tasks
     * @return
     */
    public String execute(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        Task task = new ToDos(this.description);
        assert task != null : "task is null";
        tasks.add(task);
        try {
            Storage.appendToFile(task);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getAddedMessage(tasks, task);
    }
}
