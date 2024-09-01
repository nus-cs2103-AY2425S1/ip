package jbot.command;

import jbot.task.Task;
import jbot.util.TaskList;

public class UnmarkCommand implements JBotCommand {
    private static final UnmarkCommand instance = new UnmarkCommand();
    private UnmarkCommand() {
    };
    public static UnmarkCommand getInstance() {
        return UnmarkCommand.instance;
    }
    @Override
    public void run(String input) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        Task task = TaskList.get(taskIndex);
        task.unmarkAsDone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("  %1$s\n", task);
    }
}
