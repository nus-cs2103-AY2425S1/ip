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
                } else {
                    System.out.println("Error: Invalid index. Please enter an index in range.");
                    ui.showLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid index after 'delete'.");
                ui.showLine();
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
