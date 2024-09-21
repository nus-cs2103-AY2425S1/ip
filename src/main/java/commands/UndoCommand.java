package commands;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.Task;
import task.TaskList;


// TODO: Refactor this into manager system (ie: have a bunch of managers (prevToggleManager, prevCreateManager, prevDeleteManager)
//  handlePrevCommand or undoPrevCommand (override method) prevCommandManager (ABC)
//  Where each manager handles prev command(s) -> generates a String response
/**
 * Represents an undo command entered by the user.
 */
public class UndoCommand extends Command {
    /**
     * Stores the command string associated with undo command.
     *
     * @param command Command string.
     */
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

    private String handlePrevIsDelete(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks)
            throws BrockException {
        Task lastDeletedTask = tempStorage.getLastDeletedTask();
        tasks.addToList(lastDeletedTask);
        this.updateSaveFile(taskStorage, tasks, lastDeletedTask);
        return "Restoring deleted task...\n"
                + "  " + tasks.getTaskDetails(lastDeletedTask) + " restored!\n"
                + tasks.getTasksSummary();
    }

    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks, Task restoredTask) throws BrockException {
        taskStorage.writeToFile(tasks.numTasks() + ". "
                + tasks.getTaskDetails(restoredTask) + '\n',
                true);
    }


    private String processPreviousCommand(TempStorage tempStorage) throws BrockException {
        String previousCommand = tempStorage.getPreviousCommand();
        if (previousCommand == null) {
            throw new BrockException("Previous command is invalid or does not exist, cannot be undone!");
        }
        if (previousCommand.equals("undo")) {
            throw new BrockException("Cannot undo twice in a row!");
        }
        return previousCommand;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if undo command is valid.
     * If so, it performs the necessary command to undo the previous command.
     * Returns a response showing more info about the undo.
     * </p>
     *
     * @throws BrockException If undo command is invalid.
     */
    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        String previousCommand = this.processPreviousCommand(tempStorage);
        String responseHeader = "Undoing previous valid command: " + previousCommand + " ...\n\n";

        // From here onwards, is cases where able to perform undo (except else block)
        String undoResponse;
        if (previousCommand.equals("mark")) {
            undoResponse = this.handlePrevIsMark(taskStorage, tempStorage, tasks);

        } else if (previousCommand.equals("unmark")) {
            undoResponse = this.handlePrevIsUnmark(taskStorage, tempStorage, tasks);

        } else if (previousCommand.equals("todo") | previousCommand.equals("deadline")
                | previousCommand.equals("event")) {
            undoResponse = this.handlePrevIsCreate(taskStorage, tempStorage, tasks);

        } else if (previousCommand.equals("delete")) {
            undoResponse = this.handlePrevIsDelete(taskStorage, tempStorage, tasks);

        } else {
            throw new BrockException("Previous valid command: " + previousCommand + " cannot be undone!");
        }
        return responseHeader + undoResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandType() {
        return "undo";
    }
}
