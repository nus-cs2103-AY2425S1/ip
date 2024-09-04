package gopher;

import gopher.parser.Parser;
import gopher.storage.TaskManager;
import gopher.task.Task;
import gopher.task.TaskList;

/**
 * Represents the chatbot Gopher.
 */
public class Gopher {

    /**
     * TaskList object used by Gopher to track user tasks
     */
    private static TaskList taskList;

    /**
     * Constructor for Gopher class
     */
    public Gopher() {
        TaskManager.initialize();
        taskList = new TaskList(TaskManager.loadTasks());
    }

    /**
     * Start the main event loop.
     */
    public static String getResponse(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            return "Bye";
        } else if (userInput.equalsIgnoreCase("list")) {
            return taskList.toString();
        } else if (userInput.toLowerCase().startsWith("mark")) {
            int taskNumber = Parser.parseMarkCommand(userInput);
            taskList.markAsDone(taskNumber);
            return "Task marked as done";
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            int taskNumber = Parser.parseUnMarkCommand(userInput);
            taskList.markAsUndone(taskNumber);
            return "Task marked as not done";
        } else if (userInput.toLowerCase().startsWith("delete")) {
            int taskNumber = Parser.parseDeleteCommand(userInput);
            taskList.delete(taskNumber);
            return "Task delete";
        } else if (userInput.toLowerCase().startsWith("find")) {
            String keyword = Parser.parseFindCommand(userInput);
            TaskList matchedTasks = taskList.find(keyword);
            return matchedTasks.toString();
        } else {
            Task task = Parser.parseCreateTaskCommand(userInput);
            if (task != null) {
                taskList.add(task);
            }
            return "Task successfully added";
        }
    }
}

