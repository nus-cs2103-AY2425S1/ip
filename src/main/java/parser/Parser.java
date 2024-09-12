package parser;

import commands.Command;
import exceptions.AliceException;
import exceptions.FormatException;
import exceptions.MissingArgumentException;
import storage.TaskList;
import tasks.Priority;
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
            return ui.getListedTasks(list.getSize(),
                    list.findTaskOfPriority(Priority.HIGH),
                    list.findTaskOfPriority(Priority.MEDIUM),
                    list.findTaskOfPriority(Priority.LOW));
        case MARK:
            String[] markParams = result[1].split(" ");
            int markTaskNumber = checkValidTaskNumber(markParams[0]);
            checkValidTaskInput(Command.MARK, markParams);
            Task markTask = list.markTask(markTaskNumber, markParams[1]);
            return ui.getHandleTaskMessage(markTask, "mark");
        case UNMARK:
            String[] unmarkParams = result[1].split(" ");
            int unmarkTaskNumber = checkValidTaskNumber(unmarkParams[0]);
            checkValidTaskInput(Command.UNMARK, unmarkParams);
            Task unmarkTask = list.unmarkTask(unmarkTaskNumber, unmarkParams[1]);
            return ui.getHandleTaskMessage(unmarkTask, "unmark");
        case DELETE:
            String[] deleteParams = result[1].split(" ");
            int deleteTaskNumber = checkValidTaskNumber(deleteParams[0]);
            checkValidTaskInput(Command.DELETE, deleteParams);
            Task task = list.deleteTask(deleteTaskNumber, deleteParams[1]);
            return ui.getHandleTaskMessage(task, "delete", list.getSize());
        case TODO:
            // result[1] contains description /p priority
            checkValidTaskInput(Command.TODO, result);

            String[] todoParams = result[1].split("/p");
            addedTask = todoParams.length == 1
                    ? list.addTask(Command.TODO, todoParams[0])
                    : list.addTask(Command.TODO, todoParams[0], todoParams[1].strip());
            break;
        case DEADLINE:
            // result[1] contains description /by deadline /p priority
            checkValidTaskInput(Command.DEADLINE, result);

            String[] deadlineInfo = result[1].split("/by ");
            checkValidTaskInput(Command.DEADLINE, deadlineInfo);

            String[] deadlineParams = deadlineInfo[1].split("/p");

            addedTask = deadlineParams.length == 1
                    ? list.addTask(Command.DEADLINE, deadlineInfo[0], deadlineParams[0].strip())
                    : list.addTask(Command.DEADLINE, deadlineInfo[0], deadlineParams[0].strip(),
                        deadlineParams[1].strip());
            break;
        case EVENT:
            // result[1] contains description /from from /to to /p priority
            checkValidTaskInput(Command.EVENT, result);

            String[] eventInfo = result[1].split("/from ");
            checkValidTaskInput(Command.EVENT, eventInfo);

            String[] times = eventInfo[1].split("/to ");
            checkValidTaskInput(Command.EVENT, times);

            String[] eventParams = times[1].split("/p");

            addedTask = eventParams.length == 1
                    ? list.addTask(Command.EVENT, eventInfo[0], times[0].strip(), eventParams[0].strip())
                    : list.addTask(Command.EVENT, eventInfo[0], times[0].strip(), eventParams[0].strip(),
                        eventParams[1].strip());
            break;
        case FIND:
            return ui.getFilteredTasks(list.findTasks(result[1]));
        case EXIT:
            return "";
        case PRIORITY:
            String[] params = result[1].split(" ");
            int taskNumber = checkValidTaskNumber(params[0]);
            checkValidTaskInput(Command.PRIORITY, params);
            Task updateTask = list.updatePriority(taskNumber, params[1].strip(), params[2].strip());
            return ui.getHandleTaskMessage(updateTask, "priority", params[2].strip());
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

    /**
     * Returns the task number if it is a number, throws exception otherwise.
     *
     * @param input the task number as a string.
     * @return the parsed task number as a number.
     * @throws FormatException when string cannot be parsed to a number.
     */
    private int checkValidTaskNumber(String input) throws FormatException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new FormatException(input);
        }
    }

    /**
     * Checks if the user supplied  information required for the task type.
     *
     * @param command type of task.
     * @param taskInfo inputs to check.
     * @throws MissingArgumentException when there is a missing argument.
     */
    private void checkValidTaskInput(Command command, String[] taskInfo) throws MissingArgumentException {
        if (command == Command.PRIORITY && taskInfo.length == 3) {
            return;
        } else if (command == Command.PRIORITY) {
            throwMissingArgumentException(command);
        }
        if (taskInfo.length != 2) {
            throwMissingArgumentException(command);
        }
    }

    /**
     * Throws the MissingArgumentException when called, based on the type of task.
     *
     * @param command the type of task.
     * @throws MissingArgumentException when called.
     */
    private void throwMissingArgumentException(Command command) throws MissingArgumentException {
        switch (command) {
        case TODO:
            throw new MissingArgumentException("Todo", "description");
        case DEADLINE:
            throw new MissingArgumentException("Deadline", "description", "by");
        case EVENT:
            throw new MissingArgumentException("Event", "description", "from", "to");
        case MARK:
            throw new MissingArgumentException("Mark", "taskNumber", "priority");
        case UNMARK:
            throw new MissingArgumentException("Unmark", "taskNumber", "priority");
        case DELETE:
            throw new MissingArgumentException("Delete", "taskNumber", "priority");
        case PRIORITY:
            throw new MissingArgumentException("Priority", "taskNumber", "priority", "newPriority");
        default:
            break;
        }
    }
}
