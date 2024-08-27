package Commands;

import Exceptions.NedException;

import java.util.ArrayList;

import Tasks.Task;

public interface Command {
    void execute(String userInput, ArrayList<Task> listOfTasks) throws NedException;
    String getRegex();
}
