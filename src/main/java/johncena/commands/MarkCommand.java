package johncena.commands;

import java.util.ArrayList;

import johncena.exceptions.CenaInvalidTaskIndexException;
import johncena.storage.Storage;
import johncena.tasks.Task;

/**
 * The {@code MarkCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "mark" command, which marks a task as done in the task list.
 */
public class MarkCommand implements Command {
    private ArrayList<Task> tasks;
    private int taskIndex;

    /**
     * Constructs a new {@code MarkCommand} with the specified task list and task index.
     *
     * @param tasks the list of tasks
     * @param taskIndex the index of the task to be marked as done
     */
    public MarkCommand(ArrayList<Task> tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the "mark" command. Marks a task as done in the task list.
     *
     * @throws CenaInvalidTaskIndexException if the task index is invalid
     */
    @Override
    public String execute() throws CenaInvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new CenaInvalidTaskIndexException("The task index is invalid.");
        }
        tasks.get(taskIndex).markAsDone();
        Storage.saveTasks(tasks);

        StringBuilder sb = new StringBuilder();
        //sb.append("____________________________________________________________\n");
        sb.append(" Nice! I've marked this task as done:\n");
        sb.append("   ").append(tasks.get(taskIndex)).append("\n");
        //sb.append("____________________________________________________________\n");

        return sb.toString();

//        System.out.println("____________________________________________________________");
//        System.out.println(" Nice! I've marked this task as done:");
//        System.out.println("   " + tasks.get(taskIndex));
//        System.out.println("____________________________________________________________");
//
    }
}
