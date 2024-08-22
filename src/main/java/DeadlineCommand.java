import java.util.ArrayList;

public class DeadlineCommand extends AddCommand {
    private static final DeadlineCommand instance = new DeadlineCommand();
    private DeadlineCommand() {
    };
    public static DeadlineCommand getInstance() {
        return DeadlineCommand.instance;
    }
    @Override
    public void run(String input, ArrayList<Task> taskList) {
        Task task = new DeadlineTask(input);
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %1$s \n", task);
        super.run(input, taskList);
    }
}
