package xizi.chatbot.command;

import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

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

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws XiziException, IOException {
        List<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getItems().get(i);
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.printMessage("No tasks matching your keyword were found.");
        } else {
            ui.printMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.printMessage((i + 1) + "." + matchingTasks.get(i));
            }
        }

        ui.showLine();
    }
}

