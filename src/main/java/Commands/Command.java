package Commands;

import Default.TaskList;
import Exceptions.NedException;

import java.util.ArrayList;

import Tasks.Task;

public interface Command {
    void execute(String userInput, TaskList taskList) throws NedException;
    String getRegex();
}
