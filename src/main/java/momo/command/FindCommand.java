package momo.command;

import momo.InvalidCommandException;
import momo.task.Task;
import momo.task.TaskList;

public class FindCommand extends Command {
    public static void run(String input, TaskList tasks) throws InvalidCommandException {
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
            System.out.println("There are no matching tasks in your list");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(matchingTasks);
        }
    }
}
