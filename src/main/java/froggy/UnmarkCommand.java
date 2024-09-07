package froggy;

/**
 * Marks a task as undone based on index.
 */
public class UnmarkCommand extends Command {

    private String input;

    /**
     * Constructor that takes in raw input given for Unmark command.
     *
     * @param input raw input.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() <= 7) {
            System.out.println("[INFO] Error: Please enter the index of the task to be unmarked.");
            ui.showLine();
        } else {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < taskList.getSize()) {
                    taskList.setDone(index, false);
                    System.out.println("Marked the following task as undone:");
                    taskList.printTask(index);
                    ui.showLine();
                } else {
                    System.out.println("[INFO] Error: Invalid index. Please enter an index in range.");
                    ui.showLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("[INFO] Error: Please enter a valid index after 'unmark'.");
                ui.showLine();
            }
        }
        storage.saveTasks(taskList.getTasks());
    }

    @Override
    public String executeAndGetOutput(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() <= 7) {
            return "[INFO] Error: Please enter the index of the task to be unmarked.\n" + ui.getLine();
        } else {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < taskList.getSize()) {
                    taskList.setDone(index, false);
                    storage.saveTasks(taskList.getTasks());
                    return "Marked the following task as undone:\n" + taskList.getTask(index).toString()
                            + "\n" + ui.getLine();
                } else {
                    System.out.println("[INFO] Error: Invalid index. Please enter an index in range.");
                    ui.showLine();
                    return "[INFO] Error: Invalid index. Please enter an index in range.\n" + ui.getLine();
                }
            } catch (NumberFormatException e) {
                return "[INFO] Error: Please enter a valid index after 'unmark'.\n" + ui.getLine();
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
