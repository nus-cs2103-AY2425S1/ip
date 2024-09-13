package tecna.command;

import java.time.format.DateTimeParseException;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.task.Event;
import tecna.ui.Ui;
import tecna.util.DateTimeUtil;

import static tecna.util.DateTimeUtil.DATE_TIME_PATTERN;

public class EventCommand extends Command {
    public EventCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        Event event;

        try {
            event = parseEventCommand();
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }

        assert event != null;
        setIsSuccessful(true);

        try {
            taskList.addItem(event);
        } catch (tecna.exception.TaskDuplicateException e) {
            return ui.printError(e.getMessage());
        }

        return ui.printAddItemMsg(taskList, event);
    }

    public Event parseEventCommand() throws WrongFormatException {
        Event event = null;

        assert message.contains("event");

        String[] description = message.split("event | /from | /to ");

        try {
            event = new Event(description[1].trim(), DateTimeUtil.parseDateTime(description[2].trim()), DateTimeUtil.parseDateTime(description[3].trim()));
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new WrongFormatException("event", "Event task should in the format of \"event [description] /from [" + DATE_TIME_PATTERN + "] /to [" + DATE_TIME_PATTERN + "]");
        }

        return event;
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("");
        Ui ui = new Ui();
        EventCommand command = new EventCommand("event go shopping /from 2024-12-11 1400 /to 2024-12-11 1500 ");
        System.out.println(command.execute(taskList, storage, ui));
    }
}
