package parser;

import commands.Command;
import exceptions.AliceException;
import exceptions.MissingArgumentException;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Parses user input and triggers the corresponding actions,
 * by activating TaskList and Ui.
 */
public class Parser {
    private boolean isBye;
    private final TaskList LIST;
    private final Ui UI;

    public Parser(TaskList list, Ui ui) {
        isBye = false;
        this.LIST = list;
        this.UI = ui;
    }

    /**
     * Parses user input, breaking down actions by commands.
     *
     * @param input the string to be parsed.
     * @throws AliceException if command is invalid, or if task number is invalid, or if task is missing arguments.
     */
    public void parse(String input) throws AliceException {
        String[] result = input.split(" ", 2);
        Command command = Command.stringToCommand(result[0]);
        Task addedTask = new Task("");

        switch (command) {
        case BYE:
            isBye = true;
            break;
        case LIST:
            UI.printListedTasks(LIST.listTasks(), LIST.getSize());
            break;
        case MARK:
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(result[1]);
            } catch (NumberFormatException e) {
                System.out.println(result[1] + " is not a number!");
                System.out.println("------------------------------------------");
                break;
            }
            Task markTask = LIST.markTask(taskNumber);
            UI.printHandleTaskMessage(markTask, "mark");
            break;
        case UNMARK:
            int taskNum;
            try {
                taskNum = Integer.parseInt(result[1]);
            } catch (NumberFormatException e) {
                System.out.println(result[1] + " is not a number!");
                System.out.println("------------------------------------------");
                break;
            }
            Task unmarkTask = LIST.unmarkTask(taskNum);
            UI.printHandleTaskMessage(unmarkTask, "unmark");
            break;
        case DELETE:
            int taskNo;
            try {
                taskNo = Integer.parseInt(result[1]);
            } catch (NumberFormatException e) {
                System.out.println(result[1] + " is not a number!");
                System.out.println("------------------------------------------");
                break;
            }
            Task task = LIST.deleteTask(taskNo);
            UI.printHandleTaskMessage(task, "delete", LIST.getSize());
            break;
        case TODO:
            // result[1] contains description
            if (result.length != 2) {
                throw new MissingArgumentException("Todo", new String[]{"description"});
            }
            addedTask = LIST.addTask(Command.TODO, result);
            break;
        case DEADLINE:
            // result[1] contains description /by deadline
            if (result.length != 2) {
                throw new MissingArgumentException("Deadline", new String[]{"description, by"});
            }
            String[] deadlineInfo = result[1].split("/by ");
            if (deadlineInfo.length != 2) {
                throw new MissingArgumentException("Deadline", new String[]{"description, by"});
            }
            addedTask = LIST.addTask(Command.DEADLINE, deadlineInfo);
            break;
        case EVENT:
            // result[1] contains description /from from /to to
            if (result.length != 2) {
                throw new MissingArgumentException("Event", new String[]{"description, from, to"});
            }
            String[] eventInfo = result[1].split("/from ");
            if (eventInfo.length != 2) {
                throw new MissingArgumentException("Event", new String[]{"description, from, to"});
            }
            String[] times = eventInfo[1].split("/to ");
            if (times.length != 2) {
                throw new MissingArgumentException("Event", new String[]{"description, from, to"});
            }
            addedTask = LIST.addTask(Command.EVENT, eventInfo);
            break;
        default:
            throw new AliceException(input);
        }
        if (command == Command.TODO || command == Command.DEADLINE || command == Command.EVENT) {
            UI.printHandleTaskMessage(addedTask, "add", LIST.getSize());
        }
    }

    /**
     * Returns whether the command is "bye".
     * @return isBye
     */
    public boolean isBye() {
        return this.isBye;
    }
}
