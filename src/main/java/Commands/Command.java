package Commands;

import Default.Storage;
import Default.TaskList;
import Default.Ui;
import Exceptions.NedException;

import java.util.ArrayList;

import Tasks.Task;

public interface Command {
    void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException;
    default boolean isExit() {
        return false;
    }
    String getRegex();
}
