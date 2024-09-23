package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.task.Todo;
import hien.ui.UI;

public class TodoCommand extends Command {
    private String input;

    public TodoCommand(String input, boolean isExit) {
        super(isExit);
        this.input = input;
    }
    private String addTodo(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String description = input.substring(4).trim();
        String msg = "";
        if (description.isEmpty()) {
            throw new HienException("☹ OOPS!!! The description of todo cannot be empty");
        }
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks);
        msg += ui.showMessage(" Got it. I've added this task:");
        msg += ui.showMessage("   " + todo);
        msg += ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        return msg;

    }


    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        return this.addTodo(tasks, input, storage, ui);
    }
}
