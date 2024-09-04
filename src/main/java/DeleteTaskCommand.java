import java.io.IOException;

public class DeleteTaskCommand extends Command {
    private final String input;

    public DeleteTaskCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList taskList, TaskFileManager manager, UserInterface ui) {
        String[] inputComponents = input.split(" ", 2);
        if (inputComponents.length < 2) {
            ui.showMessage("Please specify the task you want to delete.");
            return;
        }

        String rawTaskNumber = inputComponents[1];
        try {
            int taskNumber = Integer.parseInt(rawTaskNumber);
            Task deletedTask = taskList.deleteTask(taskNumber);
            saveTasks(taskList, manager);

            ui.showMessage(String.format("Task:%n%s%nhas been deleted.", deletedTask));
        } catch (NumberFormatException e) {
            ui.showMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            ui.showMessage(String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            ui.showMessage(String.format("Failed to delete task %s from the list. Please try again.", rawTaskNumber));
        }
    }

}
