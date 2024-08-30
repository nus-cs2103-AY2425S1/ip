package alex.command;

import java.util.Scanner;

import java.io.IOException;

import alex.TaskList;
import alex.Ui;
import alex.Storage;
import alex.AlexException;

public class MarkCommand extends Command{
    private Scanner lineScanner;
    private String mark;
    public MarkCommand(Scanner lineScanner, String response) {
        this.lineScanner = lineScanner;
        this.mark = response;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException {
        if (!lineScanner.hasNext()) {
            throw new AlexException("Oh no! Please provide an integer number after 'mark' or 'unmark'\n" +
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
            throw new AlexException("Oh no! Please only provide an integer number after 'mark' or 'unmark'\n" +
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

    @Override
    public boolean isExit() {
        return false;
    }
}
