package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.Deadline;
import Majima.task.Task;
import Majima.task.TaskList;
import Majima.ui.Ui;

public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;

    /**
     * Adds a AddDeadlineCommand object, which when executed, will create the corresponding
     * deadline task in the list of tasks.
     *
     * @param description The description of the task. A String.
     * @param deadline The deadline of the task, in the format "dd-MM-YYYY HHmm"
     */
    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        Task newTask = new Deadline(description, deadline);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask);
        storage.save(tasks.getTasks());
        assert newTask != null : "Assertion Failed: Deadline object cannot be equal to null";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}