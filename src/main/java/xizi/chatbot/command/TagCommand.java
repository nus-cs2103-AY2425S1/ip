package xizi.chatbot.command;

import java.io.IOException;

import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;
/**
 * Represents a command to add a tag to a specific task in the task list.
 */
public class TagCommand implements Command {
    private int taskIndex;
    private String tag;

    /**
     * Constructs a TagCommand based on the user input.
     * Parses the input to extract the task number that should be tagged.
     *
     * @param userInput The user input containing the task number and the tag string.
     * @throws XiziException If the input format is invalid or the task number cannot be parsed.
     */
    public TagCommand(String userInput) {
        String[] tagParts = userInput.split(" ");
        int taskNum = Integer.parseInt(tagParts[1]);
        String tag = tagParts[2].replace("#", "");
        this.taskIndex = taskNum - 1; // Convert to 0-based index
        this.tag = tag;
    }

    /**
     * Executes the tagging command on a specified task in the task list.
     * This method adds a tag to the task identified by the task index.
     * It then saves the updated list of tasks to the storage and displays a confirmation message.
     * If the task index is invalid (i.e., out of bounds), an error message is displayed.
     *
     * @param tasks   The task list containing all tasks.
     * @param storage The storage object used to save the updated list of tasks.
     * @param ui      The UI object used to print messages to the user.
     * @throws IOException If an I/O error occurs while saving tasks to storage.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
            Task task = tasks.getItems().get(taskIndex);
            task.addTag(tag);
            storage.saveTasks(tasks.getItems()); // Save changes
            ui.printMessage("Tagged task: " + task);
        } else {
            ui.printMessage("Invalid task number.");
        }
    }
}

