package spongebob.command;

import java.util.ListIterator;

import spongebob.ui.Ui;
import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.task.Task;



/**
 * Command to find keyword in task list
 */
public class FindCommand extends Command {

    private String[] arguments;

    public FindCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * executes the command, prints out list of tasks that contains keyword
     * @param taskList  tasklist of Spongebob
     * @param ui    UI Component of Spongebob
     * @param storage   Storage of Spongebob
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = arguments[1];
        StringBuilder builder = new StringBuilder();
        ListIterator<Task> iter = taskList.find(keyword).listIterator();
        while (iter.hasNext()) {
            Task cur = iter.next();
            builder.append((iter.previousIndex() + 1) + "." + cur + "\n");
        }
        return ui.showFindTask(builder.toString());

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
