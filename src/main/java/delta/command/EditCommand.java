package delta.command;

import java.time.LocalDateTime;

import delta.exception.DeltaException;
import delta.task.Deadline;
import delta.task.Event;
import delta.task.Task;
import delta.task.Todo;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;

/**
 * Concrete subclass of Command abstract class.
 * Edits a task in list.
 */
public class EditCommand extends Command {
    private int index;
    private String type;
    private String description;
    private LocalDateTime time;

    /**
     * Creates a EditCommand instance.
     *
     * @param index Index of task to be edited in list.
     * @param type String to indicate type of attribute to be changed in task.
     * @param description String to be used as new description of task.
     * @param time Date/Time to be used as new time in task.
     */
    public EditCommand(int index, String type, String description, LocalDateTime time) {
        super(CommandType.Edit);
        this.index = index;
        this.type = type;
        this.description = description;
        this.time = time;
    }

    /**
     * Returns that EditCommand is not the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Edits specific task in list.
     *
     * @param tasks List containing all current tasks.
     * @param ui User Interfacing object to print task edited message.
     * @param storage Storage object to save list after task edited.
     * @throws DeltaException If problem editing task in list or list unable to be saved.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        Task task = tasks.getTask(index - 1);
        Task newTask = null;

        // Edit description of Todo
        if (type.equals("/desc") && (task instanceof Todo)) {
            newTask = new Todo(description);

        // Edit description of Deadline
        } else if (type.equals("/desc") && (task instanceof Deadline)) {
            newTask = new Deadline(description, ((Deadline) task).getByUnformatted());

        // Edit description of Event
        } else if (type.equals("/desc") && (task instanceof Event)) {
            newTask = new Event(description, ((Event) task).getStartUnformatted(), ((Event) task).getEndUnformatted());

        // Edit deadline of Deadline
        } else if (type.equals("/by") && (task instanceof Deadline)) {
            newTask = new Deadline(task.getDescription(), time);

        // Edit start time of Event
        } else if (type.equals("/from") && (task instanceof Event)) {
            newTask = new Event(task.getDescription(), time, ((Event) task).getEndUnformatted());

        // Edit end time of Event
        } else if (type.equals("/to") && (task instanceof Event)) {
            newTask = new Event(task.getDescription(), ((Event) task).getStartUnformatted(), time);

        } else {
            throw new DeltaException("""
                    OOPS!!! The format to edit tasks is wrong!
                    \t Please follow the proper format:
                    \t * edit [index of task] [task attribute] [new value]
                    \t Possible task attributes include:
                    \t * Todo: /desc
                    \t * Deadline: /desc /by
                    \t * Event: /desc /from /to""");
        }

        if ((newTask instanceof Event)
                && (((Event) newTask).getEndUnformatted().isBefore(((Event) newTask).getStartUnformatted()))) {
            throw new DeltaException("""
                    OOPS!!! The end date cannot be before the start date!
                    \t Please set the correct date/time!""");
        }

        newTask = tasks.editTask(index, newTask);

        String message = "Nice! I've edited this task:\n"
                + "  " + newTask;
        ui.showCommand(message);
        storage.save(tasks);
        return message;
    }
}
