package Command;

import Task.TaskList;
import Task.RasputinException;
import Task.InvalidTaskException;
import Task.Todo;
import Task.Deadline;
import Task.Event;

import Ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents the command to add a new task into the TaskList.
 */
public class AddCommand extends Command {

    private TaskList tasks;
    private String input;

    public AddCommand(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }

    /**
     * Checks the task to be added into the TaskList.
     * If the task is valid with correct parameters, adds the task in the TaskList.
     *
     * @throws RasputinException If task has incorrect parameters such as empty description or invalid time and date.
     */
    @Override
    public void execute() throws RasputinException {
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
                Ui.printAddTask(todo, tasks);
                break;
            case "deadline":
                String str = input.substring(9);
                String[] deadlineSplit = str.split(" /by ");
                desc = deadlineSplit[0];
                if (deadlineSplit.length < 2) {
                    throw new InvalidTaskException("ERROR! Deadline tasks require a deadline to be completed by.");
                }
                String by = deadlineSplit[1];
                Deadline deadline = null;
                try {
                    deadline = new Deadline(desc, by);
                } catch (DateTimeParseException e) {
                    throw new InvalidTaskException("ERROR! Invalid deadline format.");
                }
                tasks.add(deadline);
                Ui.printAddTask(deadline, tasks);
                break;
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

                Event event = null;
                try {
                    event = new Event(desc, from, to);
                } catch (DateTimeParseException e) {
                    throw new InvalidTaskException("ERROR! Invalid event duration format.");
                }
                tasks.add(event);
                Ui.printAddTask(event, tasks);
                break;
        }
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
