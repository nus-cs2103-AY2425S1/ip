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
    private void addTodo(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new HienException("☹ OOPS!!! The description of todo cannot be empty");
        }
        ui.showMessage("description: " + description);
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + todo);
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");

    }


    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        this.addTodo(tasks, input, storage, ui);
    }
}
