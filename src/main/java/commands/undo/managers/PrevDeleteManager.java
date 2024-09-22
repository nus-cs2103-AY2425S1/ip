package commands.undo.managers;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.Task;
import task.TaskList;

/**
 * Class to undo previous command, if it is a delete command.
 */
public class PrevDeleteManager extends PrevCommandManager {
    /**
     * {@inheritDoc}
     *
     * Specifically, undoes the previous delete command.
     */
    @Override
    public String undoPrevCommand(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks)
            throws BrockException {
        Task lastDeletedTask = tempStorage.getLastDeletedTask();
        tasks.addToList(lastDeletedTask);
        this.updateSaveFile(taskStorage, tasks, lastDeletedTask);
        return this.getResponse(lastDeletedTask, tasks);
    }

    /**
     * Writes the restored task back into the save file.
     *
     * @param taskStorage Instance that interfaces with save file.
     * @param tasks List of current {@code Task} objects.
     * @param restoredTask Restored task to write into the save file.
     * @throws BrockException If writing to file fails.
     */
    private void updateSaveFile(TaskStorage taskStorage, TaskList tasks, Task restoredTask) throws BrockException {
        taskStorage.writeToFile(tasks.numTasks() + ". "
                + tasks.getTaskDetails(restoredTask) + '\n',
                true);
    }

    /**
     * Gets the chatbot response to undoing previous delete command.
     *
     * @param lastDeletedTask Restored task.
     * @param tasks List of current {@code Task} objects.
     * @return Chatbot response.
     */
    private String getResponse(Task lastDeletedTask, TaskList tasks) {
        return "Restoring deleted task...\n"
                + "  " + tasks.getTaskDetails(lastDeletedTask) + " restored!\n"
                + tasks.getTasksSummary()
                + "\nLAO GAN MA!";
    }
}
