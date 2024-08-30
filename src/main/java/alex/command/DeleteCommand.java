package alex.command;

import java.util.Scanner;

import java.io.IOException;

import alex.TaskList;
import alex.Ui;
import alex.Storage;
import alex.AlexException;

/**
 * Represents the command by user to delete a Task from Tasklist.
 */
public class DeleteCommand extends Command {
    private Scanner lineScanner;

    public DeleteCommand(Scanner lineScanner) {
        this.lineScanner = lineScanner;
    }

    /**
     * {@inheritDoc}
     *
     * Deletes the Task from TaskList, save the changes to file and informs user.
     *
     * @param tasks Tasklist that holds the list of Tasks.
     * @param ui Ui object that displays messages to user based on action taken by chatbot.
     * @param storage Storage object that saves changes to file.
     * @throws AlexException If user provides wrong input such as not providing an integer number or
     * providing a number that is too small or too big.
     * @throws IOException If there are issues saving the changes to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException {
        if (!lineScanner.hasNext()) {
            throw new AlexException("Oh no! Please provide an integer number after 'delete' indicating the " +
                    "task number to delete!");
        }
        String taskNumberStr = lineScanner.next();
        int taskNumber = 0;

        //handles exception where user write too much
        if (lineScanner.hasNext()) {
            throw new AlexException("Wait! Please only provide a number after 'delete'!");
        }

        //handles case where user doesn't provide a number or not an integer
        try {
            taskNumber = Integer.valueOf(taskNumberStr);
        } catch (NumberFormatException e) {
            throw new AlexException("Oh no! Please only provide an integer number after 'delete' indicating\n" +
                    "the task number to delete!");
        }

        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new AlexException("Oh no! Please provide a correct task number to delete!");
        }

        tasks.delete(taskNumber, storage, ui);
    }

    /**
     * {@inheritDoc}
     *
     * @return false as user is not done yet.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
