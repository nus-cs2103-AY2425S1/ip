package wenjieBot.commands;

import wenjieBot.exceptions.DukeException;
import wenjieBot.Storage;
import wenjieBot.TaskList;
import wenjieBot.Ui;

public abstract class Command {

    private boolean isExit = false;
    private String input = "";

    public Command(boolean isExit, String input) {
        this.isExit = isExit;
        this.input = input;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }

    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public String getInput() {
        return input;
    }
}
