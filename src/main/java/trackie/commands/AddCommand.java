package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

public class AddCommand extends Command {
    public AddCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            switch (arguments[0]) {
                case "todo":
                    tasklist.addTodoTask(arguments);
                    break;
                case "deadline":
                    tasklist.addDeadlineTask(arguments);
                    break;
                case "event":
                    tasklist.addEventTask(arguments);
            }
            storage.save();
        } catch (TrackieException e) {
            ui.displayErrorMessage(e);
        }
    }
}
