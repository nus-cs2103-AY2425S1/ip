package Commands;

import Exceptions.NedException;

import java.util.ArrayList;

import Tasks.Task;

import Default.FlagWrapper;

public class ByeCommand implements Command {
    private final String REGEX = "bye\\s*";

    public ByeCommand(){}

    @Override
    public void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException {
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }

    public void execute(FlagWrapper flag) {
        flag.setStatus(false);
    }
}
