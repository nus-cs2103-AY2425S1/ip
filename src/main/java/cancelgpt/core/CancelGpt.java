package cancelgpt.core;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import cancelgpt.exception.command.UnknownInput;
import cancelgpt.exception.task.DeleteTaskInputException;
import cancelgpt.exception.task.FindTaskInputException;
import cancelgpt.exception.task.InvalidTaskException;
import cancelgpt.exception.task.MarkTaskInputException;
import cancelgpt.exception.task.TaskDoesNotExist;
import cancelgpt.exception.task.UnmarkTaskInputException;
import cancelgpt.task.Task;
import javafx.application.Application;

/**
 * Represents the chatbot.
 */
public class CancelGpt {
    private final String chatbotName;
    private final TasksList tasksList;
    private final CommandParser commandParser;
    private final TasksStorage tasksStorage;

    /**
     * Initialises the chatbot with given storage directory path.
     *
     * @param storageDirPath the path to the directory that stores tasks data
     */
    public CancelGpt(Path storageDirPath) throws IOException {
        this.chatbotName = "cancelgpt";
        this.tasksList = new TasksList();
        try {
            this.tasksStorage = new TasksStorage(this, storageDirPath);
        } catch (IOException e) {
            throw new IOException("Unable to use TASKS STORAGE. Exiting program");
        }
        this.commandParser = new CommandParser(this);
    }

    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }

    /**
     * Gets the name of the chatbot.
     *
     * @return the chatbot name
     */
    public String getName() {
        return this.chatbotName;
    }

    /**
     * Returns greeting message
     *
     * @return greeting message
     */
    public String greet() {
        return "Hello! I am " + chatbotName + "\n"
                + "What can I do for you?";
    }

    /**
     * Parses and handles the command given, and returns the result message
     *
     * @param command command entered by user
     * @return result message of the executed command, if successful
     */
    public String handleCommand(String command) throws MarkTaskInputException, UnmarkTaskInputException,
            InvalidTaskException, TaskDoesNotExist, UnknownInput, DeleteTaskInputException,
            FindTaskInputException {
        return this.commandParser.parseAndHandle(command);
    }

    /**
     * Deletes a task identified by its task number in the chatbot's task list,
     * and returns the result message.
     *
     * @param taskNumber the task number in the chatbot's task list
     * @return the result message of successful delete task
     * @throws TaskDoesNotExist if the task number does not exist in the task list
     */
    public String deleteTask(int taskNumber) throws TaskDoesNotExist {
        return this.tasksList.deleteTask(taskNumber);
    }

    /**
     * Handles adding the task to the chatbot's task list.
     * Returns message of adding task to console, and calls addToTaskList
     *
     * @param task a Task object to be added
     * @return result message after successfully adding task
     */
    public String handleAddingTask(Task task) {
        return "Got it. I've added this task: "
                + " " + this.addToTaskList(task)
                + "Now you have " + this.tasksList.getSize() + " tasks in the list.";
    }

    /**
     * Marks the task identified by its task number in the chatbot's task list.
     * Does nothing if the task is already marked.
     * Returns the task string representation after marking the task.
     *
     * @param taskNumber the task number in the chatbot's task list
     * @return task string representation after it is marked
     * @throws TaskDoesNotExist if the task number does not exist in the task list
     */
    public String markTask(int taskNumber) throws TaskDoesNotExist {
        return this.tasksList.markTask(taskNumber);
    }

    /**
     * Unmarks the task identified by its task number in the chatbot's task list.
     * Does nothing if the task is already unmarked.
     * Returns the task string representation after unmarking the task.
     *
     * @param taskNumber the task number in the chatbot's task list
     * @return task string representation after it is unmarked
     * @throws TaskDoesNotExist if the task number does not exist in the task list
     */
    public String unmarkTask(int taskNumber) throws TaskDoesNotExist {
        return this.tasksList.unmarkTask(taskNumber);
    }

    /**
     * Adds the task to the chatbot's task list,
     * and returns the result message after adding the task to the task list.
     *
     * @param task a Task object to be added
     * @return the result message after adding the task to the task list
     */
    public String addToTaskList(Task task) {
        return this.tasksList.addToTaskList(task);
    }

    /**
     * Returns the tasks display string in the chatbot's task list to console.
     *
     * @return string of tasks separated by '\n'
     */
    public String displayTasksList() {
        return this.tasksList.displayTasksList();
    }

    /**
     * Saves all the tasks to the chatbot's storage in the chatbot's given
     * by its storage path directory, so that it can be
     * retrieved in subsequent sessions of using the chatbot with the same
     * storage path directory given.
     *
     * @throws IOException if the tasks cannot be saved
     */
    public void saveTasks() throws IOException {
        try {
            this.tasksStorage.saveTasks();
        } catch (IOException e) {
            throw new IOException("Unable to save tasks");
        }
    }

    /**
     * Displays a list of tasks with the keyword given in their description.
     *
     * @param keyword the keyword in the description of tasks to find
     * @return string of tasks that matches the keyword, separated by '\n'
     */
    public String findTasks(String keyword) {
        StringBuilder findTasksResponse = new StringBuilder();
        findTasksResponse.append("Here are the matching tasks in your list:\n");

        List<Task> tasksFound = this.tasksList.findTasksByDescriptionKeyword(keyword);
        for (int i = 0; i < tasksFound.size(); i++) {
            findTasksResponse.append(i + 1).append(".").append(tasksFound.get(i)).append("\n");
        }
        return findTasksResponse.toString();
    }

    /**
     * Returns the chatbot's task list
     *
     * @return the chatbot's task list
     */
    public List<Task> getTasks() {
        return this.tasksList.getTasksList();
    }
}
