package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

import gallium.task.Deadline;
import gallium.task.Event;
import gallium.task.Task;
import gallium.task.Todo;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task to the task list.
 * The task can be a todo, deadline, or event.
 */
public class AddCommand extends Command {
    private String desc;

    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    /**
     * Constructs an AddCommand with the task description.
     *
     * @param desc The description of the task will be added.
     */
    public AddCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the add command, adding a task to the task list and saving it to
     * storage.
     *
     * @param tasklist The list of tasks to execute the command on.
     * @param ui       The user interface that will interact with user.
     * @param storage  The storage that will save any changes made by the command.
     * @throws GalliumException If an error occurs during the execution of the
     *                          command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        try {
            if (desc.startsWith("todo ")) {
                Todo todo = new Todo(desc);
                ui.printAddTodo(todo);
                taskList.add(todo);
                Task.count++;
            } else if (desc.startsWith("deadline ")) {
                Deadline deadline = new Deadline(desc);
                taskList.add(deadline);
                ui.printAddDeadline(deadline);
                Task.count++;
            } else if (desc.startsWith("event ")) {
                Event event = new Event(desc);
                ui.printAddEvent(event);
                taskList.add(event);
                Task.count++;
            }
            storage.writeFile(ui);
        } catch (ArrayIndexOutOfBoundsException e) {
            if (desc.startsWith(DEADLINE)) {
                ui.showIncompleteDeadline();
            } else if (desc.startsWith(EVENT)) {
                ui.showIncompleteEvent();
            }
        } catch (ParseException e) {
            ui.showWrongDateTimeFormat();
        } catch (DateTimeParseException e) {
            ui.showWrongDateTimeFormat();
        }
    }
}
