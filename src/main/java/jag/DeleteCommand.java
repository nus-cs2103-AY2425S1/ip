package jag;

import java.io.IOException;

public class DeleteCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // jag.Task
        int index = ui.getDeleteIndex();

        // jag.Ui response + deletion
        Task task = tasks.getTask(index - 1);
        ui.deleteResponse(task, tasks, tasks.size() - 1);
        tasks.deleteTask(index - 1);

        // jag.Storage
        try {
            storage.write(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
