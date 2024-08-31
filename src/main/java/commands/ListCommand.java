package commands;

import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Execution actions:
     * - Convert all tasks to their string representations
     * - Print to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.length() == 0) {
            ui.printGenericMessage("There are no tasks in the list.");
            return;
        }

        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.length(); i++) {
            listOfTasks
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.getTaskAt(i))
                    .append("\n");
        }

        // remove the last character, which is a new line.
        ui.printGenericMessage(
                listOfTasks.substring(0, Math.max(0, listOfTasks.length() - 1))
        );
    }
}
