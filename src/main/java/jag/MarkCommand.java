package jag;

import java.io.IOException;

public class MarkCommand extends Command {
    Boolean isMark;
    public MarkCommand(Boolean isMark) {
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isMark) {
            // Mark jag.Command
            int index = ui.getMark();
            Task task = tasks.getTask(index-1);
            task.setStatus(true);

            // jag.Ui response
            ui.markResponse(task);

            // jag.Storage
            try {
                storage.write(tasks);
            } catch (IOException io) {
                System.out.println("Error in writing history" + io.getMessage());
            }
        } else {
            // Unmark jag.Command
            int index = ui.getMark();
            Task task = tasks.getTask(index-1);
            task.setStatus(false);

            // jag.Ui response
            ui.unmarkResponse(task);

            // jag.Storage
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
