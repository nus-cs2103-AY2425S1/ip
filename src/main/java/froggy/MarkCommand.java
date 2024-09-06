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
                if (index >= 0 && index < taskList.getSize()) {
                    taskList.setDone(index, true);
                    System.out.println("Marked the following task as done:");
                    taskList.printTask(index);
                    ui.showLine();
                } else {
                    System.out.println("Error: Invalid index. Please enter an index in range.");
                    ui.showLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid index after 'mark'.");
                ui.showLine();
            }
        }
        storage.saveTasks(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
