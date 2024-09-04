package chatbot;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.Deadline;
import task.Event;
import task.InvalidInputException;
import task.Task;
import task.TaskIndexException;
import task.TaskList;
import task.Todo;


/**
 * Starts the chatbot "Bee".
 *
 * @author celeschai
 */
public class Bee {
    TaskList taskList;
    Storage storage;
    Boolean isFileOpen;

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
                boolean isSaved = this.storage.writeToTaskListFile(this.taskList.parseTaskListToTxt());
                if (isSaved) {
                    return "Bye~ Hope to see you again soon!";
                }
                return "Tasks not saved, please try again.";
            } else if (input.equalsIgnoreCase("list")) {
                return "Here are the tasks in your list:\n".concat(this.taskList.toString());

            } else if (input.matches("mark (.+)")) {
                // Uses regular expression to capture task index for completion
                Pattern pattern = Pattern.compile("mark (\\d+)");
                Matcher matcher = pattern.matcher(input);

                // Obtains task index if match succeeds
                if (matcher.matches()) {
                    String num = matcher.group(1);
                    int index = Integer.parseInt(num);

                    // Marks task as done
                    if (this.taskList.isTaskExist(index)) {
                        this.taskList.markTaskAsDone(index);
                        return String.format(
                                        "Nice! I've marked this task as done:\n    %s",
                                        this.taskList.getTask(index));
                    } else {
                        // Warns user of invalid task specified
                        throw new TaskIndexException();
                    }
                }

            } else if (input.matches("unmark (.+)")) {
                // Uses regular expression to capture task index for incompletion
                Pattern pattern = Pattern.compile("unmark (\\d+)");
                Matcher matcher = pattern.matcher(input);

                // Obtains task index if match succeeds
                if (matcher.matches()) {
                    String num = matcher.group(1);
                    int index = Integer.parseInt(num);

                    // Marks task as incomplete
                    if (this.taskList.isTaskExist(index)) {
                        this.taskList.markTaskAsIncomplete(index);
                        return String.format("OK, I've marked this task as not done yet:\n    %s",
                                this.taskList.getTask(index));
                    } else {
                        // Warns user of invalid task specified
                        throw new TaskIndexException();
                    }
                }

            } else if (input.matches("delete (.+)")) {
                // Uses regular expression to capture task index for deletion
                Pattern pattern = Pattern.compile("delete (\\d+)");
                Matcher matcher = pattern.matcher(input);

                // Obtains task index if match succeeds
                if (matcher.matches()) {
                    String num = matcher.group(1);
                    int index = Integer.parseInt(num);

                    // Deletes task
                    if (this.taskList.isTaskExist(index)) {
                        Task deletedTask = this.taskList.removeTask(index);

                        return Ui.deleteTaskResponse(
                                deletedTask.toString(), this.taskList.getTotalNumOfTasks());
                    }
                } else {
                    throw new TaskIndexException();
                }

            } else if (input.matches("todo (.*)")) {
                // Uses regular expression to capture todo name
                Pattern pattern = Pattern.compile("todo (\\S.*)");
                Matcher matcher = pattern.matcher(input);

                // Obtains parameters if match succeeds
                if (matcher.matches()) {
                    String name = matcher.group(1);
                    Todo todo = new Todo(name);
                    this.taskList.addTask(todo);
                    return Ui.addTaskResponse(
                            todo.toString(), this.taskList.getTotalNumOfTasks());

                } else {
                    throw new InvalidInputException("name", "task");
                }

            } else if (input.matches("deadline (.*)")) {
                // Uses regular expression to capture deadline name and /by parameter
                Pattern pattern = Pattern.compile("deadline (\\S.*) /by (\\S.*)");
                Matcher matcher = pattern.matcher(input);

                // Obtains parameters if match succeeds
                if (matcher.matches()) {
                    String name = matcher.group(1);
                    String byParam = matcher.group(2);

                    // Instantiates a Task object and add it to todolist
                    Deadline deadline = new Deadline(name, byParam);
                    this.taskList.addTask(deadline);
                    return Ui.addTaskResponse(deadline.toString(), this.taskList.getTotalNumOfTasks());

                } else {
                    throw new InvalidInputException("name, and due date", "deadline");
                }

            } else if (input.matches("event (.*)")) {
                // Uses regular expression to capture event name, /from and /to parameters
                Pattern pattern = Pattern.compile("event (\\S.*) /from (\\S.*) /to (\\S.*)");
                Matcher matcher = pattern.matcher(input);

                // Obtains parameters if match succeeds
                if (matcher.matches()) {
                    String name = matcher.group(1);
                    String fromParam = matcher.group(2);
                    String toParam = matcher.group(3);

                    // Instantiates a Task object and add it to todolist
                    Event event = new Event(name, fromParam, toParam);
                    this.taskList.addTask(event);
                    return Ui.addTaskResponse(event.toString(), this.taskList.getTotalNumOfTasks());

                } else {
                    throw new InvalidInputException("name, start, and end time", "event");
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
}
