package chatbot;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

import chatbot.exceptions.BeeException;
import task.Deadline;
import task.Event;
import task.exceptions.InvalidDurationException;
import task.exceptions.InvalidInputException;
import task.Task;
import task.exceptions.TaskIndexException;
import task.TaskList;
import task.Todo;


/**
 * Starts the chatbot "Bee".
 *
 * @author celeschai
 */
public class Bee {
    private TaskList taskList;
    private Storage storage;
    private Boolean isFileOpen;

    public Bee() {
        this.taskList = new TaskList();
        this.storage = new Storage();

        // Checks if source text file is successfully opened
        this.isFileOpen = this.storage.readFromTaskListFile();
    }

    public String welcomeMessage() {
        if (isFileOpen) {
            // Parses input from saved text file
            Parser.parseFromTxtTaskList(this.storage.getScanner(), this.taskList);

            // Welcomes user
            return Ui.LOGO + "\nHello, I'm Bee! What can I do for you?";
        } else {
            return "I can't seem to access your saved files / save a new file on your local disk, please restart.";
        }
    }

    public String getBeeResponse(String input) {
        try {
            if (input.matches("bye.*")) {
                return userLeavesResponse();
            } else if (input.equalsIgnoreCase("list")) {
                return listAllTasks();

            } else if (input.matches("mark (.+)")) {
                Matcher matcher = Parser.getMatcher(input, "mark (\\d+)");
                if (matcher.matches()) {
                    return markTaskAsDoneResponse(matcher);
                }

            } else if (input.matches("unmark (.+)")) {
                Matcher matcher = Parser.getMatcher(input, "unmark (\\d+)");
                if (matcher.matches()) {
                    return markTaskAsIncompleteResponse(matcher);
                }

            } else if (input.matches("delete (.+)")) {
                Matcher matcher = Parser.getMatcher(input, "delete (\\d+)");
                if (matcher.matches()) {
                    return deleteTaskResponse(matcher);
                }

            } else if (input.matches("todo (.*)")) {
                Matcher matcher = Parser.getMatcher(input, "todo (\\S.*)");
                if (matcher.matches()) {
                    return addTodoResponse(matcher);
                } else {
                    throw new InvalidInputException("name", "task");
                }

            } else if (input.matches("deadline (.*)")) {
                Matcher matcher = Parser.getMatcher(input, "deadline (\\S.*) /by (\\S.*)");
                if (matcher.matches()) {
                    return addDeadLineResponse(matcher);
                } else {
                    throw new InvalidInputException("name, and due date", "deadline");
                }

            } else if (input.matches("event (.*)")) {
                Matcher matcher = Parser.getMatcher(input, "event (\\S.*) /from (\\S.*) /to (\\S.*)");
                if (matcher.matches()) {
                    return addEventResponse(matcher);
                }

            } else if (input.matches("help")) {
                return Ui.HELP;
            } else {
                // Throws exception when nothing, whitespaces, or no name is provided
                throw new BeeException("Say something helpful.");
            }

        } catch (BeeException e) {
            return e.getMessage();

        } catch (DateTimeParseException e) {
            // Guides user on date time input formats
            return "Format your time in: "
                    + "yyyy-MM-dd HHmm (24Hr) or yyyy-MM-dd hh:mm am (12Hr).\n"
                    + "You can replace - with \\ as well";
        }
        return "I don't understand what do you mean by: " + input;
    }

    private String addEventResponse(Matcher matcher) throws InvalidDurationException {
        String name = matcher.group(1);
        String fromParam = matcher.group(2);
        String toParam = matcher.group(3);

        Event event = new Event(name, fromParam, toParam);
        this.taskList.addTask(event);
        return Ui.addTaskResponse(event.toString(), this.taskList.getTotalNumOfTasks());
    }

    private String addDeadLineResponse(Matcher matcher) {
        String name = matcher.group(1);
        String byParam = matcher.group(2);

        Deadline deadline = new Deadline(name, byParam);
        this.taskList.addTask(deadline);
        return Ui.addTaskResponse(deadline.toString(), this.taskList.getTotalNumOfTasks());
    }

    private String addTodoResponse(Matcher matcher) {
        String name = matcher.group(1);
        Todo todo = new Todo(name);
        this.taskList.addTask(todo);
        return Ui.addTaskResponse(
                todo.toString(), this.taskList.getTotalNumOfTasks());
    }

    private String deleteTaskResponse(Matcher matcher) throws TaskIndexException {
        String num = matcher.group(1);
        int index = Integer.parseInt(num);

        if (this.taskList.isTaskExist(index)) {
            Task deletedTask = this.taskList.removeTask(index);

            return Ui.deleteTaskResponse(
                    deletedTask.toString(), this.taskList.getTotalNumOfTasks());

        } else {
            throw new TaskIndexException();
        }
    }

    private String markTaskAsIncompleteResponse(Matcher matcher) throws TaskIndexException {
        String num = matcher.group(1);
        int index = Integer.parseInt(num);

        if (this.taskList.isTaskExist(index)) {
            this.taskList.markTaskAsIncomplete(index);
            return String.format("OK, I've marked this task as not done yet:\n    %s",
                    this.taskList.getTask(index));
        } else {
            throw new TaskIndexException();
        }
    }

    private String markTaskAsDoneResponse(Matcher matcher) throws TaskIndexException {
        String num = matcher.group(1);
        int index = Integer.parseInt(num);

        if (this.taskList.isTaskExist(index)) {
            this.taskList.markTaskAsDone(index);
            return String.format(
                    "Nice! I've marked this task as done:\n    %s",
                    this.taskList.getTask(index));
        } else {
             throw new TaskIndexException();
        }
    }

    private String listAllTasks() {
        return "Here are the tasks in your list:\n".concat(this.taskList.toString());
    }

    private String userLeavesResponse() {
        boolean isSaved = this.storage.writeToTaskListFile(this.taskList.parseTaskListToTxt());
        if (isSaved) {
            return "Bye~ Hope to see you again soon!";
        }
        return "Tasks not saved, please try again.";
    }
}
