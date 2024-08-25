package papadom.commands;
import papadom.Exceptions.IncorrectTaskInputFormatException;
import papadom.Storage.*;
import papadom.tasks.Todo;
import papadom.Ui;
public class AddTodoCommand extends Command {
    private String text;
    public AddTodoCommand(String text) {
        this.text = text.trim();
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IncorrectTaskInputFormatException {
        // Check if the text is only "todo" or "todo " without a meaningful task description
        if (this.text.equals("todo") || this.text.equals("todo ")) {
            throw new IncorrectTaskInputFormatException();
        }
        String taskDescription = this.text.substring(5).trim();
        Todo todoTask = new Todo(taskDescription);
        ui.output(taskList.addToList(todoTask));
    }
}
