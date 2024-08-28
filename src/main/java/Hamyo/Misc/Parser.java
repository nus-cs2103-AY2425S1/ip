package Hamyo.Misc;

import Hamyo.Tasks.TaskList;
import Hamyo.Tasks.TaskType;

/**
 * Deals with making sense of the user command.
 *
 * @author Han Yu
 */
public class Parser {

    /**
     * Parse method takes in user input from terminal and executes the command.
     * If no command is found, a HamyoException occurs and prompted in terminal.
     *
     * @param fullCommand The full command/ input given by the user.
     * @param tasks The list of the users' tasks.
     * @return false if the user command ("bye") terminates the chatbot, true
     * otherwise. false: application terminates, true: application continues.
     */
    public static boolean parse(String fullCommand, TaskList tasks) {
        String commandType = fullCommand.split(" ")[0];
        String commandFields = fullCommand.substring(commandType.length());
        try {
            switch (commandType) {
            case "todo":
                tasks.addTask(TaskType.TODO, commandFields);
                break;
            case "deadline":
                tasks.addTask(TaskType.DEADLINE, commandFields);
                break;
            case "event":
                tasks.addTask(TaskType.EVENT, commandFields);
                break;
            case "list":
                tasks.listTasks();
                break;
            case "listDate":
                tasks.listTasksByDate(commandFields);
                break;
            case "mark":
                tasks.markTask(commandFields);
                break;
            case "unmark":
                tasks.unmarkTask(commandFields);
                break;
            case "delete":
                tasks.deleteTask(commandFields);
                break;
            case "bye":
                return false;
            default:
                throw new HamyoException("Invalid Command!");
            }
        } catch (HamyoException e) {
            UI.printException(e);
        }
        return true;
    }

}
