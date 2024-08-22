import java.util.ArrayList;

public class UnmarkCommand implements JBotCommand {
    private static final UnmarkCommand instance = new UnmarkCommand();
    private UnmarkCommand() {
    };
    public static UnmarkCommand getInstance() {
        return UnmarkCommand.instance;
    }
    @Override
    public void run(String input, ArrayList<Task> taskList) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        Task task = taskList.get(taskIndex);
        task.unmarkAsDone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("  %1$s\n", task);
    }
}
