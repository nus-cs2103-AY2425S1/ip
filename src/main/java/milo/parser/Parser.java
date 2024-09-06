package milo.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import milo.tasks.Deadline;
import milo.tasks.Event;
import milo.tasks.Task;
import milo.tasks.TaskList;
import milo.tasks.TaskTypes;
import milo.tasks.Todo;
import milo.ui.Ui;

/**
* Represents Milo's logic system, how the commands are interpreted
* these commands include: list, mark, unmark, delete, to-do, deadline, event,
* bye
 */
public class Parser {
    private final Ui ui;

    /**
    * Initialises the Parser Object
    *
    * @param ui object to initialise Parser with
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
    * Interprets user input as one of the aforementioned command
    * subsequently, executes the respective command
    * given that it is formatted correctly
    * else an error is thrown and printed
     */
    public void readInput(String userInput, TaskList todoList) {
        String[] arrOfInput = userInput.split(" ", 2);
        String action = arrOfInput[0];
        switch (action) {
        // Show list
        case "list":
            ui.printList(todoList);
            break;
        // Find task
        case "find":
            // Check for proper formatting
            if (arrOfInput.length != 2) {
                ui.printError(TaskTypes.TaskType.TODO, "The description of a find cannot be empty");
            } else {
                String desc = arrOfInput[1].strip();
                TaskList matchingTaskList = todoList.findMatchingTask(desc);
                ui.printFoundTask(matchingTaskList, matchingTaskList.getNumberOfTasks());
            }
            break;
        // Mark as complete
        case "mark":
            Task curTask = todoList.get(Integer.parseInt(arrOfInput[1]) - 1);
            curTask.mark();
            ui.printMark(curTask);
            break;
        // Mark as incomplete
        case "unmark":
            Task currTask = todoList.get(Integer.parseInt(arrOfInput[1]) - 1);
            currTask.unmark();
            ui.printUnmark(currTask);
            break;
        // Deleting tasks
        case "delete":
            int delIndex = Integer.parseInt(arrOfInput[1]) - 1;
            Task currrTask = todoList.get(delIndex);
            currrTask.delete();
            todoList.remove(delIndex);
            ui.printDelete(currrTask, todoList.getNumberOfTasks());
            break;
        // Adding tasks
        // Todos
        case "todo":
            // Check case where todos empty
            if (arrOfInput.length == 1) {
                ui.printError(TaskTypes.TaskType.TODO, "The description of a todo cannot be empty");
            } else {
                String desc = arrOfInput[1].strip();
                Task curTodo = new Todo(desc);
                todoList.add(curTodo);
                ui.printTask(curTodo, todoList.getNumberOfTasks());
            }
            break;
        // Deadline
        case "deadline":
            // Check case where deadline empty
            if (arrOfInput.length == 1) {
                ui.printError(TaskTypes.TaskType.DEADLINE, "The description of a deadline cannot be empty");
            } else {
                // Check case where deadline command is not properly formatted
                String[] deadlineDesc = arrOfInput[1].split("/by", 2);
                if (deadlineDesc.length != 2) {
                    ui.printError(TaskTypes.TaskType.DEADLINE, "Invalid deadline "
                            + "command\n Proper formatting: deadline <task description> + /by + <date description>");
                } else {
                    try {
                        LocalDate curDate = LocalDate.parse(deadlineDesc[1].strip());
                        Task curDeadline = new Deadline(deadlineDesc[0].strip(), curDate);
                        todoList.add(curDeadline);
                        ui.printTask(curDeadline, todoList.getNumberOfTasks());
                    } catch (DateTimeParseException e) {
                        ui.printError(TaskTypes.TaskType.DATE, "");
                    }
                }
            }
            break;
        // Event
        case "event":
            // Check case where event empty
            if (arrOfInput.length == 1) {
                ui.printError(TaskTypes.TaskType.EVENT, "The description of an event cannot be empty");
            } else {
                // Check case where event command is not properly formatted
                String[] eventDesc = arrOfInput[1].split("/from | /to", 3);
                if (eventDesc.length != 3) {
                    ui.printError(TaskTypes.TaskType.DEADLINE, "Invalid event "
                            + "command\n Proper formatting: deadline <task description> + /from + "
                            + "<starting date description> + /to + <ending date description");
                } else {
                    try {
                        LocalDate fromDate = LocalDate.parse(eventDesc[1].strip());
                        LocalDate toDate = LocalDate.parse(eventDesc[1].strip());
                        Task curEvent = new Event(eventDesc[0].strip(), fromDate, toDate);
                        todoList.add(curEvent);
                        ui.printTask(curEvent, todoList.getNumberOfTasks());
                    } catch (DateTimeParseException e) {
                        ui.printError(TaskTypes.TaskType.DATE, "");
                    }
                }
            }
            break;
        case "bye":
            // Do nothing
            break;
        default:
            ui.printError(TaskTypes.TaskType.INVALID, "");
        }
    }
}
