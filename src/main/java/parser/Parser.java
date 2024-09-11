package parser;

import commands.Command;
import exceptions.AliceException;
import exceptions.FormatException;
import exceptions.MissingArgumentException;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Parses user input and triggers the corresponding actions,
 * by activating TaskList and Ui.
 */
public class Parser {
    private final TaskList list;
    private final Ui ui;

    /**
     * Initialises a Parser object with a TaskList and Ui.
     *
     * @param list the list for storing the tasks.
     * @param ui the ui for printing output to the terminal.
     */
    public Parser(TaskList list, Ui ui) {
        this.list = list;
        this.ui = ui;
    }

    /**
     * Parses user input, breaking down actions by commands.
     *
     * @param input the string to be parsed.
     * @return response of Alice.
     * @throws AliceException if command is invalid, or if task number is invalid, or if task is missing arguments.
     */
    public String parse(String input) throws AliceException {
        String[] result = input.split(" ", 2);
        Command command = Command.stringToCommand(result[0]);
        Task addedTask;

        switch (command) {
        case BYE:
            return ui.showExitMessage();
        case LIST:
            return ui.getListedTasks(list.listTasks(), list.getSize());
        case MARK:
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(result[1]);
            } catch (NumberFormatException e) {
                throw new FormatException(result[1]);
            }
            Task markTask = list.markTask(taskNumber);
            return ui.getHandleTaskMessage(markTask, "mark");
        case UNMARK:
            int taskNum;
            try {
                taskNum = Integer.parseInt(result[1]);
            } catch (NumberFormatException e) {
                throw new FormatException(result[1]);
            }
            Task unmarkTask = list.unmarkTask(taskNum);
            return ui.getHandleTaskMessage(unmarkTask, "unmark");
        case DELETE:
            int taskNo;
            try {
                taskNo = Integer.parseInt(result[1]);
            } catch (NumberFormatException e) {
                throw new FormatException(result[1]);
            }
            Task task = list.deleteTask(taskNo);
            return ui.getHandleTaskMessage(task, "delete", list.getSize());
        case TODO:
            // result[1] contains description
            if (result.length != 2) {
                throw new MissingArgumentException("Todo", "description");
            }
            addedTask = list.addTask(Command.TODO, result);
            break;
        case DEADLINE:
            // result[1] contains description /by deadline
            if (result.length != 2) {
                throw new MissingArgumentException("Deadline", "description", "by");
            }
            String[] deadlineInfo = result[1].split("/by ");
            if (deadlineInfo.length != 2) {
                throw new MissingArgumentException("Deadline", "description", "by");
            }
            addedTask = list.addTask(Command.DEADLINE, deadlineInfo);
            break;
        case EVENT:
            // result[1] contains description /from from /to to
            if (result.length != 2) {
                throw new MissingArgumentException("Event", "description", "from", "to");
            }
            String[] eventInfo = result[1].split("/from ");
            if (eventInfo.length != 2) {
                throw new MissingArgumentException("Event", "description", "from", "to");
            }
            String[] times = eventInfo[1].split("/to ");
            if (times.length != 2) {
                throw new MissingArgumentException("Event", "description", "from", "to");
            }
            addedTask = list.addTask(Command.EVENT, eventInfo);
            break;
        case FIND:
            return ui.getFilteredTasks(list.findTasks(result[1]));
        default:
            throw new AliceException(input);
        }

        // check that only "todo", "deadline", and "event" prints the task message
        assert result[0].equals("todo")
                || result[0].equals("deadline")
                || result[0].equals("event")
                : "the command should be adding a task!";

        // prints task messages for todos, deadlines, and events
        return ui.getHandleTaskMessage(addedTask, "add", list.getSize());
    }
}
