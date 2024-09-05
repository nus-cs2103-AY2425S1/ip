package gopher;

import java.time.format.DateTimeParseException;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.MissingTokenException;
import gopher.exception.UnknownCommandException;
import gopher.parser.Parser;
import gopher.storage.TaskManager;
import gopher.task.Task;
import gopher.task.TaskList;
import gopher.ui.UI;
import javafx.application.Platform;

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
        try {
            if (userInput.equalsIgnoreCase("bye")) {
                Platform.exit();
                return UI.getExitMessage();
            } else if (userInput.equalsIgnoreCase("list")) {
                return UI.getTaskListMessage(taskList);
            } else if (userInput.toLowerCase().startsWith("mark")) {
                int[] taskNumbers = Parser.parseMarkCommand(userInput);
                taskList.markAsDone(taskNumbers);
                StringBuilder message = new StringBuilder();
                for (int taskNumber: taskNumbers) {
                    message.append(UI.getMarkAsDoneMessage(taskList.getTask(taskNumber)));
                }
                return message.toString();
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                int[] taskNumbers = Parser.parseUnmarkCommand(userInput);
                taskList.markAsUndone(taskNumbers);
                StringBuilder message = new StringBuilder();
                for (int taskNumber: taskNumbers) {
                    message.append(UI.getMarkAsUndoneMessage(taskList.getTask(taskNumber)));
                }
                return message.toString();
            } else if (userInput.toLowerCase().startsWith("delete")) {
                int[] taskNumbers = Parser.parseDeleteCommand(userInput);
                StringBuilder message = new StringBuilder();
                for (int taskNumber: taskNumbers) {
                    message.append(UI.getDeleteTaskMessage(taskList.getTask(taskNumber)));
                }
                taskList.delete(taskNumbers);
                return message.toString();
            } else if (userInput.toLowerCase().startsWith("find")) {
                String keyword = Parser.parseFindCommand(userInput);
                TaskList matchedTasks = taskList.find(keyword);
                return UI.getMatchedTasksMessage(matchedTasks);
            } else {
                Task task = Parser.parseCreateTaskCommand(userInput);
                if (task != null) {
                    taskList.add(task);
                }
                return UI.getAddTaskMessage(task);
            }
        } catch (UnknownCommandException e) {
            return UI.getUnknownCommandWarning(e);
        } catch (EmptyTaskDescriptionException e) {
            return "Task Description cannot be empty";
        } catch (MissingTokenException e) {
            return "Your task input has missing token";
        } catch (DateTimeParseException e) {
            return UI.getInvalidDateWarning();
        }
    }
}
