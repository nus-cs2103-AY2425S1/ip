package trackie.commands;

import java.time.format.DateTimeParseException;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.tasks.Deadline;
import trackie.tasks.Event;
import trackie.tasks.Task;
import trackie.tasks.Todo;
import trackie.ui.TrackieException;

/**
 * A command that adds a task to <code>TaskList</code>.
 *
 * The type of command that is added is determined by the
 * <code>arguments</code> that are passed in.
 */
public class AddCommand extends Command {
    private StringBuilder retriever = new StringBuilder();
    private int ptr = 1;

    /**
     * Constructs a new Add Command with the arguments provided by the user.
     *
     * @param arguments An array of Strings storing the arguments provided by the user.
     */
    public AddCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    /**
     * Executes the add command.
     * Depending on the first argument that the user provides, this command will
     * tell the task list to add either a todo, deadline or event task.
     * If an exception is thrown in the process of adding the command, its
     * error message will be displayed to the user.
     *
     * @param taskList The TaskList object to which a task will be added.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws TrackieException {
        try {
            switch (arguments[0]) {
            case "todo":
                Task todoTask = new Todo(super.fetchDescription());
                taskList.addTask(todoTask);
                assert !taskList.isEmpty() : "A task should have been added, but taskList was empty.";
                return "Added: " + todoTask.toString();
            case "deadline":
                Task deadlineTask = new Deadline(super.fetchDescription(), super.fetchDeadline());
                taskList.addTask(deadlineTask);
                assert !taskList.isEmpty() : "A task should have been added, but taskList was empty.";
                return "Added: " + deadlineTask.toString();
            case "event":
                Task eventTask = new Event(super.fetchDescription(), super.fetchStartTime(), super.fetchEndTime());
                taskList.addTask(eventTask);
                assert !taskList.isEmpty() : "A task should have been added, but taskList was empty.";
                return "Added: " + eventTask.toString();
            default:
                return "Invalid Command bro";
            }
        } catch (TrackieException e) {
            return e.getMessage();
        } catch (DateTimeParseException ex) {
            return "Correct format for deadline: yyyy-dd-mm";
        } finally {
            storage.save();
        }
    }
}
