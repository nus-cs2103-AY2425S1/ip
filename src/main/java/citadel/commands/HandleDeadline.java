package citadel.commands;

import citadel.Task.Task;
import citadel.Task.TaskList;
import citadel.Task.Deadline;
import citadel.exception.CitadelTaskNoInput;
import citadel.exception.CitadelException;
import citadel.ui.TextUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the command to handle the creation of a deadline task in the Citadel application.
 * This class extends {@link Command} and provides functionality to add a deadline task to the task list.
 */
public class HandleDeadline extends Command {

    /**
     * Constructs a new {@code HandleDeadline} command with the specified input and task list.
     *
     * @param input The user input associated with the deadline command.
     * @param tasks The task list to which the deadline task will be added.
     */
    public HandleDeadline(String input, TaskList tasks) {
        super(input, tasks);
    }

    /**
     * Executes the handle deadline command.
     * <p>
     * This method parses the user input to extract the task description and deadline,
     * creates a new {@link Deadline} task, adds it to the task list, and displays a confirmation message.
     * If the input is incomplete or improperly formatted, a {@link CitadelTaskNoInput} exception is thrown.
     * </p>
     *
     * @throws CitadelException If the input is invalid or an error occurs during the creation of the deadline task.
     */
    @Override
    public void run() throws CitadelException {
        Task t;
        String[] words = input.split(" /by ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }
        String task = words[0].substring(9).trim();
        String deadline = words[1].trim();

        if (task.isEmpty() || deadline.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        LocalDateTime deadlineFormatted = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        t = new Deadline(task, deadlineFormatted);
        this.tasks.add(t);
        TextUI.printTask(t, tasks);
    }
}
