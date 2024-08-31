package command;

import java.util.ArrayList;

import exception.*;
import task.*;
import ui.Ui;

public class ListCommand extends UserCommand {
    /**
     * Lists all tasks
     * @param userInput String representing the line that user inputs
     * @param ui Ui to print output
     * @param storage Storage where tasks are saved
     * @param taskList Task list
     */
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();
        ui.printTasks(tasks);
    }
}    