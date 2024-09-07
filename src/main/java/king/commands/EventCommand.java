package king.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;
import king.task.Event;

/**
 * Represents a command to create an event and add it to the task list.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * Creates an EventCommand with the specified input.
     *
     * @param input The input string containing the event details.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the event command by creating an event with the specified details,
     * adding it to the task list, saving the updated task list to storage, and
     * displaying the updated task list.
     *
     * @param tasks The task list to which the event will be added.
     * @param ui The user interface to display messages and the updated task list.
     * @param storage The storage to save the updated task list.
     * @throws KingException If the input format is incorrect or the date format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        String[] parts = input.split(" /from | /to ");
        if (parts.length == 3) {
            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            try {
                LocalDateTime fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                LocalDateTime toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                Event event = new Event(description, fromDate, toDate);
                tasks.add(event);
                ui.showTaskAdded(event, tasks.size());
                storage.save(tasks.getTaskList());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid event date format. Please use yyyy-MM-dd HHmm.");
            }
        } else {
            System.out.println("Invalid event date format. Please use yyyy-MM-dd HHmm.");
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
