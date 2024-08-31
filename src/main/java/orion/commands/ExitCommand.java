package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;

import orion.exceptions.OrionTaskListSaveException;

import java.util.List;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        // Saves tasks
        List<String> saveTaskDescriptions = tasks.getSavedTaskDescriptions();
        try {
            storage.saveTasks(saveTaskDescriptions);
            ui.printSaveTasks();
        } catch (OrionTaskListSaveException e) {
            ui.printSaveError();
        } finally {
            ui.printGoodbye();
        }
    }
}
