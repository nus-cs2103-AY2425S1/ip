package spongebob.command;

import java.util.ListIterator;

import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.task.Task;
import spongebob.ui.Ui;

/**
 * Command to find keyword in task list
 */
public class FindCommand extends Command {

    private String[] arguments;

    public FindCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command, prints out list of tasks that contains keyword
     * @param taskList  tasklist of Spongebob
     * @param ui    UI Component of Spongebob
     * @param storage   Storage of Spongebob
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        assert !storage.isEmpty() : "storage is empty!";

        String keyword = arguments[1];

        StringBuilder listInStringBuilder = new StringBuilder();
        ListIterator<Task> iter = taskList.find(keyword).listIterator();

        // adds and format tasks in string form to builder.
        while (iter.hasNext()) {
            Task cur = iter.next();
            listInStringBuilder.append((iter.previousIndex() + 1) + "." + cur + "\n");
        }
        return ui.showFindTask(listInStringBuilder.toString());

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
