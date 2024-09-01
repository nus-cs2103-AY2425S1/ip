import exceptions.AstorException;
import exceptions.EmptyTaskInfoException;

import java.io.IOException;

public class TodoCommand extends Command {
    private String info;

    public TodoCommand(String info) {
        this.info = info;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AstorException, IOException {
        String s1 = info.substring(4).trim();
        if (s1.isEmpty()) {
            throw new EmptyTaskInfoException();
        } else {
            Task task = new Todo(s1);
            String s = taskList.addTask(task, storage);
            ui.showOutput("Got it. I've added this task:\n  " +
                    s + "\nNow you have " + taskList.size() + " tasks in the list.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
