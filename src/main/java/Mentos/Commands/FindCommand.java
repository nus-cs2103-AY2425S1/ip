package Mentos.Commands;

import Mentos.MentosException.MentosException;
import Mentos.components.GuiResponse;
import Mentos.task.Task;
import Mentos.task.TaskList;
import Mentos.task.ToDo;

import java.util.regex.Matcher;

public class FindCommand extends Command {
    private final String regex = "find (\\w+)";
    private Task task;


    public FindCommand(String input) throws MentosException {
        super("find");
        Matcher matcher = super.regexHandler(input, this.regex);
        if (matcher == null) {
            throw new MentosException("Invalid find");
        }
        String keyword = matcher.group(1);
        this.task = new ToDo(keyword);

    }

    public String searchTask(String description, TaskList tasklist) {
        int index = 0;
        GuiResponse gui = super.getGui();
        StringBuilder res = new StringBuilder();
        for (Task t : tasklist.getTasks()) {
            assert t != null : "Tasks in TaskList should not be null.";
            if (t.getDescription().contains(description)) {
                index++;
                res.append(gui.printEvent(index, t));
            }
        }
        res.append("\n");
        if (index == 0) {
            res.append(gui.noMatchingEvents());
        } else {
            res.append(gui.printMatchingEvents());
        }
        return res.toString();
    }

    @Override
    public String execute(TaskList tasklist) {
        String keyword = this.task.getDescription();
        return searchTask(keyword, tasklist);
    }
}
