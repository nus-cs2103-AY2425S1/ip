package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class HelpCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printLine();
        ui.show("PandaBot Commands:");
        ui.show("1. todo <description> : Adds a new Todo task.");
        ui.show("2. deadline <description> /by <DD/MM/YYYY HHmm> : Adds a new tasks.Deadline task.");
        ui.show("3. event <task description> /from <DD/MM/YYYY HHmm> /to <DD/MM/YYYY HHmm> : Adds a new tasks.Event task.");
        ui.show("4. list : Lists all tasks.");
        ui.show("5. mark <task number> : Marks the specified task as done.");
        ui.show("6. unmark <task number> : Unmarks the specified task.");
        ui.show("7. delete <task number> : Deletes the specified task.");
        ui.show("8. help : Displays this help message.");
        ui.show("9. find <keyword> : Lists tasks containing the keyword.");
        ui.show("9. bye : Exits the PandaBot.");
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
