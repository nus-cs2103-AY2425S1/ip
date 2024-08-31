package rob;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
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

        if (Objects.equals(command, "bye")) {
            return ui.getExit();
        } else if (Objects.equals(command, "list")) {
            return ui.showList(tasks);
        } else if (Objects.equals(command, "mark")) {
            try {
                int taskNum = findTaskNum(input);
                String markedStr = ui.mark(tasks, taskNum);
                tasks.getTask(taskNum - 1).markAsDone();
                storage.saveTasks(tasks);
                return markedStr;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        } else if (Objects.equals(command, "unmark")) {
            try {
                int taskNum = findTaskNum(input);
                String unmarkedStr = ui.unmark(tasks, taskNum);
                tasks.getTask(taskNum - 1).unmark();
                storage.saveTasks(tasks);
                return unmarkedStr;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        } else if (Objects.equals(command, "delete")) {
            try {
                int taskNum = findTaskNum(input);
                String delStr = ui.delete(tasks, taskNum);
                tasks.removeTask(taskNum - 1);
                storage.saveTasks(tasks);
                return delStr;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        } else if (Objects.equals(command, "find")) {
            try {
                String keyword = parser.getFind();
                List<Task> filteredList = tasks.searchTasks(keyword);
                TaskList filteredTasks = new TaskList(filteredList);
                return ui.showList(filteredTasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        } else {
            try {
                if (Objects.equals(command, "deadline")) {
                    String desc = parser.getDesc();
                    String day = parser.getDay();
                    tasks.getTasks().add(new Deadline(desc, day));
                    storage.saveTasks(tasks);
                } else if (Objects.equals(command, "event")) {
                    String desc = parser.getDesc();
                    String from = parser.getFrom();
                    String to = parser.getTo();
                    tasks.getTasks().add(new Event(desc, from, to));
                    storage.saveTasks(tasks);
                } else if (Objects.equals(command, "todo")) {
                    String desc = parser.getDesc();
                    tasks.getTasks().add(new Todo(desc));
                    storage.saveTasks(tasks);
                } else {
                    throw new DukeException("I'm sorry... I don't seem to understand.");
                }
                return ui.printText(tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        }
    }
}

