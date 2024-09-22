package rizz.command;
import rizz.source.TaskList;
import rizz.task.ToDo;

public class AddToDoCommand extends SaveableCommand {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks) {
        if (this.description == null ) {
            return "ToDo cannot be empty";
        }
        ToDo newToDo = new ToDo(this.description, false);
        tasks.addTask(newToDo);
        return "ToDo added: " + newToDo.toString();
    }
}
