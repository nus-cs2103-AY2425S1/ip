import java.util.ArrayList;

public class MarkCommand implements JBotCommand {
    private static final MarkCommand instance = new MarkCommand();
    private MarkCommand() {
    };
    public static MarkCommand getInstance() {
        return MarkCommand.instance;
    }
    @Override
    public void run(String input, ArrayList<Task> taskList) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        Task task = taskList.get(taskIndex);
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  %1$s\n", task);
    }
}
