package rob;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Rob class represents a chatbot that helps users manage tasks
 */
public class Rob {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Parser parser;

    /**
     * Initializes a new instance of the Rob chatbot.
     * Initializes the user interface, storage, and task list components
     * of the Rob chatbot.
     *
     * @param filePath The file path where tasks are stored.
     */

    public Rob(String filePath) {
        assert filePath != null && !filePath.isEmpty();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks(tasks));
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Finds and returns the last integer found in the input string.
     *
     * @param input The input string that contains the task number.
     * @return The task number extracted from the input string.
     * @throws RobException If no number is found in the input string or if the task number is out of range.
     */
    public int findTaskNum(String input) throws RobException {
        assert input != null && !input.isEmpty();
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String lastNum = null;

        while (matcher.find()) {
            lastNum = matcher.group();
        }

        if (lastNum == null) {
            throw new RobException("Invalid task number...");
        } else {
            int taskNum = Integer.parseInt(lastNum);
            if (taskNum < 1 || taskNum > tasks.len()) {
                throw new RobException("Invalid task number");
            } else {
                return taskNum;
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @return The string containing the chatbot's response.
     */
    public String getResponse(String input) throws RobException {
        parser = new Parser(input);
        parser.checkString();
        String command = parser.getCommand();

        try {
            switch (command) {
            case "bye":
                return ui.getExit();
            case "list":
                return ui.showList(tasks);
            case "mark":
                return markTask(input);
            case "unmark":
                return unmarkTask(input);
            case "delete":
                return deleteTask(input);
            case "find":
                return findTask(input);
            case "deadline":
            case "event":
            case "todo":
                return addTask(input);
            case "force":
                return forceTask(input);
            default:
                throw new RobException("I'm sorry... I don't seem to understand.");
            }
        } catch (RobException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Handles adding a task based on the command provided.
     *
     * @param command The type of task to add ("deadline", "event", or "todo").
     * @param parser  The parser used to extract task details such as description, dates, and times.
     * @throws RobException If an unknown command is provided or there is an error in adding the task.
     */
    private void handleAddTask(String command, Parser parser) throws RobException {
        String desc = parser.getDesc();
        switch (command) {
        case "deadline":
            String day = parser.getDay();
            tasks.getTasks().add(new Deadline(desc, day));
            break;
        case "event":
            String from = parser.getFrom();
            String to = parser.getTo();
            tasks.getTasks().add(new Event(desc, from, to));
            break;
        case "todo":
            tasks.getTasks().add(new Todo(desc));
            break;
        default:
            throw new RobException("Unknown task type.");
        }
        storage.saveTasks(tasks);
    }

    /**
     * Marks a task as done by the input given by user.
     *
     * @param input The user's input containing the command and task number to mark as done.
     * @return A string confirming the task has been marked as done.
     * @throws RobException If the task number is invalid.
     */
    private String markTask(String input) throws RobException {
        int markTaskNum = findTaskNum(input);
        assert markTaskNum > 0 && markTaskNum <= tasks.len();
        String markedStr = ui.mark(tasks, markTaskNum);
        tasks.getTask(markTaskNum - 1).markAsDone();
        storage.saveTasks(tasks);
        return markedStr;
    }

    /**
     * Unmarkes a task by the input given by user.
     *
     * @param input The user's input containing the command and task number to unmark.
     * @return A string confirming the task has been unmarked.
     * @throws RobException If the task number is invalid.
     */
    private String unmarkTask(String input) throws RobException {
        int unmarkTaskNum = findTaskNum(input);
        assert unmarkTaskNum > 0 && unmarkTaskNum <= tasks.len();
        String unmarkedStr = ui.unmark(tasks, unmarkTaskNum);
        tasks.getTask(unmarkTaskNum - 1).unmark();
        storage.saveTasks(tasks);
        return unmarkedStr;
    }

    /**
     * Deletes a task by the input given by user.
     *
     * @param input The user's input containing the command and task number to be deleted.
     * @return A string confirming the task has been deleted.
     * @throws RobException If the task number is invalid.
     */
    private String deleteTask(String input) throws RobException {
        int deleteTaskNum = findTaskNum(input);
        assert deleteTaskNum > 0 && deleteTaskNum <= tasks.len();
        String delStr = ui.delete(tasks, deleteTaskNum);
        tasks.removeTask(deleteTaskNum - 1);
        storage.saveTasks(tasks);
        return delStr;
    }

    /**
     * Finds tasks by the input given by user.
     *
     * @param input The user's input containing the command and task number to be deleted.
     * @return A string of the list of tasks found.
     * @throws RobException If the task number is invalid.
     */
    private String findTask(String input) throws RobException {
        String keyword = parser.getFind();
        List<Task> filteredList = tasks.searchTasks(keyword);
        TaskList filteredTasks = new TaskList(filteredList);
        return ui.showList(filteredTasks);
    }

    /**
     * Adds a new task to the task list by the input given by user.
     *
     * @param input The user's input containing the usual task commands.
     * @return A string confirming the task has been added.
     * @throws RobException If the task number is invalid.
     */
    private String addTask(String input) throws RobException {
        String command = parser.getCommand();
        String desc = parser.getDesc();
        if (!tasks.searchExactTasks(desc).isEmpty()) {
            return ui.showDuplicate();
        }
        handleAddTask(command, parser);
        return ui.printText(tasks);
    }

    /**
     * Forces a duplicate task to be added by the input given by user.
     *
     * @param input The user's input containing the usual task command prepended by the force command.
     * @return A string confirming the task has been added.
     * @throws RobException If the task number is invalid.
     */
    private String forceTask(String input) throws RobException {
        parser = new Parser(input.split(" ", 2)[1]);
        parser.checkString();
        String forcedCommand = parser.getCommand();
        handleAddTask(forcedCommand, parser);
        return ui.printText(tasks);
    }
}

