package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.Event;
import Majima.task.Task;
import Majima.task.TaskList;
import Majima.ui.Ui;

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Adds a AddEventCommand object, which when executed, will create the corresponding
     * event task in the list of tasks.
     *
     * @param description The description of the event. A String.
     * @param from The starting time/date of the event. A String.
     * @param to The ending time/date of the event. A String.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        Task newTask = new Event(description, from, to);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask);
        storage.save(tasks.getTasks());
        assert newTask != null : "Assertion Failed: Event object cannot be equal to null";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}