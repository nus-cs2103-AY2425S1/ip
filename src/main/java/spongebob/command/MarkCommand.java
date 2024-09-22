package spongebob.command;

import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.task.Task;
import spongebob.ui.Ui;

/**
 * Command to mark or unmark a task
 */
public class MarkCommand extends Command {

    private String[] arguments;
    private int index;

    public MarkCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     *  Executes the command, checks the first argument and marks / unmarks the task accordingly
     * @param taskList  Tasklist of Spongebob
     * @param ui    Ui containing all UI components
     * @param storage   Storage to keep all entries to a .txt file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        // checks if there is nothing to mark
        assert !storage.isEmpty() : "storage is empty!";
        assert arguments[0].equals("mark")
                || arguments[0].equals("unmark")
                    : "Arguments must be mark or unmark!";

        if (arguments[1].equals(" ") || arguments[1].isEmpty()) {
            if (this.arguments[0].equals("mark")) {
                return ui.showMarkedError();
            } else {
                return ui.showUnmarkedError();
            }
        }

        // set index of task to mark / unmark.
        try {
            this.index = Integer.parseInt(arguments[1]) - 1;
        } catch (NumberFormatException e) {
            return ui.showException(e);
        }
        Task task = taskList.getCache().get(index);

        try {
            if (this.arguments[0].equals("mark")) {
                task.markAsDone();
                taskList.update(this.index, task);
                storage.update(this.index, task);
                return ui.showMarked(task);

            } else if (this.arguments[0].equals("unmark")) {
                task.unmarkAsDone();
                taskList.update(this.index, task);
                storage.update(this.index, task);
                return ui.showUnmarked(task);

            } else {
                // should not happen
                return ui.unknownCommand();
            }

        } catch (IndexOutOfBoundsException e) {
            return ui.showException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String[] getArgs() {
        return arguments;
    }
}
