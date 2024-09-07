package froggy;

/**
 * Adds a Todo to the task list.
 */
public class AddTodoCommand extends Command {

    private final String input;

    /**
     * Constructor that takes in raw input as String.
     *
     * @param input raw input.
     */
    public AddTodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() == 5) {
            System.out.println("Error: No description for ToDo task.");
            System.out.println("Please input a description for the task.");
            ui.showLine();
        } else {
            String desc = input.substring(5);
            Todo current = new Todo(desc);
            taskList.add(current, storage);
            System.out.println("Added this task:");
            System.out.println(current.toString());
            ui.showLine();
        }
    }

    @Override
    public String executeAndGetOutput(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() == 5) {
            System.out.println("Error: No description for ToDo task.");
            System.out.println("Please input a description for the task.");
            ui.showLine();
            return "[INFO] Error: No description for ToDo task.\n"
                    + "Please input a description for the task.\n" + ui.getLine();
        } else {
            String output = "";
            String desc = input.substring(5);
            Todo current = new Todo(desc);
            taskList.add(current, storage);
            output = current.toString() + "\n";
            return "Added this task:\n" + output + ui.getLine();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
