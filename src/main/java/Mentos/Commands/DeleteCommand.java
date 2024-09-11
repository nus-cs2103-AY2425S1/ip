package Mentos.Commands;

import Mentos.Mentos;
import Mentos.MentosException.MentosException;
import Mentos.task.Task;
import Mentos.task.TaskList;

import java.util.regex.Matcher;

public class DeleteCommand extends Command {

    private final String regex = "delete (\\d+)$";
    private int index = 0;

    public DeleteCommand(String input) throws MentosException {
        super("delete");
        Matcher match = regexHandler(input, "delete (\\d+)$");
        if (match == null) {
            throw new MentosException("Invalid Delete input!");
        }
        String extracted = match.group(1);
        this.index = Integer.parseInt(extracted);

    }


    @Override
    public String execute(TaskList tasklist) {
        try {
            if (super.checkIndex(this.index, tasklist)) {
                Task task = tasklist.get(this.index - 1);
                tasklist.remove(this.index - 1);
                return super.getGui().deleteEvent(task, tasklist.size());
            }
        } catch (MentosException e) {
            return e.toString();
        }
        return "Invalid Input";
    }
}
