package juno.command;

import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private TaskManager taskManager;
    private ArrayList<Task> tasks;
    private String userInput;

    public FindCommand(String userInput, TaskManager taskManager) {
        this.userInput = userInput;
        this.taskManager = taskManager;
        this.tasks = taskManager.getTasksArray();
    }

    @Override
    public void runCommand() throws TaskManagerException {
        if (this.tasks.isEmpty()) {
            throw new TaskManagerException("\uD83C\uDF31 No tasks added yet! Why not plant the first seed? " +
                    "\uD83C\uDF31", TaskManagerException.ErrorType.EMPTY_LIST);
        } else {
            String taskString;
            try {
                taskString = userInput.split("\\s+", 2)[1];
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. " +
                        "Please enter a valid task string after find command. " +
                        "(\uD83D\uDCA1 Tip: You can type \"list\" to see your tasks)",
                        TaskManagerException.ErrorType.INVALID_FIND_TASK);
            }
            ArrayList<Task> tasksFound = new ArrayList<>();
            for (Task task : this.tasks) {
                if (task.getDescription().toLowerCase().contains(taskString.toLowerCase()) ||
                        task.getTaskType().toLowerCase().contains(taskString.toLowerCase())) {
                    tasksFound.add(task);
                }
            }

            if (tasksFound.isEmpty()) {
                throw new TaskManagerException("No matching tasks found for the keyword: " + taskString,
                        TaskManagerException.ErrorType.NO_TASK_FOUND);
            } else {
                System.out.println("Here are the matching task(s) in your list:");
                for (int i = 0; i < tasksFound.size(); i++) {
                    String formmattedString = String.format(
                            "%d. %s",
                            (i + 1),
                            tasksFound.get(i).toString()
                    );
                    System.out.println(formmattedString);
                }
                System.out.println("\uD83C\uDFAF I have found " + tasksFound.size() + " tasks related to " +
                        "your prompt.");
            }
        }
    }
}
