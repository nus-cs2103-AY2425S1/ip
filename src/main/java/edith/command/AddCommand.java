package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.Task;
import edith.task.ToDo;
import edith.task.Deadline;
import edith.task.Event;
import edith.task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task to the task list.
 * The AddCommand class parses a user instruction to create and add a new task
 * to the task list. It supports adding ToDo, Deadline, and Event tasks.
 */
public class AddCommand extends Command {
    private String instruction;

    /**
     * Constructs an AddCommand with the specified instruction.
     *
     * @param instruction The instruction provided by the user for adding a task.
     */
    public AddCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Executes the AddCommand by creating a task based on the user instruction,
     * adding it to the task list, and saving the updated task list.
     *
     * <p>This method will:
     * <ul>
     *     <li>Create a ToDo, Deadline, or Event task based on
     *     the instruction provided.</li>
     *     <li>Throw an EdithException if the instruction is invalid or incomplete.</li>
     *     <li>Update the task list and save the changes using the provided Storage.</li>
     * </ul>
     *
     * @param tasks The TaskList to which the task should be added.
     * @param ui The Ui used to display messages to the user.
     * @param storage The Storage used to save the updated task list.
     * @throws EdithException If the user instruction is invalid or if there is an issue with the date/time format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        Task task = null;

        if (instruction.startsWith("todo ")) {
            String taskString = instruction.substring(5).trim();
            if (taskString.isEmpty()) {
                throw new EdithException("Invalid task as no description for this todo was provided.");
            }
            task = new ToDo(taskString);
        } else if (instruction.startsWith("deadline ")) {
            String[] parts = instruction.substring(9).split(" /by ");
            if (parts.length != 2) {
                throw new EdithException("Deadlines must have both a description and a due date.");
            }
            String taskString = parts[0].trim();
            String dueDate = parts[1].trim();
            task = new Deadline(taskString, dueDate);
        } else if (instruction.startsWith("event ")) {
            String[] parts = instruction.substring(6).split(" /from | /to ");
            if (parts.length != 3) {
                throw new EdithException("Event must have a description, start time, and end time.");
            }
            String taskString = parts[0].trim();
            String startTime = parts[1].trim();
            String endTime = parts[2].trim();
            task = new Event(taskString, startTime, endTime);
        } else {
            throw new EdithException("Invalid command for adding tasks.");
        }

        try {
            tasks.addTask(task);
            storage.save(tasks.getListOfTasks());

            StringBuilder response = new StringBuilder();
            response.append("Got it. I've added this task:\n")
                    .append(task.toString()).append("\n")
                    .append("There are now ").append(tasks.getNumOfTasks()).append(" tasks in your list.");

            return response.toString();
        } catch (DateTimeParseException e) {
            throw new EdithException(ui.invalidDateTimeError(), 1);
        } catch (IOException e) {
            return "An error occurred while saving updated Edith.task list.";
        }
    }
}
