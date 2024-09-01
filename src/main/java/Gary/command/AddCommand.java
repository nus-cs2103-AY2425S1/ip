package Gary.command;

import java.io.IOException;
import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;
import Gary.task.Task;

public class AddCommand extends Command {
        protected Task task;

        public AddCommand(Task task) {
            this.task = task;
        }
        @Override
        public void execute(TaskList taskLists, Ui ui, Storage storage) {
            taskLists.addTask(task);
            ui.addTask(task, taskLists.size());
            try {
                storage.saveTask(taskLists);
            } catch(IOException e) {

            }
        }

        @Override
        public boolean isBye() {
            return false;
        }
}
