package commands;

import bob.ExecutionStack;
import bob.Storage;
import bob.TaskList;
import bob.Ui;
import tasks.Task;
import tasks.ToDo;
import utilities.Printer;

/**
 * This command creates a ToDo task with the specified name and adds it to the task list when run.
 */
public class TodoCommand extends Command {
    public static String[] params = new String[] {
        "todo"
    };
    public static int paramCount = 1;
    private String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, ExecutionStack execStack) {
        ToDo todo = tasks.todo(name);
        execStack.push(this);
        String tasksRemaining = String.format("Now you have %d tasks in the list.", tasks.getSize());
        return Printer.format(new String[] { "Got it. I've added this task:",
            " " + todo.toString(),
            tasksRemaining });
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.pop();
        String response = "Removed task [" + removedTask.toString() + "].";
        return response;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoCommand temp) {
            return this.name.equals(temp.name);
        }
        return false;
    }
}
