import java.io.IOException;

public class MarkCommand extends Command {
    Boolean isMark;
    public MarkCommand(Boolean isMark) {
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isMark) {
            // Mark Command
            int index = ui.getMark();
            Task task = tasks.getTask(index-1);
            task.setStatus(true);

            // Ui response
            ui.markResponse(task);

            // Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }
        } else {
            // Unmark Command
            int index = ui.getMark();
            Task task = tasks.getTask(index-1);
            task.setStatus(false);

            // Ui response
            ui.unmarkResponse(task);

            // Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }
        }
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
