package astor.command;

import java.io.IOException;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.EmptyDeadlineException;
import astor.exception.EmptyTaskInfoException;
import astor.task.Deadline;
import astor.task.Task;

/**
 * Represents a command for creating a deadline task.
 *
 * Allows the command to be executed to be stored in taskList, storage and sends reply to user through ui,
 * and shows that this is not a terminal command.
 *
 * @author Choi Yi Hao
 */
public class DeadlineCommand extends Command {
    private String info;

    public DeadlineCommand(String info) {
        this.info = info;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AstorException, IOException {
        assert taskList != null : "taskList must not be null";
        assert ui != null : "ui must not be null";
        assert storage != null : "storage must not be null";

        String s = info.substring(8).trim();
        if (s.isEmpty()) {
            throw new EmptyTaskInfoException();
        } else {
            String[] stringArr = s.split("/by");
            if (stringArr.length != 2) {
                throw new EmptyDeadlineException();
            }
            Task task = new Deadline(stringArr[0].trim(), stringArr[1].trim());
            String output = taskList.addTask(task, storage);
            ui.showOutput(output);
            return output;
        }
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
