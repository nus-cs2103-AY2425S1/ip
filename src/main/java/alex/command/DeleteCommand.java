package alex.command;

import java.util.Scanner;

import java.io.IOException;

import alex.TaskList;
import alex.Ui;
import alex.Storage;
import alex.AlexException;

public class DeleteCommand extends Command {
    private Scanner lineScanner;

    public DeleteCommand(Scanner lineScanner) {
        this.lineScanner = lineScanner;
    }
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

    @Override
    public boolean isExit() {
        return false;
    }
}
