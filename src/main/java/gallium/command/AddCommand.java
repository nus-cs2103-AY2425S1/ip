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

public class AddCommand extends Command {
    private String desc;

    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    public AddCommand(String desc) {
        this.desc = desc;
    }

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
                ui.printAddDeadline(deadline);
                taskList.add(deadline);
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
