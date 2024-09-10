package momo.command;

import momo.Ui;
import momo.exception.InvalidCommandException;
import momo.task.Task;
import momo.task.TaskList;

public class FindCommand extends Command {
    public static void run(String input, TaskList tasks, Ui ui) throws InvalidCommandException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";

        StringBuilder matchingTasks = new StringBuilder();
        String desc = input.substring(4).trim().toLowerCase();

        if (desc.isEmpty()) {
            throw new InvalidCommandException("What are you trying to find?! Don't make me come and find you...");
        }

        int count = 1;
        for (Task task : tasks.getTaskList()) {
            if (task.getTask().toLowerCase().contains(desc)) {
                matchingTasks.append(count).append(". ").append(task).append("\n");
            }
            count++;
        }

        if (matchingTasks.isEmpty()) {
            ui.printDialogue("There are no matching tasks in your list");
        } else {
            ui.printDialogue("Here are the matching tasks in your list:");
            ui.printDialogue(matchingTasks.toString());
        }
    }
}
