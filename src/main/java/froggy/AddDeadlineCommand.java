package froggy;

/**
 * Adds a deadline to the task list.
 */
public class AddDeadlineCommand extends Command {

    private final String input;

    /**
     * Constructor that takes in raw input as String.
     *
     * @param input raw input
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = input.toLowerCase().indexOf("/by ");
        if (index != -1) {
            String desc = input.substring(9, index - 1);
            String by = input.substring(index + 4);
            Deadline current = new Deadline(desc, by);
            taskList.add(current, storage);

            System.out.println("Added this task:");
            System.out.println(current.toString());
            ui.showLine();
        }
    }

    @Override
    public String executeAndGetOutput(TaskList taskList, Ui ui, Storage storage) {
        int index = input.toLowerCase().indexOf("/by");
        if (index == -1) {
            String formatErrorMessage = "Please enter a due date using '/by '.\n"
                    + ui.getLine();
            return formatErrorMessage;
        }
        String output = "";
        String desc = input.substring(9, index).trim();
        String by = input.substring(index + 3).trim();
        Deadline current = new Deadline(desc, by);
        if (taskList.isDuplicate(current)){
            return "Duplicate Task found. Adding failed";
        }
        taskList.add(current, storage);
        output = current.toString() + "\n";
        return "Added this task:\n" + output + ui.getLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
