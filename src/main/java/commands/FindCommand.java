package commands;

import applemazer.Storage;
import applemazer.TaskList;
import tasks.Task;

/**
 * Class that represents the "find" command.
 */
public class FindCommand extends Command {
    private final String desc;

    /**
     * Constructor for a {@code FindCommand} object.
     * <p>
     * @param desc The keyword in the task description to search for.
     */
    public FindCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the "find" command which finds a task by searching for a keyword in the task description.
     * @param tasks   The task list to use.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        int taskNumber = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(desc)) {
                System.out.println(taskNumber + "." + task.getStatusIcon() + task);
            }
            taskNumber++;
        }
        System.out.println(); // Leave an empty line.
    }

    @Override
    public boolean continueProcessing() {
        return true;
    }
}
