package command;

import exceptions.DelphiException;
import exceptions.InvalidInputException;
import exceptions.TaskNotFoundException;
import parser.Parser;
import storage.Storage;
import TaskList.TaskList;
import task.Task;
import ui.Ui;

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
            s.writeToHardDisk(t.getTasks());
            return "task has been updated!";
        }
    }
}
