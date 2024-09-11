import susan.command.Command;
import susan.task.TaskList;
import susan.ui.Parser;
import susan.ui.Storage;
import susan.ui.SusanException;
import susan.ui.Ui;

public class Susan {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Susan() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (SusanException e) {
            return ui.showError(e.getMessage());
        }
    }
}
