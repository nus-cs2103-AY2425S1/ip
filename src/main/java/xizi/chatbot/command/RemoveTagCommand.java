package xizi.chatbot.command;

import java.io.IOException;
import java.util.Arrays;

import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;
/**
 * Represents a command to remove a tag to a specific task in the task list.
 */
public class RemoveTagCommand implements Command {
    private final int taskIndex;
    private final String tag;

    /**
     * Constructs a RemoveTagCommand based on the user input.
     * Parses the input to extract the task number that should be tagged and the tag to be removed.
     *
     * @param userInput The user input containing the task number and the tag string.
     * @throws XiziException If the input format is invalid or the task number cannot be parsed.
     */
    public RemoveTagCommand(String userInput) throws XiziException {
        String[] inputParts = userInput.split(" ");
        if (inputParts.length < 4) {
            throw new XiziException("Invalid format. Usage: remove tag <task number> <tag>");
        }
        try {
            this.taskIndex = Integer.parseInt(inputParts[2]) - 1; // Convert to 0-based index
            this.tag = inputParts[3].replace("#", "");
        } catch (NumberFormatException e) {
            throw new XiziException(Arrays.toString(inputParts));
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
            Task task = tasks.getItems().get(taskIndex);
            if (task.hasRemovedTag(tag)) {
                storage.saveTasks(tasks.getItems()); // Save changes
                ui.printMessage("Removed tag from task: " + task);
            } else {
                ui.printMessage("Tag not found on task. Valid tags are " + task.getTags());
            }
        } else {
            ui.printMessage("Invalid task number.");
        }
    }
}

