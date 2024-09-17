package echoa;

import java.io.IOException;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    @Override
    public void execute(String line) throws InvalidIndexInputException, ListOutOfBoundsException, IOException {
        index = parser.parseIndex(line);
        taskList.markTaskAsUndone(index);
        storage.handleChange(taskList);
    }

    @Override
    public String getMessage() {
        return ui.getUnmarkTaskMessage(taskList, index);
    }
}
