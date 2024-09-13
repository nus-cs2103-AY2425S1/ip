package tecna.command;

import tecna.collection.TaskList;
import tecna.storage.Storage;
import tecna.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String message) {
        super(message);
        setIsExitCommand();
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        storage.setFilePath("src/main/resources/data/tecna1.json");

        try {
            storage.save(taskList);
        } catch (java.io.IOException ioException) {
            ui.printError("I cannot access the data file " + storage.getFilePath());
        }

        return ui.printGoodbyeMsg();
    }
}
