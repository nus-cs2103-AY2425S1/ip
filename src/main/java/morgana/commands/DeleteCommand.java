package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.ui.Ui;

public class DeleteCommand extends Command {
    private final String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MorganaException {
        int index = Parser.parseTaskIndex(args, tasks);
        ui.showToUser(
                "Noted. I've removed this task:",
                "%d. %s".formatted(index + 1, tasks.remove(index)),
                "Now you have %d task%s in the list.".formatted(tasks.size(),
                        tasks.size() > 1 ? "s" : ""));
        storage.save(tasks);
    }
}
