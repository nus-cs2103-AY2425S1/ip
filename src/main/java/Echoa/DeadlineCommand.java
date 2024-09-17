package echoa;

import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public DeadlineCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    @Override
    public void execute(String line) throws InvalidDeadlineContentException, IOException {
        Object[] deadline = parser.parseDeadlineTask(line);
        String deadlineDescription = (String) deadline[0];
        LocalDateTime dateAndTime = (LocalDateTime) deadline[1];
        taskList.addTask(new Deadline(deadlineDescription, dateAndTime));
        storage.handleChange(taskList);
    }

    @Override
    public String getMessage() {
        return ui.getAddTaskMessage(taskList);
    }
}
