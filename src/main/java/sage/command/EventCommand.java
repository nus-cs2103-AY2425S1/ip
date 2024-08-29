package sage.command;

import sage.exception.SageException;
import sage.ui.Ui;
import sage.storage.Storage;
import sage.task.Task;
import sage.task.TaskList;
import sage.task.Event;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String input) throws SageException {
        String[] parts = input.split(" /from ");
        if (parts.length < 2) {
            throw new SageException("What time is your event?? :o");
        }
        this.description = parts[0].trim();
        String[] timePart = parts[1].split(" /to ");
        if (timePart.length < 2) {
            throw new SageException("What time is your event?? :o");
        }
        this.from = timePart[0].trim();
        this.to = timePart[1].trim();
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws SageException {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.showMessage("Great! I will add this task to the list:\n" + task +
                "\nNow you have " + tasks.size() +
                (tasks.size() > 1 ? " tasks" : " task") + " in your list");
        storage.saveTasks(tasks);
    }
}
