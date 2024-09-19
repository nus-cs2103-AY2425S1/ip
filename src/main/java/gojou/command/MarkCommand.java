package gojou.command;

import java.io.IOException;
import java.util.Scanner;

import gojou.GojouException;
import gojou.Storage;
import gojou.Ui;
import gojou.task.TaskList;

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
     * @throws GojouException If user provides incorrect input, such as not providing a number or providing
     *                       a number that is too big or too small.
     * @throws IOException If there are issues saving changes to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GojouException, IOException {
        checkIfUserEnteredIntegerAfterMark();
        int taskNumber = getTaskNumber();
        checkInvalidTaskNumber(tasks, taskNumber);

        return markTaskAndGetDisplayString(tasks, ui, storage, taskNumber);
    }

    private int getTaskNumber() throws GojouException {
        String taskNumberStr = getTaskNumberStr();
        return getTaskNumber(taskNumberStr);
    }

    private int getTaskNumber(String taskNumberStr) throws GojouException {
        int taskNumber;

        // Handles case where user doesn't provide a number or provides an invalid integer
        try {
            taskNumber = Integer.valueOf(taskNumberStr);
        } catch (NumberFormatException e) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part of "
                    + "getting stronger. Let's try that again, shall we? Please only provide an integer number "
                    + "after 'mark' or 'unmark' indicating the task number to mark or unmark!");
        }
        return taskNumber;
    }

    private String markTaskAndGetDisplayString(TaskList tasks, Ui ui, Storage storage, int taskNumber)
            throws IOException {
        if (this.response.equals("mark")) {
            return tasks.mark(taskNumber, storage, ui);
        } else {
            return tasks.unmark(taskNumber, storage, ui);
        }
    }

    private void checkInvalidTaskNumber(TaskList tasks, int taskNumber) throws GojouException {
        // Handles the case where user provides an invalid task number
        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just "
                    + "part of getting stronger. Let's try that again, shall we? Please provide a correct "
                    + "task number to mark or unmark!");
        }
    }

    private String getTaskNumberStr() throws GojouException {
        String taskNumberStr = lineScanner.next();

        // Handles case where user provides extra input
        if (lineScanner.hasNext()) {
            throw new GojouException("Whoa, slow down there, chatterbox! You might be giving me a run for my money. "
                    + "Let's take it one step at a time, okay? Please only provide a number after 'mark' or 'unmark'!");
        }
        return taskNumberStr;
    }

    private void checkIfUserEnteredIntegerAfterMark() throws GojouException {
        if (!lineScanner.hasNext()) {
            throw new GojouException("Oops, looks like you tripped up! No worries though - mistakes are just part of "
                    + "getting stronger. Let's try that again, shall we? Please provide an integer number after "
                    + "'mark' or 'unmark' indicating the task number to mark or unmark!");
        }
    }

    @Override
    public String getCommandType() {
        return "ChangeMarkCommand";
    }
}

