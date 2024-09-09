package handsome.command;

import java.io.IOException;

import handsome.Storage;
import handsome.TaskList;
import handsome.Ui;
import handsome.exception.HandsomeException;
import handsome.exception.InvalidTaskException;
import handsome.task.Deadline;

/**
 * The DeadlineCommand class handles the "deadline" command, which allows
 * users to add a new deadline task with a description and due date to the Handsome
 * chatBot's task list.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    /**
     * Constructs a DeadlineCommand object by parsing the user input to
     * extract the deadline task description and due date.
     * The input must be in the format: {@code deadline <description> /by <due date>}.
     * If the format is incorrect or missing required information, an exception is thrown.
     *
     * @param input The input string containing the deadline command details.
     * @throws InvalidTaskException If the input format is incorrect or required fields are missing.
     */
    public DeadlineCommand(String input) throws InvalidTaskException {
        try {
            String[] temp = input.split("/by");
            if (temp[0].substring(9).trim().isEmpty()) {
                throw new InvalidTaskException("You need provide task description for deadline. "
                        + "Type prompt to check the correct command.");
            }
            description = temp[0].substring(9).trim();
            deadline = temp[1].trim();
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new InvalidTaskException("Invalid format for deadline! Type prompt to check the correct command.");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws HandsomeException, IOException {
        tasks.add(new Deadline(description, "[ ]", deadline, false));
        storage.writeToFile(tasks);
        return ui.showTaskText(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
