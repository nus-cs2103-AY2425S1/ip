package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isMark;
    public MarkCommand(int taskNumber, boolean isMark) {
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        if(taskNumber > tasks.getSize() - 1) {
            throw new LexiException("Sorry! That task does not exist.\nPlease key in the correct task number");
        }
        Task taskToBeMarked = tasks.getTask(taskNumber);
        if(isMark) {
            markTask(taskToBeMarked, tasks, ui);
        } else {
            unmarkTask(taskToBeMarked, tasks, ui);
        }
        storage.updateStorage(tasks.getTasks());

    }

    private void unmarkTask(Task taskToBeMarked, TaskList tasks, Ui ui) throws LexiException {
        if(!taskToBeMarked.getIsDone()) {
            throw new LexiException("This task has already been unmarked!\n");
        }
        taskToBeMarked.undoTask();
        tasks.updateTask(taskToBeMarked, this.taskNumber);
        ui.showUnmarkMessage(taskToBeMarked);
    }

    public void markTask(Task taskToBeMarked, TaskList tasks, Ui ui) throws LexiException {
        if(taskToBeMarked.getIsDone()) {
            throw new LexiException("This task has already been marked!\n");
        }
        taskToBeMarked.doTask();
        tasks.updateTask(taskToBeMarked, this.taskNumber);
        ui.showMarkMessage(taskToBeMarked);
    }
    @Override
    public String getCommandName() {
        return "MARK";
    }

}
