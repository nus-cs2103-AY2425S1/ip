import java.io.IOException;
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        try {
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            System.out.println("Noted. I've removed this task: " + task);
            System.out.println(task);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
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
