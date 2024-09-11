package Mentos.Commands;

import Mentos.MentosException.MentosException;
import Mentos.task.TaskList;

import java.util.regex.Matcher;

public class MarkCommand extends Command {
    private final String regex = "mark (\\d+)";
    private int index = 0;

    public MarkCommand(String input) throws MentosException {
        super("mark");
        Matcher match = super.regexHandler(input, this.regex);
        if (match == null) {
            throw new MentosException("Invalid input!");
        }
        String extracted = match.group(1);
        this.index = Integer.parseInt(extracted);
    }


    @Override
    public String execute(TaskList tasklist) {
        try {
            if (super.checkIndex(this.index, tasklist)) {
                tasklist.get(this.index - 1).markAsDone();
                return super.getGui().markEvent(tasklist.get(this.index - 1));
            }
        } catch (MentosException e) {
            return e.toString();
        }
        return null;
    }
}
