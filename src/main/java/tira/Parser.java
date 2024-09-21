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
    private Ui ui;
    /**
     * Constructs a Parser instance with the specified taskList.
     *
     * @param taskList The task list that this parser will operate on.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    /**
     * Parses the user command and determines which method to call based on the command.
     *
     * @param command Main command entered by user.
     * @throws TiraException Custom Tira exception class.
     */
    public String parseCommand(String command) throws TiraException {
        String [] commandSplitBySpace = command.split(" ");
        String taskType = commandSplitBySpace[0];
        try {
            switch(taskType) {
            case "list":
                return ui.showTaskList(taskList);
            case "mark":
                return(taskList.markTask(command, commandSplitBySpace));
            case "unmark":
                return(taskList.unmarkTask(command, commandSplitBySpace));
            case "delete":
                return(taskList.delete(commandSplitBySpace));
            case "todo":
                return(taskList.addToDo(command, commandSplitBySpace));
            case "deadline":
                return(taskList.addDeadline(command, commandSplitBySpace));
            case "event":
                return(taskList.addEvent(command, commandSplitBySpace));
            case "find":
                return(taskList.findTask(command, commandSplitBySpace));
            case "statistics":
                ui.showStatistics(taskList.getTasks());
                return ui.getOutMessage();
            default:
                throw new TiraException("MRAWWW??? What task is this??? Please rethink your task!");
            }
        } catch (TiraException e) {
            return e.getMessage();
        }
    }
}
