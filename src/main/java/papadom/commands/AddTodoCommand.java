package papadom.commands;
import papadom.Deadline;
import papadom.Exceptions.NoTaskException;
import papadom.Storage.*;
import papadom.Todo;
import papadom.Ui;
public class AddTodoCommand extends Command {
    private String text;
    public AddTodoCommand(String text) {
        this.text = text;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoTaskException {
        Todo todoTask = new Todo(this.text.substring(5));
        ui.output(taskList.addToList(todoTask));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
