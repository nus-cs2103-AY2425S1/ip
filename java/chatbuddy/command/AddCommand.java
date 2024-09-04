package chatbuddy.command;

import chatbuddy.exception.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.task.Deadline;
import chatbuddy.task.Event;
import chatbuddy.task.Task;
import chatbuddy.task.TaskList;
import chatbuddy.task.ToDo;
import chatbuddy.ui.Ui;

/**
 * Represents a command to add a task (ToDo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {

    private String description;
    private String type;

    /**
     * Constructs an AddCommand with the given description and task type.
     *
     * @param description The task description.
     * @param type The task type ("T" for ToDo, "D" for Deadline, "E" for Event).
     */
    public AddCommand(String description, String type) {
        this.description = description;
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task;
        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                String[] deadlineParts = description.split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                    throw new ChatBuddyException("The due date of a deadline cannot be empty.");
                }
                task = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "E":
                String[] eventParts = description.split(" /from | /to ");
                if (eventParts.length < 3 || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
                    throw new ChatBuddyException("The start time or end time of an event cannot be empty.");
                }
                task = new Event(eventParts[0], eventParts[1], eventParts[2]);
                break;
            default:
                throw new ChatBuddyException("Unknown task type.");
        }
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        ui.showAddTask(task, tasks.size());
    }
}
