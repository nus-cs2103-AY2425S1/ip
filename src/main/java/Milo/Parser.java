package Milo;

import Milo.TaskObj.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/*
* Represents Milo's logic system, how the commands are interpreted
* these commands include: list, mark, unmark, delete, to-do, deadline, event,
* bye
 */
public class Parser {
    /*
    * Interprets user input as one of the aforementioned command
    * subsequently, executes the respective command
    * given that it is formatted correctly
    * else an error is thrown and printed
     */
    public static void readInput(String userInput, ArrayList<Task> todoList) {
        String[] arrOfInput = userInput.split(" ", 2);
        String action = arrOfInput[0];
        switch (action) {
            // Show list
            case "list":
                Ui.printList(todoList);
                break;
            // Mark as complete
            case "mark":
                Task curTask = todoList.get(Integer.parseInt(arrOfInput[1]) - 1);
                curTask.mark();
                Ui.printMark(curTask);
                break;
            // Mark as incomplete
            case "unmark":
                Task currTask = todoList.get(Integer.parseInt(arrOfInput[1]) - 1);
                currTask.unmark();
                Ui.printUnmark(currTask);
                break;
            // Deleting tasks
            case "delete":
                int delIndex = Integer.parseInt(arrOfInput[1]) - 1;
                Task currrTask = todoList.get(delIndex);
                currrTask.delete();
                todoList.remove(delIndex);
                Ui.printDelete(currrTask);
                break;
            // Adding tasks
            // Todos
            case "todo":
                // Check case where todos empty
                if (arrOfInput.length == 1) {
                    Ui.printError(TaskType.taskType.TODO, "The description of a todo cannot be empty");
                } else {
                    String desc = arrOfInput[1].strip();
                    Task curTodo = new Todos(desc);
                    todoList.add(curTodo);
                    Ui.printTask(curTodo);
                }
                break;
            // Deadline
            case "deadline":
                // Check case where deadline empty
                if (arrOfInput.length == 1) {
                    Ui.printError(TaskType.taskType.DEADLINE, "The description of a deadline cannot be empty");
                } else {
                    // Check case where deadline command is not properly formatted
                    String[] deadlineDesc = arrOfInput[1].split("/by", 2);
                    if (deadlineDesc.length != 2) {
                        Ui.printError(TaskType.taskType.DEADLINE, "Invalid deadline command\n Proper formatting: deadline <task description> + /by + <date description>");
                    } else {
                        try {
                            LocalDate curDate = LocalDate.parse(deadlineDesc[1].strip());
                            Task curDeadline = new Deadlines(deadlineDesc[0].strip(), curDate);
                            todoList.add(curDeadline);
                            Ui.printTask(curDeadline);
                        } catch (DateTimeParseException e) {
                            Ui.printError(TaskType.taskType.DATE, "");
                        }
                    }
                }
                break;
            // Event
            case "event":
                // Check case where event empty
                if (arrOfInput.length == 1) {
                    Ui.printError(TaskType.taskType.EVENT, "The description of an event cannot be empty");
                } else {
                    // Check case where event command is not properly formatted
                    String[] eventDesc = arrOfInput[1].split("/from | /to", 3);
                    if (eventDesc.length != 3) {
                        Ui.printError(TaskType.taskType.DEADLINE, "Invalid event command\n Proper formatting: deadline <task description> + /from + <starting date description> + /to + <ending date description");
                    } else {
                        try {
                            LocalDate fromDate = LocalDate.parse(eventDesc[1].strip());
                            LocalDate toDate = LocalDate.parse(eventDesc[1].strip());
                            Task curEvent = new Events(eventDesc[0].strip(), fromDate, toDate);
                            todoList.add(curEvent);
                            Ui.printTask(curEvent);
                        } catch (DateTimeParseException e) {
                            Ui.printError(TaskType.taskType.DATE, "");
                        }
                    }
                }
                break;
            case "bye":
                // Do nothing
                break;
            default:
                Ui.printError(TaskType.taskType.INVALID, "");
        }
    }
}
