package command;

import task.Task;
import task.ToDo;
import tasklist.TaskList;
import ui.Ui;
import exception.ParserException;

public class ToDoCommand implements Command {
    private Task todo;
    public ToDoCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing arguments for todo command");
        }
        
        String description = args[1];
        this.todo = new ToDo(description);
    }

    public void execute(TaskList tasks, Ui ui) {
        tasks.add(this.todo);
        ui.println("A task is added");
        ui.println(this.todo);
    }

    public boolean isExit() { return false; }
}
