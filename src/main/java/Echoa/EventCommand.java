package echoa;

import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    public EventCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    @Override
    public void execute(String line) throws InvalidEventContentException, IOException {
        Object[] event = parser.parseEventTask(line);
        String eventDescription = (String) event[0];
        LocalDateTime startDateAndTime = (LocalDateTime) event[1];
        LocalDateTime endDateAndTime = (LocalDateTime) event[2];
        taskList.addTask(new Event(eventDescription, startDateAndTime, endDateAndTime));
        storage.handleChange(taskList);
    }

    @Override
    public String getMessage() {
        return ui.getAddTaskMessage(taskList);
    }
}
