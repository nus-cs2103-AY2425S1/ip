package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            Object[] markParams = parseAndCheck(Command.MARK, result[1]);
            Task markTask = list.markTask((Integer) markParams[0], (String) markParams[1]);
            return ui.getHandleTaskMessage(markTask, "mark");
        case UNMARK:
            Object[] unmarkParams = parseAndCheck(Command.UNMARK, result[1]);
            Task unmarkTask = list.unmarkTask((Integer) unmarkParams[0], (String) unmarkParams[1]);
            return ui.getHandleTaskMessage(unmarkTask, "unmark");
        case DELETE:
            Object[] deleteParams = parseAndCheck(Command.DELETE, result[1]);
            Task task = list.deleteTask((Integer) deleteParams[0], (String) deleteParams[1]);
            return ui.getHandleTaskMessage(task, "delete", list.getSize());
        case TODO:
            List<String> todoParams = parseEvents(Command.TODO, result);
            addedTask = todoParams.size() == 1
                    ? list.addTask(Command.TODO, todoParams.get(0))
                    : list.addTask(Command.TODO, todoParams.get(0), todoParams.get(1));
            break;
        case DEADLINE:
            List<String> deadlineParams = parseEvents(Command.DEADLINE, result);
            addedTask = deadlineParams.size() == 2
                    ? list.addTask(Command.DEADLINE, deadlineParams.get(0), deadlineParams.get(1))
                    : list.addTask(Command.DEADLINE, deadlineParams.get(0), deadlineParams.get(1),
                        deadlineParams.get(2));
            break;
        case EVENT:
            List<String> eventParams = parseEvents(Command.EVENT, result);
            addedTask = eventParams.size() == 3
                    ? list.addTask(Command.EVENT, eventParams.get(0), eventParams.get(1), eventParams.get(2))
                    : list.addTask(Command.EVENT, eventParams.get(0), eventParams.get(1), eventParams.get(2),
                        eventParams.get(3));
            break;
        case FIND:
            return ui.getFilteredTasks(list.findTasks(result[1]));
        case EXIT:
            return "";
        case PRIORITY:
            Object[] priorityParams = parseAndCheck(Command.PRIORITY, result[1]);
            Task updateTask = list.updatePriority((Integer) priorityParams[0],
                    (String) priorityParams[1], (String) priorityParams[2]);
            return ui.getHandleTaskMessage(updateTask, "priority", (String) priorityParams[2]);
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

    /**
     * Returns parsed params from input string.
     * Only for commands MARK, UNMARK, DELETE, PRIORITY.
     *
     * @param command the type of command.
     * @param input the user input.
     * @return the inputted params.
     */
    private Object[] parseAndCheck(Command command, String input) throws AliceException {
        switch (command) {
        case MARK:
            String[] markParams = input.split(" ");
            int markTaskNumber = checkValidTaskNumber(markParams[0]);
            checkValidTaskInput(Command.MARK, markParams);
            return new Object[] {markTaskNumber, markParams[1]};
        case UNMARK:
            String[] unmarkParams = input.split(" ");
            int unmarkTaskNumber = checkValidTaskNumber(unmarkParams[0]);
            checkValidTaskInput(Command.UNMARK, unmarkParams);
            return new Object[] {unmarkTaskNumber, unmarkParams[1]};
        case DELETE:
            String[] deleteParams = input.split(" ");
            int deleteTaskNumber = checkValidTaskNumber(deleteParams[0]);
            checkValidTaskInput(Command.DELETE, deleteParams);
            return new Object[] {deleteTaskNumber, deleteParams[1]};
        case PRIORITY:
            String[] params = input.split(" ");
            int taskNumber = checkValidTaskNumber(params[0]);
            checkValidTaskInput(Command.PRIORITY, params);
            return new Object[] {taskNumber, params[1].strip(), params[2].strip()};
        default:
            return new Object[0];
        }
    }

    private List<String> parseEvents(Command command, String[] input) throws AliceException {
        switch (command) {
        case TODO:
            checkValidTaskInput(Command.TODO, input);
            String[] todoParams = input[1].split("/p");
            return Arrays.stream(todoParams).map(String::strip).toList();
        case DEADLINE:
            checkValidTaskInput(Command.DEADLINE, input);
            String[] deadlineInfo = input[1].split("/by ");
            checkValidTaskInput(Command.DEADLINE, deadlineInfo);

            String[] deadlineParams = deadlineInfo[1].split("/p");
            List<String> params = new ArrayList<>(List.of(deadlineInfo[0]));
            Arrays.stream(deadlineParams).map(String::strip).forEach(params::add);
            return params;
        case EVENT:
            checkValidTaskInput(Command.EVENT, input);

            String[] eventInfo = input[1].split("/from ");
            checkValidTaskInput(Command.EVENT, eventInfo);

            String[] times = eventInfo[1].split("/to ");
            checkValidTaskInput(Command.EVENT, times);

            String[] eventParams = times[1].split("/p");
            List<String> allEventParams = new ArrayList<>(List.of(eventInfo[0], times[0].strip()));
            Arrays.stream(eventParams).map(String::strip).forEach(allEventParams::add);
            return allEventParams;
        default:
            return List.of();
        }
    }
}
