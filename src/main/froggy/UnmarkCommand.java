package froggy;

public class UnmarkCommand extends Command{

    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() <= 7) {
            System.out.println("Error: Please enter the index of the task to be unmarked.");
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
                    System.out.println("Error: Invalid index. Please enter an index in range.");
                    ui.showLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid index after 'unmark'.");
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
