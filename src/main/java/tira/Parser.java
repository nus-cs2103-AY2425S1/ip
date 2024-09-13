package tira;

import tira.task.TaskList;

/**
 * The Parser class is responsible for interpreting user input commands
 * and calling the appropriate functions in the {@link TaskList} class.
 * Depending on the command, it may list tasks, mark/unmark tasks,
 * add tasks (ToDo, Deadline, Event), or delete and find tasks.
 */
public class Parser {
    private TaskList taskList;
    private final Ui ui = new Ui();
    /**
     * Constructs a Parser instance with the specified taskList.
     *
     * @param taskList The task list that this parser will operate on.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user command and determines which method to call based on the command.
     *
     * @param command Main command entered by user.
     * @throws TiraException Custom Tira exception class.
     */
    public void parseCommand(String command) throws TiraException {
        String [] commandSplitBySpace = command.split(" ");
        String taskType = commandSplitBySpace[0];
        try {
            switch(taskType) {
            case "list":
                ui.showTaskList(taskList);
                break;
            case "mark":
                taskList.markTask(command, commandSplitBySpace);
                break;
            case "unmark":
                taskList.unmarkTask(command, commandSplitBySpace);
                break;
            case "delete":
                taskList.delete(commandSplitBySpace);
                break;
            case "todo":
                taskList.addToDo(command, commandSplitBySpace);
                break;
            case "deadline":
                taskList.addDeadline(command, commandSplitBySpace);
                break;
            case "event":
                taskList.addEvent(command, commandSplitBySpace);
                break;
            case "find":
                taskList.findTask(command, commandSplitBySpace);
                break;
            default:
                throw new TiraException("What task is this??? Please rethink your task!");
            }
        } catch (TiraException e) {
            System.out.println(e.getMessage());
        }
    }
}
