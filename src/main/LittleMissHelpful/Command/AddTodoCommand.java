package main.LittleMissHelpful.Command;

import main.LittleMissHelpful.Exception.InvalidCommandException;
import main.LittleMissHelpful.Task.Task;
import main.LittleMissHelpful.Task.Todo;
import main.LittleMissHelpful.TaskList;
import main.LittleMissHelpful.Ui;
import main.LittleMissHelpful.Storage;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        ui.showLine();
        System.out.println("Added to list liao: " + newTask.toString());
        System.out.println("Sian, now got " + tasks.size() + " tasks in your list.");
        ui.showLine();
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
