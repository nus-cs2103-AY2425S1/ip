package topaz.command;
import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.ui.Ui;

import java.util.Objects;

/**
 * Command that will only return text when executed, has no effect on taskList
 */
public class TextCommand extends Command {

    // bye, list, help
    public TextCommand(String keyword) {
        super(keyword);
        if (Objects.equals(keyword, "bye")) {
            super.isExit = true;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (super.keyword) {
            case "bye":
                ui.goodbye();
                break;
            case "help":
                ui.showHelp();
                break;
            case "list":
                ui.showTaskList();
                tasks.listTasks();
                break;
        }
    }

}
