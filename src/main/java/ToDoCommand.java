import java.util.ArrayList;

public class ToDoCommand extends AddCommand {
    private static final ToDoCommand instance = new ToDoCommand();
    private ToDoCommand() {
    };
    public static ToDoCommand getInstance() {
        return ToDoCommand.instance;
    }
    @Override
    public void run(String input, ArrayList<Task> taskList) {
        try {
            Task task = new ToDoTask(input);
            taskList.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.printf("  %1$s\n", task);
            super.run(input, taskList);
        } catch (EmptyToDoDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }
}
