package flychat.command;

import flychat.Deadline;
import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;

public class AddDeadlineCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Parser parser, String inputString) {
        Deadline newDeadline = Deadline.createNewDeadline(parser.getTaskDescription(inputString),
                parser.getDeadlineEndDate(inputString), false);
        taskList.addToList(newDeadline);
        return "Deadline added:\n  " + newDeadline + "\nNow you have " + taskList.getSize()
                + " tasks in the list. HAVE FUN ^o^";
    }
}
