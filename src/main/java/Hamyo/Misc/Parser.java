package Hamyo.Misc;

import Hamyo.Tasks.TaskList;
import Hamyo.Tasks.TaskType;

/**
 * Parser - deals with loading tasks from the file and saving tasks in the file.
 */
public class Parser {

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
      case "find":
        tasks.listTasksByKeyword(commandFields);
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
