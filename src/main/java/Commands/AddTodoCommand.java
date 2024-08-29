package Commands;
import Exceptions.DelphiException;
import Exceptions.EmptyInputException;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;
import Tasks.Todo;

public class AddTodoCommand extends Command{
    public AddTodoCommand(String s) {
        super(s);
    }
    public void execute(TaskList t, Storage s, UI ui) throws DelphiException {
        Todo newTodo = new Todo(input.substring(5));
        t.addTask(newTodo); //will go away once addTask is just an adding to list method
        s.writeToHardDisk(t.getTasks());
        UI.taskMessage(newTodo, t.getTasks().size());
    }
}
