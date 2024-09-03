package toothless.command;

import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.exceptions.MissingIndexExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.ui.Ui;

public class MarkUndoneCommand extends Command {

        private String description;

        public MarkUndoneCommand(String taskNumber) {
            this.description = description;
        }

        @Override
        public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
            if(description.isEmpty()) {
                throw new MissingIndexExceptions("mark", "mark <index>");
            }
            int markIndex = Integer.parseInt(description);
            taskList.markUndone(markIndex);
            storage.saveTask(taskList.getList());
        }
}
