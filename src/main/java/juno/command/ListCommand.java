package juno.command;

import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    private TaskManager taskManager;
    private ArrayList<Task> tasks;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.tasks = taskManager.getTasksArray();
    }

    @Override
    public void runCommand() throws TaskManagerException {
        if (this.tasks.isEmpty()) {
            throw new TaskManagerException("\uD83C\uDF31 No tasks added yet! Why not plant the first seed? \uD83C\uDF31",
                    TaskManagerException.ErrorType.EMPTY_LIST);
        } else {
            System.out.println("Here's a rundown of all your tasks! \uD83D\uDE0A");
            for (int i = 0; i < this.tasks.size(); i++) {
                String formmattedString = String.format(
                        "%d. %s",
                        (i + 1),
                        this.tasks.get(i).toString()
                );
                System.out.println(formmattedString);
            }
            System.out.println("\uD83C\uDFAF You have " + this.tasks.size() + " tasks in the list. Keep going!");
        }
    }
}
