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
        if (rest.trim().isEmpty()) {
            throw new XBotException("Mmm... please enter a date you want to view! >.<");
        }
        this.totalList = list;
        this.ui = ui;
        return listTaskForDate(rest.trim());
    }

    public String listTaskForDate(String rest) throws XBotException {
        //Check if rest is a valid date
        if (!Parser.isValidDateFormatOnly(rest)) {
            throw new XBotException("Sorry...I cannot read this date input >_< \n" +
                    "you might want to try these date format instead :0\n" +
                    "D/M/YYYY (e.g. 9/4/2024)");
        }

        String output = "These are the tasks you have on " + Parser.changeDateFormat(rest) + " !! :)\n\n";
        boolean noTodo = listTodoForDate(rest) == ui.showNoTask();
        boolean noDeadline = listDeadlineForDate(rest) == ui.showNoTask();
        boolean noEvent = listEventForDate(rest) == ui.showNoTask();

        if (noTodo && noDeadline && noEvent) {
            output = ui.showNoTask();
        }

        output = output + listTodoForDate(rest);
        output = output + listDeadlineForDate(rest);
        output = output + listEventForDate(rest);

        return output;
    }

    public String listTodoForDate(String date) {
        assert Parser.isValidDateFormat(date) : "The date to view is invalid!";
        String output;
        output = "Todos: \n";
        TaskList todoList = totalList.viewTodoTask();
        String displayTodoList = ui.showTaskList(todoList);
        if (displayTodoList == ui.showNoTask()) {
            return "";
        }
        output = output + displayTodoList + "\n";
        return output;
    }

    public String listDeadlineForDate(String date) {
        assert Parser.isValidDateFormat(date) : "The date to view is invalid!";
        String output = "Deadlines: \n";
        TaskList deadlineList = totalList.viewDeadlineTask(date);
        String displayDeadlineList = ui.showTaskList(deadlineList);
        if (displayDeadlineList == ui.showNoTask()) {
            return "";
        }
        output = output + displayDeadlineList + "\n";
        return output;
    }

    public String listEventForDate(String date) {
        assert Parser.isValidDateFormat(date) : "The date to view is invalid!";
        String output = "Events: \n";
        TaskList eventList = totalList.viewEventTask(date);
        String displayEventList = ui.showTaskList(eventList);
        if (displayEventList == ui.showNoTask()) {
            return "";
        }
        output = output + displayEventList + "\n";
        return output;
    }
}
