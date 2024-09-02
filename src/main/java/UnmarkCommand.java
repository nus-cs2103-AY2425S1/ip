import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {

        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        try {
            Task task = tasks.getTask(taskIndex - 1);
            task.markAsNotDone();
            ui.showLine();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(task);
            storage.saveFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showLine();
            System.out.println("Oh no! You have entered an invalid number. Please try again.");
        } catch (IOException e) {
            ui.showLine();
            System.out.println("An error occurred while saving the task to the file.");
        } catch (Exception e) {
            ui.showLine();
            System.out.println("An unexpected error occurred while marking the task.");
        }
    }
}
