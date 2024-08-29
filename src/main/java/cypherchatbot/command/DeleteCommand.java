package cypherchatbot.command;

import cypherchatbot.CypherException;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

public class DeleteCommand extends Command {

    private int val;

    public DeleteCommand(int val) {
        this.val = val;
    }
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CypherException {
        if (this.val >= tasks.size()) {
            throw new CypherException(String.format("You have %d items in your list. Enter a valid integer or add more items to your list", tasks.size()));
        } else if (this.val < 0) {
            throw new CypherException("Enter a value above 0");
        }

        String output = tasks.delTask(this.val, storage);
        ui.output(output);
    }

    public boolean isExit() {
        return false;
    }
}
