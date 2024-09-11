package Mentos.Commands;

import Mentos.MentosException.MentosException;
import Mentos.task.Task;
import Mentos.task.TaskList;
import Mentos.task.ToDo;

import java.util.regex.Matcher;

public class ToDoCommand extends Command {
    private final String regex = "todo (.+)";
    private final Task task;


    public ToDoCommand(String input) throws MentosException {
        super("todo");
        Matcher matcher = super.regexHandler(input, this.regex);
        if (matcher == null) {
            throw new MentosException("Todo cannot be empty!");
        }
        String extracted = matcher.group(1);
        this.task = new ToDo(extracted);
    }

    @Override
    public String execute(TaskList tasklist) {
        tasklist.add(this.task);
        return super.getGui().printRemainingEvents("todo", this.task, tasklist.size());
    }


}
