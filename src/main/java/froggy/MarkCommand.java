package froggy;

/**
 * Marks a task as done based on index.
 */
public class MarkCommand extends Command {

    private String input;

    /**
     * Constructor that takes in raw input given for Mark command.
     *
     * @param input raw input.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() <= 5) {
            System.out.println("[INFO] Error: Please enter the index of the task to be marked.");
            ui.showLine();
        } else {
            try {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (taskList.isInRange(index)) {
                    taskList.setDone(index, true);
                    System.out.println("Marked the following task as done:");
                    taskList.printTask(index);
                    ui.showLine();
                } else {
                    System.out.println("[INFO] Error: Invalid index. Please enter an index in range.");
                    ui.showLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("[INFO] Error: Please enter a valid index after 'mark'.");
                ui.showLine();
            }
        }
        storage.saveTasks(taskList.getTasks());
    }

    @Override
    public String executeAndGetOutput(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() <= 5) {
            return "[INFO] Error: Please enter the index of the task to be marked.\n" + ui.getLine();
        } else {
            try {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (taskList.isInRange(index)) {
                    taskList.setDone(index, true);
                    storage.saveTasks(taskList.getTasks());
                    return "Marked the following task as done:\n" + taskList.getTask(index).toString()
                            + "\n" + ui.getLine();
                } else {
                    return "[INFO] Error: Invalid index. Please enter an index in range.\n" + ui.getLine();
                }
            } catch (NumberFormatException e) {
                return "[INFO] Error: Please enter a valid index after 'mark'.\n" + ui.getLine();
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
