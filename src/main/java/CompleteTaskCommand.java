import java.io.IOException;

public class CompleteTaskCommand extends Command{
    private final String input;

    public CompleteTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, TaskFileManager manager, UserInterface ui) {
        String[] inputComponents = input.split(" ", 2);
        if (inputComponents.length < 2) {
            ui.showMessage("Please specify the task you want to mark as complete.");
            return;
        }

        String rawTaskNumber = inputComponents[1];
        try {
            int taskNumber = Integer.parseInt(rawTaskNumber);
            Task completeTask = taskList.completeTask(taskNumber);
            saveTasks(taskList, manager);
            ui.showMessage(
                    String.format("I've marked the task as complete:%n%s", completeTask));
        } catch (NumberFormatException e) {
            ui.showMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            ui.showMessage(String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            ui.showMessage(String.format("Failed to mark task %s as complete. Please try again later.", rawTaskNumber));
        }
    }
}
