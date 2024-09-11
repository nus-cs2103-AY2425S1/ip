package alex.command;

import java.io.IOException;
import java.util.Scanner;

import alex.AlexException;
import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

/**
 * Represents the command by user to delete a Task from TaskList.
 */
public class DeleteCommand extends Command {
    private Scanner lineScanner;

    /**
     * Constructs a DeleteCommand instance.
     *
     * @param lineScanner Scanner object used to read user input.
     */
    public DeleteCommand(Scanner lineScanner) {
        this.lineScanner = lineScanner;
    }

    /**
     * {@inheritDoc}
     *
     * Deletes the Task from TaskList, saves the changes to file, and informs the user.
     *
     * @param tasks TaskList that holds the list of Tasks.
     * @param ui Ui object that displays messages to the user based on the action taken by the chatbot.
     * @param storage Storage object that saves changes to the file.
     * @return A message indicating the result of the delete operation.
     * @throws AlexException If the user provides incorrect input, such as not providing an integer number or
     *     providing a number that is too small or too big.
     * @throws IOException If there are issues saving the changes to the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException {
        if (!lineScanner.hasNext()) {
            throw new AlexException("Oh no! Please provide an integer number after 'delete' indicating the "
                    + "task number to delete!");
        }
        String taskNumberStr = lineScanner.next();
        int taskNumber;

        // Handles case where user writes too much
        if (lineScanner.hasNext()) {
            throw new AlexException("Wait! Please only provide a number after 'delete'!");
        }

        // Handles case where user doesn't provide a number or provides a non-integer
        try {
            taskNumber = Integer.valueOf(taskNumberStr);
        } catch (NumberFormatException e) {
            throw new AlexException("Oh no! Please only provide an integer number after 'delete' indicating\n"
                    + "the task number to delete!");
        }

        // Handles the case where the user enters an invalid task number
        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new AlexException("Oh no! Please provide a correct task number to delete!");
        }

        return tasks.delete(taskNumber, storage, ui);
    }

    @Override
    public String getCommandType() {
        return "DeleteCommand";
    }
}

