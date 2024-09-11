package froggy;

/**
 * Deletes a task from task list based on index.
 */
public class DeleteCommand extends Command {

    private String input;

    /**
     * Constructor that takes in raw input.
     *
     * @param input raw input.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() <= 7) {
            System.out.println("[INFO] Error: Please enter the index of the task to be deleted.");
            ui.showLine();
        } else {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < taskList.getSize()) {
                    System.out.println("Deleted the following task:");
                    taskList.printTask(index);
                    ui.showLine();
                    taskList.removeTask(index);
                    storage.saveTasks(taskList);
                } else {
                    System.out.println("[INFO] Error: Invalid index. Please enter an index in range.");
                    ui.showLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("[INFO] Error: Please enter a valid index after 'delete'.");
                ui.showLine();
            }
        }
    }

    @Override
    public String executeAndGetOutput(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() <= 7) {
            return "[INFO] Error: Please enter the index of the task to be deleted.\n" + ui.getLine();
        } else {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < taskList.getSize()) {
                    String output = taskList.getTask(index).toString();
                    taskList.removeTask(index);
                    storage.saveTasks(taskList);
                    return "Deleted the following task:\n" + output + "\n" + ui.getLine();
                } else {
                    return "[INFO] Error: Invalid index. Please enter an index in range.\n" + ui.getLine();

                }
            } catch (NumberFormatException e) {
                return "[INFO] Error: Please enter a valid index after 'delete'.\n" + ui.getLine();
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
