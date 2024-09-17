package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;

import java.io.IOException;

public class SaveCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {
        String message = "Saving Tasks...";

        try {
            storage.save(tasks.saveTasks());

            return message + "Success\nYour tasks have been saved.";
        } catch (IOException e) {
            throw new YapBotException(message + "Failure\nFailed to save tasks.");
        }

    }
}
