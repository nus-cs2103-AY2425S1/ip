package king.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.ui.Ui;
import king.task.Deadline;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * Creates a DeadlineCommand with the specified input.
     *
     * @param input The input string containing the task description and deadline date.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the deadline command by parsing the input to create a Deadline task,
     * adding it to the task list, and saving the task list to storage.
     *
     * @param tasks The task list to add the new deadline task to.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the updated task list.
     * @throws KingException If the input format is incorrect or an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        String[] parts = this.input.split(" /by ");
        if (parts.length == 2) {
            String description = parts[0].trim();
            String by = parts[1].trim();
            try {
                LocalDateTime byDate = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                Deadline deadline = new Deadline(description, byDate);
                tasks.add(deadline);
                storage.save(tasks.getTaskList());
                return ui.showTaskAdded(deadline, tasks.size());
            } catch (DateTimeParseException e) {
                throw new KingException("Invalid deadline date format. Please use yyyy-MM-dd HHmm.");
            }
        } else {
            throw new KingException("Invalid deadline format. Please use the format: description /by yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code false} as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
