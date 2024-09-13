package xbot.command;

import xbot.TaskList;
import xbot.exception.XBotException;
import xbot.parser.Parser;
import xbot.storage.Storage;
import xbot.task.Task;
import xbot.ui.Ui;

/**
 * Handles the "view" command.
 */
public class ViewCommand implements Command {
    TaskList totalList = new TaskList();
    Ui ui = new Ui();

    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException {
        if (rest.isEmpty()) {
            throw new XBotException("There is no date to view!");
        }
        this.totalList = list;
        this.ui = ui;
        return listTaskForDate(rest);
    }

    public String listTaskForDate(String rest) throws XBotException {
        //Check if rest is a valid date
        if (!Parser.isValidDateFormat(rest)) {
            throw new XBotException("The date to view is in invalid format!");
        }

        String output = "";
        boolean noTodo = listTodoForDate(rest) == ui.showNoTask();
        boolean noDeadline = listDeadlineForDate(rest) == ui.showNoTask();

        if (!noTodo) {
            output = output + listTodoForDate(rest);
        }
        if (!noDeadline) {
            output = output + listDeadlineForDate(rest);
        }

        if (noTodo && noDeadline) {
            output = ui.showNoTask();
        }

        return output;
    }

    public String listTodoForDate(String date) {
        assert Parser.isValidDateFormat(date) : "The date to view is invalid!";
        String output;
        output = "This is the summary of todo list for the day!\n";
        TaskList todoList = totalList.viewTodoTask();
        output = output + ui.showTaskList(todoList) + "\n";
        return output;
    }

    public String listDeadlineForDate(String date) {
        assert Parser.isValidDateFormat(date) : "The date to view is invalid!";
        String output = "This is the summary of Deadline list for the day!\n";
        TaskList deadlineList = totalList.viewDeadlineTask(date);
        output = output + ui.showTaskList(deadlineList);
        return output;
    }
}
