package tecna.command;

import java.time.format.DateTimeParseException;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.task.Deadline;
import tecna.ui.Ui;
import tecna.util.DateTimeUtil;

import static tecna.util.DateTimeUtil.DATE_TIME_PATTERN;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        Deadline deadline;

        try {
            deadline = parseDeadlineCommand();
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }

        assert deadline != null;
        setIsSuccessful(true);
        taskList.addItem(deadline);
        return ui.printAddItemMsg(taskList, deadline);
    }

    public Deadline parseDeadlineCommand() throws WrongFormatException {
        Deadline deadline = null;

        assert message.contains("deadline");

        String[] description = message.split("deadline | /by");
        try {
            deadline = new Deadline(description[1].trim(), DateTimeUtil.parseDateTime(description[2].trim()));
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new WrongFormatException("deadline", "Deadline task should be in the format \"deadline [description] /by [" + DATE_TIME_PATTERN + "]\"");

        }
        return deadline;
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("");
        Ui ui = new Ui();
        DeadlineCommand command = new DeadlineCommand("deadline go shopping /by 2024-12-11 1400");
        System.out.println(command.execute(taskList, storage, ui));
    }
}
