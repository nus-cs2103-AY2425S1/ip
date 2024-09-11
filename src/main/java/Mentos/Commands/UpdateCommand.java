package Mentos.Commands;

import Mentos.MentosException.MentosException;
import Mentos.task.Deadline;
import Mentos.task.Event;
import Mentos.task.Task;
import Mentos.task.TaskList;

import java.util.regex.Matcher;

public class UpdateCommand extends Command {
    private final String regex = "update (\\d+) (.*)";

    private final String descriptionRegex = "\\/desc (.*)$";
    private final String fromRegex = "\\/from (\\d{4}-\\d{2}-\\d{2} \\d{4})";
    private final String toRegex = "\\/to (\\d{4}-\\d{2}-\\d{2} \\d{4})";
    private final String byRegex = "\\/by (\\d{4}-\\d{2}-\\d{2} \\d{4})$";
    private final String fieldsToUpdate;
    private int index;

    public UpdateCommand(String input) throws MentosException {
        super("update");
        Matcher matcher = super.regexHandler(input, this.regex);
        if (matcher == null) {
            throw new MentosException("Invalid update input! usage:update <index> ..../from <> /to<>");
        }
        String extracted = matcher.group(1);
        this.fieldsToUpdate = matcher.group(2);
        this.index = Integer.parseInt(extracted);
    }

    public boolean updateHandler(Task task) {
        boolean hasUpdated = false;
        Matcher descriptionMatch = super.regexHandler(this.fieldsToUpdate, this.descriptionRegex);
        if (descriptionMatch != null) {
            task.updateDescription(descriptionMatch.group(1));
            hasUpdated = true;
        }
        if (task instanceof Event) {
            Matcher fromMatch = super.regexHandler(this.fieldsToUpdate, fromRegex);
            if (fromMatch != null) {
                ((Event) task).updateFrom(fromMatch.group(1));
                hasUpdated = true;
            }
            Matcher toMatch = super.regexHandler(this.fieldsToUpdate, toRegex);
            if (toMatch != null) {
                ((Event) task).updateTo(toMatch.group(1));
                hasUpdated = true;
            }
        }
        if (task instanceof Deadline) {
            Matcher byMatch = super.regexHandler(this.fieldsToUpdate, this.byRegex);
            if (byMatch != null) {
                ((Deadline) task).updateBy(byMatch.group(1));
                hasUpdated = true;
            }
        }
        return hasUpdated;
    }

    @Override
    public String execute(TaskList tasklist) {
        try {
            if (super.checkIndex(this.index, tasklist)) {
                Task taskToUpdate = tasklist.get(this.index - 1);
                boolean hasUpdated = updateHandler(taskToUpdate);
                if (hasUpdated) {
                    return super.getGui().updateCommand(tasklist.get(this.index - 1));
                }
                return super.getGui().updateCommandFailed();
            }
        } catch (MentosException e) {
            return e.toString();
        }
        return null;
    }
}

