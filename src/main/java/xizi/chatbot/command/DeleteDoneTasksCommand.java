package xizi.chatbot.command;

import java.io.IOException;
import java.util.List;

import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;



/**
 * Represents a command to delete all completed tasks from the task list.
 */
public class DeleteDoneTasksCommand implements Command {

    /**
     * Executes the command to remove completed tasks from the task list and save changes.
     *
     * @param actions the task list from which completed tasks are to be deleted
     * @param storage the storage to save the updated task list
     * @param ui the user interface for displaying messages
     * @throws IOException if an I/O error occurs while saving tasks
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException {
        List<Task> completedTasks = actions.getItems().stream()
                .filter(Task::getDone) // Assuming Task has isCompleted method
                .toList();

        if (completedTasks.isEmpty()) {
            ui.printMessage("There are no completed tasks to delete.");
        } else {
            actions.getItems().removeAll(completedTasks);
            actions.updateSize();
            storage.saveTasks(actions.getItems()); // Save changes to storage
            ui.printMessage("All completed tasks have been deleted.");
        }
    }
}


