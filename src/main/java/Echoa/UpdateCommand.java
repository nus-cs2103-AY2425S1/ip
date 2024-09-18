package echoa;

import java.io.IOException;

public class UpdateCommand extends Command {

    private int index;
    private Task task;

    public UpdateCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    @Override
    public void execute(String line) throws InvalidIndexInputException, UpdateFormatException, IOException, DateFormatException, TimeFormatException {
        index = parser.parseUpdateIndex(line);
        task = taskList.getSpecificTask(index);
        String updateDetails = Parser.removeFirstOccurrence(line, String.valueOf (index + 1));
        Object[] details = {};

        assert task instanceof ToDo ||
                task instanceof Deadline ||
                task instanceof Event;

        if (task instanceof ToDo) {
            details = parser.parseToDoUpdate(updateDetails);
        } else if (task instanceof Deadline) {
            details = parser.parseDeadlineUpdate(updateDetails);
        } else if (task instanceof Event) {
            details = parser.parseEventUpdate(updateDetails);
        }
        task.update(details);
        storage.handleChange(taskList);
    }

    @Override
    public String getMessage() {
        return ui.getUpdateTaskMessage(task, index + 1);
    }
}
