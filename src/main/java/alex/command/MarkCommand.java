package alex.command;

import java.util.Scanner;

import java.io.IOException;

import alex.TaskList;
import alex.Ui;
import alex.Storage;
import alex.AlexException;

/**
 * Represents the command by user to mark or unmark a Task.
 */
public class MarkCommand extends Command{
    private Scanner lineScanner;
    private String mark;
    public MarkCommand(Scanner lineScanner, String response) {
        this.lineScanner = lineScanner;
        this.mark = response;
    }

    /**
     * {@inheritDoc}
     *
     * Marks or unmarks a Task, save changes to file and inform user.
     *
     * @param tasks Tasklist that holds the list of Tasks.
     * @param ui Ui object that displays messages to user based on action taken by chatbot.
     * @param storage Storage object that saves changes to file.
     * @throws AlexException If user enters wrong input, such as not providing a number or providing a
     * number that is too big or too small.
     * @throws IOException If there are issues saving changes to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException {
        if (!lineScanner.hasNext()) {
            throw new AlexException("Oh no! Please provide an integer number after 'mark' or 'unmark' " +
                    "indicating the task number to mark or unmark!");
        }
        String taskNumberStr = lineScanner.next();
        int taskNumber;

        //handles exception where user write too much
        if (lineScanner.hasNext()) {
            throw new AlexException("Wait! Please only provide a number after 'mark' or 'unmark'!");
        }

        //handles case where user doesn't provide a number or not an integer
        try {
            taskNumber = Integer.valueOf(taskNumberStr);
        } catch (NumberFormatException e) {
            throw new AlexException("Oh no! Please only provide an integer number after 'mark' or 'unmark' " +
                    "indicating the task number to mark or unmark!");
        }

        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new AlexException("Oh no! Please provide a correct task number to mark or unmark!");
        }

        if (this.mark.equals("mark")) {
            tasks.mark(taskNumber, storage, ui);
        } else {
            tasks.unmark(taskNumber, storage, ui);
        }
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
