package rasputin.command;

import rasputin.task.TaskList;
import rasputin.task.RasputinException;
import rasputin.task.InvalidTaskException;
import rasputin.task.Todo;
import rasputin.task.Deadline;
import rasputin.task.Event;

import rasputin.gui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents the command to add a new task into the TaskList.
 */
public class AddCommand extends Command implements Undoable {

    private TaskList tasks;
    private String input;

    public AddCommand(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }

    /**
     * Checks the task to be added into the TaskList.
     * If the task is valid with correct parameters, adds the task to the TaskList.
     *
     * @throws RasputinException If task has incorrect parameters such as empty description or invalid time and date.
     */
    @Override
    public String execute() throws RasputinException {
        String[] cmdSplit = input.split(" ");
        String type = cmdSplit[0];
        if (cmdSplit.length < 2) {
            throw new InvalidTaskException("ERROR! The description of a task cannot be empty.");
        }
        String desc;
        switch (type) {
            case "todo":
                desc = input.substring(5);
                Todo todo = new Todo(desc);
                tasks.add(todo);
                tasks.setLastCommand(this);
                return Ui.printAddTask(todo, tasks);
            case "deadline":
                String str = input.substring(9);
                String[] deadlineSplit = str.split(" /by ");
                desc = deadlineSplit[0];
                if (deadlineSplit.length < 2) {
                    throw new InvalidTaskException("ERROR! Deadline tasks require a deadline to be completed by.");
                }
                String by = deadlineSplit[1];
                Deadline deadline;
                try {
                    deadline = new Deadline(desc, by);
                } catch (DateTimeParseException e) {
                    throw new InvalidTaskException("ERROR! Invalid deadline format.");
                }
                tasks.add(deadline);
                tasks.setLastCommand(this);
                return Ui.printAddTask(deadline, tasks);
            case "event":
                str = input.substring(6);
                String[] durations = str.split(" /from ");
                desc = durations[0];
                if (durations.length < 2) {
                    throw new InvalidTaskException("ERROR! Event tasks require a duration for the event.");
                }
                String fromSplit = durations[1];
                String[] toSplit = fromSplit.split(" /to ");
                if (toSplit.length < 2) {
                    throw new InvalidTaskException("ERROR! Event tasks require a duration for the event.");
                }
                String from = toSplit[0];
                String to = toSplit[1];

                Event event;
                try {
                    event = new Event(desc, from, to);
                } catch (DateTimeParseException e) {
                    throw new InvalidTaskException("ERROR! Invalid event duration format.");
                }
                tasks.add(event);
                tasks.setLastCommand(this);
                return Ui.printAddTask(event, tasks);
            default:
                throw new InvalidTaskException("ERROR! Invalid task.");
        }

    }

    @Override
    public String undo() {
        tasks.remove(tasks.size() - 1);
        return Ui.printUndoCommand();
    }

    /**
     * Will always return false.
     *
     * @return False.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }
}
