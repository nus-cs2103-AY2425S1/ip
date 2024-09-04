import java.io.IOException;

public class UncompleteTaskCommand extends Command {
    private final String input;
    public UncompleteTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, TaskFileManager manager, UserInterface ui) {
        String[] inputComponents = input.split(" ", 2);
        if (inputComponents.length < 2) {
            ui.showMessage("Please specify the task you want to mark as incomplete.");
            return;
        }

        String rawTaskNumber = inputComponents[1];
        try {
            int taskNumber = Integer.parseInt(rawTaskNumber);
            Task incompleteTask = taskList.uncompleteTask(taskNumber);
            saveTasks(taskList, manager);

            ui.showMessage(String.format("Changed your mind? The task is set to incomplete:%n%s", incompleteTask));
        } catch (NumberFormatException e) {
            ui.showMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            ui.showMessage(String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            ui.showMessage(String.format("Failed to mark task %s as incomplete. Please try again later.",
                    rawTaskNumber));
        }
    }
}
