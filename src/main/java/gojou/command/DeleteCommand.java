package gojou.command;

import java.io.IOException;
import java.util.Scanner;

import gojou.GojouException;
import gojou.Storage;
import gojou.Ui;
import gojou.task.TaskList;

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
     * @throws GojouException If the user provides incorrect input, such as not providing an integer number or
     *     providing a number that is too small or too big.
     * @throws IOException If there are issues saving the changes to the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GojouException, IOException {
        checkIfUserInputIntegerAfterDelete();
        int taskNumber = getTaskNumber();
        checkIfUserEnteredCorrectIntegerInput(tasks, taskNumber);

        return tasks.delete(taskNumber, storage, ui);
    }

    private int getTaskNumber() throws GojouException {
        String taskNumberStr = getTaskNumberString();
        return getTaskNumber(taskNumberStr);
    }

    private int getTaskNumber(String taskNumberStr) throws GojouException {
        int taskNumber;
        // Handles case where user doesn't provide a number or provides a non-integer
        try {
            taskNumber = Integer.valueOf(taskNumberStr);
        } catch (NumberFormatException e) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part "
                    + "of getting stronger. Let's try that again, shall we? Please only provide an integer number "
                    + "after 'delete' indicating the task number to delete!");
        }
        return taskNumber;
    }

    private void checkIfUserEnteredCorrectIntegerInput(TaskList tasks, int taskNumber) throws GojouException {
        // Handles the case where the user enters an invalid task number
        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part "
                    + "of getting stronger. Let's try that again, shall we? Please provide a correct task "
                    + "number to delete!");
        }
    }

    private String getTaskNumberString() throws GojouException {
        String taskNumberStr = lineScanner.next();
        int taskNumber;

        // Handles case where user writes too much
        if (lineScanner.hasNext()) {
            throw new GojouException("Whoa, slow down there, chatterbox! You might be giving me a run for my "
                    + "money. Let's take it one step at a time, okay? Please only provide a number after 'delete'!");
        }
        return taskNumberStr;
    }

    private void checkIfUserInputIntegerAfterDelete() throws GojouException {
        if (!lineScanner.hasNext()) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just "
                    + "part of getting stronger. Let's try that again, shall we? Please provide an integer "
                    + "number after 'delete' indicating the task number to delete!");
        }
    }

    @Override
    public String getCommandType() {
        return "DeleteCommand";
    }
}

