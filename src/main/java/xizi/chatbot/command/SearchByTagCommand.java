package xizi.chatbot.command;

import java.util.List;

import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;
/**
 * Represents a command to return all tasks with a given tag.
 */
public class SearchByTagCommand implements Command {
    private String tag;

    /**
     * Constructs a SearchByTagCommand based on the user input.
     * Parses the input to get a tag and find all tasks with the tag.
     *
     * @param userInput The user input containing the tag string.
     * @throws XiziException If the input format is invalid.
     */
    public SearchByTagCommand(String userInput) throws XiziException {
        String[] inputParts = userInput.split(" ");
        if (inputParts.length < 3) {
            throw new XiziException("Invalid format. Usage: search tag <tag>");
        }
        this.tag = inputParts[2].replace("#", ""); //remove # in front of the tag
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        List<Task> tasksWithTag = tasks.searchByTag(tag);
        if (tasksWithTag.isEmpty()) {
            ui.printMessage("No tasks found with tag: " + tag);
        } else {
            ui.printMessage("Tasks with tag '" + tag + "':");
            for (int i = 0; i < tasksWithTag.size(); i++) {
                ui.printMessage((i + 1) + ". " + tasksWithTag.get(i));
            }
        }
    }
}
