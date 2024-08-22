import java.util.ArrayList;

public class EventCommand extends AddCommand {
    private static final EventCommand instance = new EventCommand();
    private EventCommand() {
    };
    public static EventCommand getInstance() {
        return EventCommand.instance;
    }
    @Override
    public void run(String input, ArrayList<Task> taskList) {
        Task task = new EventTask(input);
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %1$s\n", task);
        super.run(input, taskList);
    }
}
