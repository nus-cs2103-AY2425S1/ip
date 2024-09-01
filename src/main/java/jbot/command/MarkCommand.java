package jbot.command;

import jbot.task.Task;
import jbot.util.TaskList;

public class MarkCommand implements JBotCommand {
    private static final MarkCommand instance = new MarkCommand();
    private MarkCommand() {
    };
    public static MarkCommand getInstance() {
        return instance;
    }
    @Override
    public void run(String input) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        Task task = TaskList.get(taskIndex);
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  %1$s\n", task);
    }
}
