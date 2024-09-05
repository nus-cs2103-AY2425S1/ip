package tira;

import tira.task.TaskList;

/*
Parser class:
1. Splits the initial command and then calls the appropriate function
if command is list, calls the getTaskList method from TaskList
if command is delete, cal
 */
public class Parser {
    private TaskList taskList;
    private final Ui ui = new Ui();

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void parseCommand(String command) throws TiraException {
        String [] splitCommand = command.split(" ");
        String taskType = splitCommand[0];
        try {
            switch (taskType) {
                case "list":
                    ui.showTaskList(taskList);
                case "mark":
                    taskList.markTask(command, splitCommand);
                    break;
                case "unmark":
                    taskList.unmarkTask(command, splitCommand);
                    break;
                case "delete":
                    taskList.delete(splitCommand);
                    break;
                case "todo":
                    taskList.addToDo(command, splitCommand);
                    break;
                case "deadline":
                    taskList.addDeadline(command, splitCommand);
                    break;
                case "event":
                    taskList.addDeadline(command, splitCommand);
                    break;
                default:
                    throw new TiraException("What task is this??? Please rethink your task!");
            }
        } catch (TiraException e) {
            System.out.println(e.getMessage());
        }
    }
}
