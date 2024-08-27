package parser;

import commands.Command;
import exceptions.AliceException;
import exceptions.MissingArgumentException;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

public class Parser {
    private boolean isBye;
    private final TaskList list;
    private final Ui ui;

    public Parser(TaskList list, Ui ui) {
        isBye = false;
        this.list = list;
        this.ui = ui;
    }

    public void parse(String input) throws AliceException {
        String[] result = input.split(" ", 2);
        Command command = Command.stringToCommand(result[0]);
        Task addedTask = new Task("");

        switch (command) {
            case BYE:
                isBye = true;
                break;
            case LIST:
                ui.printListedTasks(list.listTasks(), list.getSize());
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
                Task markTask = list.markTask(taskNumber);
                ui.printHandleTaskMessage(markTask, "mark");
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
                Task unmarkTask = list.unmarkTask(taskNum);
                ui.printHandleTaskMessage(unmarkTask, "unmark");
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
                Task task = list.deleteTask(taskNo);
                ui.printHandleTaskMessage(task, "delete", list.getSize());
                break;
            case TODO:
                // result[1] contains description
                if (result.length != 2) {
                    throw new MissingArgumentException("Todo", new String[]{"description"});
                }
                addedTask = list.addTask(Command.TODO, result);
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
                addedTask = list.addTask(Command.DEADLINE, deadlineInfo);
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
                addedTask = list.addTask(Command.EVENT, eventInfo);
                break;
        case FIND:
            ui.printFilteredTasks(list.findTasks(result[1]));
            break;
            default:
                throw new AliceException(input);
        }
        if (command == Command.TODO || command == Command.DEADLINE || command == Command.EVENT) {
            ui.printHandleTaskMessage(addedTask, "add", list.getSize());
        }
    }

    public boolean isBye() {
        return this.isBye;
    }
}
