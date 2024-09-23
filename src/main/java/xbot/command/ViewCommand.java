package xbot.command;

import xbot.TaskList;
import xbot.exception.XBotException;
import xbot.parser.Parser;
import xbot.storage.Storage;
import xbot.ui.Ui;

/**
 * Handles the "view" command.
 */
public class ViewCommand implements Command {
    private TaskList totalList = new TaskList();
    private Ui ui = new Ui();

    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException {
        if (rest.trim().isEmpty()) {
            throw new XBotException("Mmm... please enter a date you want to view! >.<");
        }
        this.totalList = list;
        this.ui = ui;
        return listTaskForDate(rest.trim());
    }

    /**
     * Retrieves and formats tasks for a specific date.
     *
     * @param rest the date string in a valid format (D/M/YYYY)
     * @return a string message displaying the tasks for the specified date
     * @throws XBotException if the input date string is invalid
     */
    public String listTaskForDate(String rest) throws XBotException {
        //Check if rest is a valid date
        if (!Parser.isValidDateFormatOnly(rest)) {
            throw new XBotException("Sorry...I cannot read this date input >_< \n"
                    + "you might want to try these date format instead :0\n"
                    + "D/M/YYYY (e.g. 9/4/2024)");
        }

        boolean noTodo = listTodoForDate(rest).equalsIgnoreCase("");
        boolean noDeadline = listDeadlineForDate(rest).equalsIgnoreCase("");
        boolean noEvent = listEventForDate(rest).equalsIgnoreCase("");


        String output = "";
        if (noTodo && noDeadline && noEvent) {
            output = ui.showNoTask();
        } else {
            output = "These are the tasks you have on " + Parser.changeDateFormat(rest) + " !! :)\n\n";
        }

        output = output + listTodoForDate(rest);
        output = output + listDeadlineForDate(rest);
        output = output + listEventForDate(rest);

        return output;
    }

    /**
     * Retrieves and formats todo task for a specific date.
     *
     * @param date the date string in a valid format (D/M/YYYY)
     * @return a string message displaying the tasks for the specified date
     * @throws XBotException if the input date string is invalid
     */
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

    /**
     * Retrieves and formats deadline tasks for a specific date.
     *
     * @param date the date string in a valid format (D/M/YYYY)
     * @return a string message displaying the tasks for the specified date
     * @throws XBotException if the input date string is invalid
     */
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

    /**
     * Retrieves and formats event tasks for a specific date.
     *
     * @param date the date string in a valid format (D/M/YYYY)
     * @return a string message displaying the tasks for the specified date
     * @throws XBotException if the input date string is invalid
     */
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
