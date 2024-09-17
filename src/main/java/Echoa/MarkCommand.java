package echoa;

import java.io.IOException;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    @Override
    public void execute(String line) throws InvalidIndexInputException, ListOutOfBoundsException, IOException {
        index = parser.parseIndex(line);
        taskList.markTaskAsDone(index);
        storage.handleChange(taskList);
    }

    @Override
    public String getMessage() {
        return ui.getMarkTaskMessage(taskList, index);
    }
}
