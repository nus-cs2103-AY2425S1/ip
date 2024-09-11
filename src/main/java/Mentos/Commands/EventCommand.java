package Mentos.Commands;

import Mentos.MentosException.MentosException;
import Mentos.task.Event;
import Mentos.task.Task;
import Mentos.task.TaskList;

import java.util.regex.Matcher;

public class EventCommand extends Command {

    private final String regex = "event (.+) \\/from (\\d{4}-\\d{2}-\\d{2} \\d{4}) \\/to " +
            "(\\d{4}-\\d{2}-\\d{2} \\d{4})$";
    private final Task task;

    public EventCommand(String input) throws MentosException {
        super("event");
        Matcher matcher = super.regexHandler(input, this.regex);
        if (matcher == null) {
            throw new MentosException("Invalid Event input! usage:event <desc> /from <datetime> " +
                    "yyyy-mm-dd hhmm /to <datetime> yyyy-mm-dd hhmm");
        }
        String eventDesc = matcher.group(1);
        String from = matcher.group(2);
        String to = matcher.group(3);
        this.task = new Event(eventDesc, from, to);
    }


    @Override
    public String execute(TaskList tasklist) {
        tasklist.add(this.task);
        return super.getGui().printRemainingEvents("event", this.task, tasklist.size());
    }

}
