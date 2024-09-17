package commands;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.Task;
import task.TaskList;

public class UndoCommand extends Command {
    public UndoCommand(String command) {
        super(command);
    }

    private String handlePrevIsMark(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks)
            throws BrockException {
        int lastMarkedTaskNum = tempStorage.getLastToggledTaskNum();
        Command unmarkCommand = new UnmarkCommand("unmark " + lastMarkedTaskNum);
        return unmarkCommand.execute(taskStorage, tempStorage, tasks);
    }

    private String handlePrevIsUnmark(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks)
            throws BrockException {
        int lastUnmarkedTaskNum = tempStorage.getLastToggledTaskNum();
        Command markCommand = new MarkCommand("mark " + lastUnmarkedTaskNum);
        return markCommand.execute(taskStorage, tempStorage, tasks);
    }

    private String handlePrevIsCreate(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks)
            throws BrockException {
        int lastCreatedTaskNum = tempStorage.getLastCreatedTaskNum();
        Command deleteCommand = new DeleteCommand("delete " + lastCreatedTaskNum);
        return deleteCommand.execute(taskStorage, tempStorage, tasks);
    }

    private String handlePrevIsDelete(TempStorage tempStorage, TaskList tasks) {
        Task lastDeletedTask = tempStorage.getLastDeletedTask();
        tasks.addToList(lastDeletedTask);
        return tasks.getTaskDetails(lastDeletedTask);
    }

    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        String previousCommand = tempStorage.getPreviousCommand();
        if (previousCommand == null) {
            throw new BrockException("Previous command is invalid/does not exist, cannot be undone!");
        }
        if (previousCommand.equals("undo")) {
            throw new BrockException("Cannot undo twice in a row!");
        }

        String responseHeader = "Undoing previous command...\n";
        String undoResponse;
        if (previousCommand.equals("mark")) {
            undoResponse = this.handlePrevIsMark(taskStorage, tempStorage, tasks);
        }
        else if (previousCommand.equals("unmark")) {
            undoResponse = this.handlePrevIsUnmark(taskStorage, tempStorage, tasks);
        }
        else if (previousCommand.equals("todo")
                | previousCommand.equals("deadline")
                | previousCommand.equals("event")) {
            undoResponse = this.handlePrevIsCreate(taskStorage, tempStorage, tasks);
        }
        else if (previousCommand.equals("delete")) {
            undoResponse = this.handlePrevIsDelete(tempStorage, tasks);
        } else {
            throw new BrockException("Previous command: " + previousCommand + " cannot be undone!");
        }
        tempStorage.setPreviousCommand("undo");
        return responseHeader + undoResponse;
    }
}
