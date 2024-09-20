package astor.command;

import java.io.IOException;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.EmptyTaskInfoException;
import astor.exception.EmptyTimeException;
import astor.task.Event;
import astor.task.Task;

/**
 * Represents a command to create an Event task.
 *
 * Allows the command to be executed, to store events in taskList, updates storage and sends reply to the ui,
 * and shows that this is not a terminal command
 *
 * @author Choi Yi Hao
 */
public class EventCommand extends Command {
    private String info;

    public EventCommand(String info) {
        this.info = info;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AstorException, IOException {
        assert taskList != null : "taskList must not be null";
        assert ui != null : "ui must not be null";
        assert storage != null : "storage must not be null";

        String s2 = info.substring(5).trim();
        if (s2.isEmpty()) {
            throw new EmptyTaskInfoException();
        } else {
            String[] stringArr = s2.split("/from");
            if (stringArr.length != 2) {
                throw new EmptyTimeException();
            }
            String[] stringArr2 = stringArr[1].split("/to");
            if (stringArr2.length != 2) {
                throw new EmptyTimeException();
            }
            Task task = new Event(stringArr[0].trim(), stringArr2[0].trim(), stringArr2[1].trim());
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
