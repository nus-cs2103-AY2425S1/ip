package nimbus.ui;

import nimbus.command.*;
import nimbus.exception.WrongDateTimeFormatException;

import java.io.IOException;

public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void handleInput(String userInput) throws IOException, WrongDateTimeFormatException {

        if (userInput.equals("bye")) {
            Storage.updateFile(taskList.getTaskList());
            Ui.goodbyeMessage();
        } else if (userInput.equals("list")) {
            taskList.toString();
        } else if (userInput.startsWith("check ")) {
            new CheckCommand(userInput, taskList).execute();
        } else if (userInput.startsWith("mark ")) {
            new MarkCommand(userInput, taskList).execute();
        } else if (userInput.startsWith("unmark ")) {
            new UnmarkCommand(userInput, taskList).execute();
        } else if (userInput.startsWith("delete ")) {
            new DeleteCommand(userInput, taskList).execute();
        } else {
            new AddCommand(userInput, taskList).execute();
        }
    }
}
