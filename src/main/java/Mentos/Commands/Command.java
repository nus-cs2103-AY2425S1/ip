package Mentos.Commands;

import Mentos.MentosException.MentosException;
import Mentos.components.GuiResponse;
import Mentos.task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    private final String action;
    private final GuiResponse gui = new GuiResponse();

    public Command(String action) {
        this.action = action;
    }

    public Matcher regexHandler(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher;
        }
        return null;
    }

    public GuiResponse getGui() {
        return this.gui;
    }

    public String getAction() {
        return this.action;
    }

    public boolean checkIndex(int index, TaskList tasklist) throws MentosException {
        if (index > tasklist.size() || index == 0) {
            throw new MentosException("No Such Tasks!");
        }
        return true;
    }

    public abstract String execute(TaskList tasklist);

}
