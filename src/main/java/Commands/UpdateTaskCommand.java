package Commands;

import Exceptions.DelphiException;
import Exceptions.InvalidInputException;
import Exceptions.TaskNotFoundException;
import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import UI.Ui;

import java.util.List;

public class UpdateTaskCommand extends Command{
    public UpdateTaskCommand(String s) {
        super(s);
    }

    public String execute(TaskList t, Storage s, Ui ui) throws DelphiException {
        String str;
        if (getInput().contains("/by")) {
            str = getInput().substring(7, getInput().indexOf("/by"));
        } else if (getInput().contains("/from")) {
            str = getInput().substring(7, getInput().indexOf("/from"));
        } else {
            throw new InvalidInputException();
        }
        List<Task> searchResults = t.findTask(str);
        if (searchResults.size() == 0) {
            throw new TaskNotFoundException();
        } else if (searchResults.size() > 1) {
            return "please be more specific";
        } else {
            searchResults.get(0).editTask(getInput().substring(7), new Parser());
            return "task has been updated!";
        }
    }
}
