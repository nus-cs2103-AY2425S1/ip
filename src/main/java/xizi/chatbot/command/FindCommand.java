package xizi.chatbot.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;


/**
 * Represents a command to find tasks that contain a specified keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param userInput The user input containing the keyword to search for.
     */
    public FindCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.FIND.matcher(userInput);
        if (matcher.matches()) {
            this.keyword = matcher.group(1);
        } else {
            throw new XiziException("Invalid find command format. Use: find <keyword>");
        }
    }

    /**
     * Executes the find command, searching for tasks that contain the specified keyword.
     * If matching tasks are found, they are displayed to the user. If no matches are found,
     * a message indicating the absence of matches is displayed.
     *
     * @param tasks   The task list containing all tasks.
     * @param storage The storage handler (not used in this command).
     * @param ui      The user interface for interacting with the user.
     * @throws XiziException If any errors occur during execution.
     * @throws IOException   If an I/O error occurs (though unlikely in this command).
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws XiziException, IOException {
        List<Task> matchingTasks = new ArrayList<>();
        ui.showLine();

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getItems().get(i);
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.printMessage("No tasks matching your keyword were found.");
            return;
        }

        ui.printMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            ui.printMessage((i + 1) + "." + matchingTasks.get(i));
        }

        ui.showLine();
    }
}

