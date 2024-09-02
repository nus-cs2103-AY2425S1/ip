package dave.command;

import dave.task.Task;
import dave.task.TaskList;
import dave.storage.Storage;
import dave.ui.Ui;
import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        try {
            tasks.addTask(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println(task);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            storage.amendFile(task);  // Ensure amendFile is accessed via the correct instance
        } catch (IOException e) {
            ui.showLine();
            System.out.println("An error occurred while saving the task to the file.");
        } catch (Exception e) {
            ui.showLine();
            System.out.println("An unexpected error occurred while adding the task.");
        }
    }
}
