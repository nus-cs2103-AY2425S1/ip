package johncena.commands;

import java.util.ArrayList;

import johncena.tasks.Task;

/**
 * The {@code ListCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "list" command, which lists all tasks in the task list.
 */
public class ListCommand implements Command {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new {@code ListCommand} with the specified task list.
     *
     * @param tasks the list of tasks
     */
    public ListCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the "list" command. Lists all tasks in the task list.
     */
    @Override
    public String execute() {

        StringBuilder sb = new StringBuilder();
        sb.append(" Alright, Champ! Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        sb.append("Keep pushing forward and never give up!\n");
        return sb.toString();
    }
}
