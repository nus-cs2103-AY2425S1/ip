public class DisplayTasksCommand extends Command {
    public DisplayTasksCommand() {
        // Empty constructor
    }
    @Override
    public void execute(TaskList taskList, TaskFileManager manager, UserInterface ui) {
        if (taskList.getSize() < 1) {
            ui.showMessage("You have no tasks at the moment.");
            return;
        }

        ui.showMessage(String.format("Here's your laundry list:%n%s", taskList));
    }
}
