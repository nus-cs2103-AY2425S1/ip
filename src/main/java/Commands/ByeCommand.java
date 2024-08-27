package Commands;

import Default.Storage;
import Default.TaskList;
import Default.Ui;
import Exceptions.NedException;

import java.util.ArrayList;

import Tasks.Task;

import Default.FlagWrapper;

public class ByeCommand implements Command {
    private final String REGEX = "bye\\s*";

    public ByeCommand(){}

    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException {
        uiInstance.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }

    public void execute(FlagWrapper flag) {
        flag.setStatus(false);
    }
}
