package command;

import components.Storage;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

public class MarkCommand extends Command{

    private int taskNumber;
    private boolean isMark;

    public MarkCommand(int taskNumber, boolean isMark) {
        super();
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LightException {

        if (isMark) {
            tasks.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskNumber));

        } else {
            tasks.get(taskNumber).markAsUndone();
            System.out.println("Nice! I've marked this task as undone:\n" + tasks.get(taskNumber));
        }
        storage.write(TaskList.arrayToNumberedString(tasks));

    }
}
