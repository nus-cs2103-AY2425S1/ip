package citadel.commands;

import citadel.Task.Task;
import citadel.Task.TaskList;
import citadel.Task.Event;
import citadel.exception.CitadelException;
import citadel.exception.CitadelTaskNoInput;
import citadel.ui.TextUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the command to handle the creation of an event task in the Citadel application.
 * This class extends {@link Command} and provides functionality to add an event task to the task list.
 */
public class HandleEvent extends Command {

    /**
     * Constructs a new {@code HandleEvent} command with the specified input and task list.
     *
     * @param input The user input associated with the event command.
     * @param tasks The task list to which the event task will be added.
     */
    public HandleEvent(String input, TaskList tasks) {
        super(input, tasks);
    }

    /**
     * Executes the handle event command.
     * <p>
     * This method parses the user input to extract the task description, start time, and end time,
     * creates a new {@link Event} task, adds it to the task list, and displays a confirmation message.
     * If the input is incomplete or improperly formatted, a {@link CitadelTaskNoInput} exception is thrown.
     * If the start time is after the end time, a warning message is displayed instead of creating the event.
     * </p>
     *
     * @throws CitadelException If the input is invalid or an error occurs during the creation of the event task.
     */
    @Override
    public void run() throws CitadelException {
        Task t;
        String[] words = this.input.split(" /from ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String task = words[0].substring(5).trim();
        String[] timeline = words[1].split(" /to ");

        if (timeline.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String from = timeline[0].trim();
        String to = timeline[1].trim();

        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        LocalDateTime fromFormatted = LocalDateTime
                .parse(from,
                        DateTimeFormatter
                                .ofPattern("dd/MM/yyyy HH:mm"));
        LocalDateTime toFormatted = LocalDateTime
                .parse(to,
                        DateTimeFormatter
                                .ofPattern("dd/MM/yyyy HH:mm"));

        if (fromFormatted.isAfter(toFormatted)) {
            System.out.println("The start time must be before the end time!");
        } else {
            t = new Event(task, fromFormatted, toFormatted);
            this.tasks.add(t);
            TextUI.printTask(t, tasks);
        }
    }
}
