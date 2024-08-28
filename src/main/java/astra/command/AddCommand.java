package astra.command;

import astra.AstraException;
import astra.Ui;
import astra.Storage;
import astra.TaskList;
import astra.task.Deadline;
import astra.task.Event;
import astra.task.Task;
import astra.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param type The type of task to add.
     * @param args The arguments for the task.
     * @throws AstraException If the arguments are invalid.
     */
    public AddCommand(Task.TaskType type, HashMap<String, String> args) throws AstraException {
        try {
            switch (type) {
            case TODO:
                this.task = new Todo(args.getOrDefault("main", ""));
                break;
            case DEADLINE:
                this.task = new Deadline(args.getOrDefault("main", ""), args.get("by"));
                break;
            case EVENT:
                this.task = new Event(args.getOrDefault("main", ""), args.get("from"), args.get("to"));
                break;
            }
        } catch (NullPointerException e) {
            throw new AstraException("Please supply dates.");
        } catch (DateTimeParseException e) {
            throw new AstraException("Please supply valid dates (yyyy-MM-dd).");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.save(tasks);
        String msg = "Got it. I've added this task: \n  " + task + "\n"
                + "Now you have " + tasks.length() + " tasks in the list. \n";
        ui.display(msg);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "ADD " + task.toText();
    }
}
