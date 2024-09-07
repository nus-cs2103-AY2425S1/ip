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
     * @throws DukeException If no number is found in the input string or if the task number is out of range.
     */
    public int findTaskNum(String input) throws DukeException {
        assert input != null && !input.isEmpty();
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String lastNum = null;

        while (matcher.find()) {
            lastNum = matcher.group();
        }

        if (lastNum == null) {
            throw new DukeException("Invalid task number...");
        } else {
            int taskNum = Integer.parseInt(lastNum);
            if (taskNum < 1 || taskNum > tasks.len()) {
                throw new DukeException("Invalid task number");
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
    public String getResponse(String input) {
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
                int markTaskNum = findTaskNum(input);
                assert markTaskNum > 0 && markTaskNum <= tasks.len();
                String markedStr = ui.mark(tasks, markTaskNum);
                tasks.getTask(markTaskNum - 1).markAsDone();
                storage.saveTasks(tasks);
                return markedStr;
            case "unmark":
                int unmarkTaskNum = findTaskNum(input);
                assert unmarkTaskNum > 0 && unmarkTaskNum <= tasks.len();
                String unmarkedStr = ui.unmark(tasks, unmarkTaskNum);
                tasks.getTask(unmarkTaskNum - 1).unmark();
                storage.saveTasks(tasks);
                return unmarkedStr;
            case "delete":
                int deleteTaskNum = findTaskNum(input);
                assert deleteTaskNum > 0 && deleteTaskNum <= tasks.len();
                String delStr = ui.delete(tasks, deleteTaskNum);
                tasks.removeTask(deleteTaskNum - 1);
                storage.saveTasks(tasks);
                return delStr;
            case "find":
                String keyword = parser.getFind();
                List<Task> filteredList = tasks.searchTasks(keyword);
                TaskList filteredTasks = new TaskList(filteredList);
                return ui.showList(filteredTasks);
            case "deadline":
            case "event":
            case "todo":
                handleAddTask(command, parser);
                return ui.printText(tasks);
            default:
                throw new DukeException("I'm sorry... I don't seem to understand.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    private void handleAddTask(String command, Parser parser) throws DukeException {
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
            throw new DukeException("Unknown task type.");
        }
        storage.saveTasks(tasks);
    }
}

