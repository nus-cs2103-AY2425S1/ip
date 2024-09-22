package spongebob.command;

import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.task.Task;
import spongebob.ui.Ui;

public class TagCommand extends Command{
    private String[] arguments;
    private int index;

    public TagCommand(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert !storage.isEmpty() : "storage is empty!";

        try {
            this.index = Integer.parseInt(arguments[1]) - 1;
            if (arguments[1].equals(" ") || arguments[1].isEmpty()) {
                return ui.showTagError();
            }

        } catch (NumberFormatException e) {
            return ui.showException(e);
        }

        try {
            Task task = taskList.getCache().get(index);
            task.setTag(arguments[2]);
            taskList.update(this.index, task);
            storage.update(this.index, task);

            return ui.showTaggedTask(task);

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
        return new String[0];
    }
}
