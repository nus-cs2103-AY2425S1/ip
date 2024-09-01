package jbot.command;

import jbot.task.Task;
import jbot.util.TaskList;

public class DeleteCommand extends AddCommand {
    private static final DeleteCommand instance = new DeleteCommand();
    private DeleteCommand() {
    };
    public static DeleteCommand getInstance() {
        return DeleteCommand.instance;
    }
    @Override
    public void run(String input) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = TaskList.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.printf("  %1$s\n", task);
        super.run(input);
    }
}
