package sage.Command;

import sage.List.TaskList;
import sage.SageException;
import sage.Storage;
import sage.Task.DeadlineTask;
import sage.Task.EventTask;
import sage.Task.Task;
import sage.Task.ToDoTask;
import sage.Ui;

public class AddCommand extends Command {
    private final String commandType;
    private final String description;

    /**
     * Constructs an AddCommand with the command type and description.
     *
     * @param commandType The type of task to be added ("todo", "deadline", or "event").
     * @param description The description of the task, including details needed for each task.
     */
    public AddCommand(String commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    /**
     * Executes the add command, adding a task to the task list.
     * Depending on the command type, a ToDoTask, DeadlineTask, or EventTask will be created and added.
     *
     * @param tasks   The TaskList to which the new task will be added.
     * @param ui      The Ui object to handle user interaction.
     * @param storage The Storage object for saving changes to the file.
     * @throws SageException If the command type is invalid or required details are missing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        Task task;
        switch (commandType) {
        case "todo" -> {
            if (description.isEmpty()) {
                throw new SageException("Invalid todo command. Please include a description.");
            }

            task = new ToDoTask(description);
        }

        case "deadline" -> {
            String[] deadlineCommand = description.split(" /by ", 2);
            if (deadlineCommand.length < 2) {
                throw new SageException("Invalid deadline command. Please include a description and /by.");
            }

            String deadlineDescription = deadlineCommand[0].trim();
            if (deadlineDescription.isEmpty()) {
                throw new SageException("Invalid deadline command. Please include a description.");
            }

            String by = deadlineCommand[1].trim();
            if (by.isEmpty()) {
                throw new SageException("Invalid deadline command. Please include /by.");
            }

            task = new DeadlineTask(deadlineDescription, by);
        }

        case "event" -> {
            String[] eventCommand = description.split(" /from | /to ", 3);
            if (eventCommand.length < 3) {
                throw new SageException("Invalid event command. Please include description, /from, and /to.");
            }

            String eventDescription = eventCommand[0].trim();
            if (eventDescription.isEmpty()) {
                throw new SageException("Invalid event command. Please include a description.");
            }

            String from = eventCommand[1].trim();
            if (from.isEmpty()) {
                throw new SageException("Invalid event command. Please include a /from.");
            }

            String to = eventCommand[2].trim();
            if (to.isEmpty()) {
                throw new SageException("Invalid event command. Please include a /to.");
            }

            task = new EventTask(eventDescription, from, to);
        }

        default -> throw new SageException("Unknown task type.");
        }

        tasks.addTask(task);
        ui.showAddedTask(task, tasks.getSize());
    }
}
