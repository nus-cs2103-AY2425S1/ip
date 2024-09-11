package Mentos.Commands;

import Mentos.MentosException.MentosException;
import Mentos.task.Deadline;
import Mentos.task.Task;
import Mentos.task.TaskList;

import java.util.regex.Matcher;

public class DeadlineCommand extends Command {

    private final String regex = "deadline (.+) \\/by (\\d{4}-\\d{2}-\\d{2} \\d{4})$";
    private final Task task;


    public DeadlineCommand(String input) throws MentosException {
        super("deadline");
        Matcher matcher = super.regexHandler(input, this.regex);
        if (matcher == null) {
            throw new MentosException("Invalid deadline input! usage:deadline <desc> /by <datetime> in yyyy-mm-dd hhmm");
        }
        String deadline_desc = matcher.group(1);
        String by = matcher.group(2);
        this.task = new Deadline(deadline_desc, by);
    }

    @Override
    public String execute(TaskList tasklist) {
        tasklist.add(this.task);
        return super.getGui().printRemainingEvents("deadline", this.task, tasklist.size());
    }
}
