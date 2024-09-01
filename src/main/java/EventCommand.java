import exceptions.AstorException;
import exceptions.EmptyTaskInfoException;
import exceptions.EmptyTimeException;

import java.io.IOException;

public class EventCommand extends Command {
    private String info;

    public EventCommand(String info) {
        this.info = info;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AstorException, IOException {
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
            String s3 = taskList.addTask(task, storage);
            ui.showOutput("Got it. I've added this task:\n  " +
                    s3 + "\nNow you have " + taskList.size() + " tasks in the list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
