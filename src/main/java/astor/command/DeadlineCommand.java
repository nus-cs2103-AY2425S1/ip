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

public class DeadlineCommand extends Command {
    private String info;

    public DeadlineCommand(String info) {
        this.info = info;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AstorException, IOException {
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
            ui.showOutput("Got it. I've added this astor.task:\n  "
                    + s1 + "\nNow you have " + taskList.size() + " tasks in the list.");
        }
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
