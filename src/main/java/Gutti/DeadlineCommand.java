package Gutti;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a Deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param input The input string containing the task description and due date.
     * @throws GuttiException If the input format is invalid or the date format is incorrect.
     */
    public DeadlineCommand(String input) throws GuttiException {
        String[] parts = input.split("/by");
        if (parts.length != 2) {
            throw new GuttiException("Invalid format. Use: deadline <task description> /by <date/time>");
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
        try {
            DateTimeUtil.parseDateTime(by);
        } catch (DateTimeParseException e) {
            throw new GuttiException("Invalid date/time format. Accepted formats: yyyy-MM-dd HHmm, d/MM/yyyy HHmm, MMM dd yyyy h:mma, dd/mm/yyyy HHmm");
        }
    }

    /**
     * Executes the DeadlineCommand by adding a Deadline task to the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI object to handle user interactions.
     * @param storage The Storage object to handle saving tasks to a file.
     * @throws GuttiException If there is an error during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        Task deadlineTask = new Deadline(description, by, false);
        tasks.addTask(deadlineTask);
        storage.saveTasksToFile(tasks.getTasks());
        ui.showTaskList(tasks);
    }

    /**
     * Returns boolean on whether this command should terminate the application.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}