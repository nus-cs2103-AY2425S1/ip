package alex.command;

import java.io.IOException;
import java.util.Scanner;

import alex.AlexException;
import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

/**
 * Represents the command by user to mark or unmark a Task.
 */
public class MarkCommand extends Command {
    private Scanner lineScanner;
    private String response;

    /**
     * Constructs a MarkCommand instance.
     *
     * @param lineScanner Scanner object used to read user input.
     * @param response Indicates whether the command is to mark or unmark a task.
     */
    public MarkCommand(Scanner lineScanner, String response) {
        this.lineScanner = lineScanner;
        this.response = response;
    }

    /**
     * {@inheritDoc}
     *
     * Marks or unmarks a Task, saves changes to file, and informs user.
     *
     * @param tasks TaskList that holds the list of Tasks.
     * @param ui Ui object that displays messages to user based on action taken by chatbot.
     * @param storage Storage object that saves changes to file.
     * @return A message indicating the result of the mark/unmark operation.
     * @throws AlexException If user provides incorrect input, such as not providing a number or providing
     *                       a number that is too big or too small.
     * @throws IOException If there are issues saving changes to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException {
        if (!lineScanner.hasNext()) {
            throw new AlexException("Oh no! Please provide an integer number after 'mark' or 'unmark' "
                    + "indicating the task number to mark or unmark!");
        }
        String taskNumberStr = lineScanner.next();
        int taskNumber;

        // Handles case where user provides extra input
        if (lineScanner.hasNext()) {
            throw new AlexException("Wait! Please only provide a number after 'mark' or 'unmark'!");
        }

        // Handles case where user doesn't provide a number or provides an invalid integer
        try {
            taskNumber = Integer.valueOf(taskNumberStr);
        } catch (NumberFormatException e) {
            throw new AlexException("Oh no! Please only provide an integer number after 'mark' or 'unmark' "
                    + "indicating the task number to mark or unmark!");
        }

        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new AlexException("Oh no! Please provide a correct task number to mark or unmark!");
        }

        if (this.response.equals("mark")) {
            return tasks.mark(taskNumber, storage, ui);
        } else {
            return tasks.unmark(taskNumber, storage, ui);
        }
    }

    @Override
    public String getCommandType() {
        return "ChangeMarkCommand";
    }
}

