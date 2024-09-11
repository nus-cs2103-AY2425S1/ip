package ipman.commands;

import ipman.models.Task;
import ipman.models.TaskList;
import ipman.models.ToDo;
import ipman.ui.Ui;

/**
 * Creates a <code>ToDo</code> inside <code>Context</code>'s
 * <code>TaskList</code>
 * @see ToDo
 * @see Context
 * @see TaskList
 */
public class CreateToDoCommand implements Command {
    private final String name;

    /**
     * Creates command that creates a particular to-do
     * @param name to-do name
     * @see ToDo
     */
    public CreateToDoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();
        Task task = new ToDo(name);
        tasks.add(task);

        Ui ui = context.ui();
        ui.showMessage(String.format("""
            Got it. I've added this task:
            %s
            Now you have %d tasks in the list.
        """, task, tasks.size()));
    }
}
