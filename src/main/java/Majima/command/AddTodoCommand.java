package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.Task;
import Majima.task.TaskList;
import Majima.task.Todo;
import Majima.ui.Ui;

public class AddTodoCommand extends Command {
    private String description;

    /**
     * Adds a AddTodoCommand object, which when executed, will create the corresponding
     * event task in the list of tasks.
     *
     * @param description The description of the event. A String.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }


    /**
     * The assertion should never come true as an empty/invalid command
     * is caught by other classes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        Task newTask = new Todo(description);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask);
        storage.save(tasks.getTasks());
        assert newTask != null : "Assertion Failed: Todo object cannot be equal to null";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
