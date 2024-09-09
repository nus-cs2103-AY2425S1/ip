package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.EmptyDeadlineException;
import astor.exception.EmptyTaskInfoException;
import astor.task.Deadline;
import astor.task.Task;

import java.io.IOException;

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
        String s = info.substring(8).trim();
        if (s.isEmpty()) {
            throw new EmptyTaskInfoException();
        } else {
            String[] stringArr = s.split("/by");
            if (stringArr.length != 2) {
                throw new EmptyDeadlineException();
            }
            Task task = new Deadline(stringArr[0].trim(), stringArr[1].trim());
            String s1 = taskList.addTask(task, storage);
            ui.showOutput("Got it. I've added this task:\n  "
                    + s1 + "\nNow you have " + taskList.size() + " tasks in the list.");
            return "Got it. I've added this task:\n  "
                    + s1 + "\nNow you have " + taskList.size() + " tasks in the list.";
        }
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
