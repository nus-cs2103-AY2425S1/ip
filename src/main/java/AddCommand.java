import java.util.ArrayList;

public class AddCommand implements JBotCommand {
    private static final AddCommand instance = new AddCommand();
    private AddCommand() {
    };
    public static AddCommand getInstance() {
        return AddCommand.instance;
    }
    @Override
    public void run(String input, ArrayList<Task> taskList) {
        Task task = new Task(input);
        taskList.add(task);
        System.out.printf("added: %1$s \n", task);
    }
}
