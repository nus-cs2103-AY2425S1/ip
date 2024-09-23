package taskpack;

import meerkatpack.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Used to store a list of Tasks.
 */
public class TaskList {
    private static List<Task> listOfTasks = new ArrayList<>();
    private static final String STORAGE_FILE_PATH = "Meerkat.txt";
    private Storage storage;
    private Ui ui;

    /**
     * Sole constructor.
     */
    public TaskList() {
        this.storage = new Storage();
        this.ui = new Ui();
    }

    /**
     * Creates a String to be written to save file from given TaskList.
     * @param taskList TaskList provided as input with tasks.
     * @return String to be written into save file.
     */
    public String taskListToString(List<Task> taskList) {
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append(task.toParseableString()).append("\n");
        }
        return s.toString();
    }

    /**
     * Creator for todo Task and adds it to the ArrayList of tasks. Also
     * writes to the save file in storage as there is a change in the taskList.
     * Prints UI creation message only if the file is created during the run instead
     * of when reading tasks from save file.
     * @param name The name of the task.
     * @throws IOException If there are any errors in reading or writing the file.
     */
    public String createTodoTask(String name, boolean isMarked) throws IOException {
        Task thisTask = new Todo(name, isMarked);
        if (detectDuplicateTask(thisTask)) {
            return ui.showDuplicateDetectedMessage();
        }
        listOfTasks.add(thisTask);
        storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
        return ui.showTaskCreationMessage(thisTask, this);
    }

    /**
     * Creates an Event Task and adds it to the ArrayList of tasks. Also
     * writes to the save file in storage as there is a change in the taskList.
     * Prints UI creation message only if the file is created during the run instead
     * of when reading tasks from save file.
     * @param name The name of the task.
     * @param start The start date of the task.
     * @param end The end date of the task.
     * @throws IOException If there are any errors in reading or writing the file.
     */
    public String createEventTask(String name, String start, String end, boolean isMarked) throws IOException {
        Task thisTask = new Event(name, start, end, isMarked);
        if (detectDuplicateTask(thisTask)) {
            return ui.showDuplicateDetectedMessage();
        }
        listOfTasks.add(thisTask);
        storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
        return ui.showTaskCreationMessage(thisTask, this);
    }

    /**
     * Creates a Deadline Task and adds it to the ArrayList of tasks. Also
     * writes to the save file in storage as there is a change in the taskList.
     * Prints UI creation message only if the file is created during the run instead
     * of when reading tasks from save file.
     * @param name The name of the task.
     * @param dueDate The deadline of the task.
     * @throws IOException If there are any errors in reading or writing the file.
     */
    public String createDeadlineTask(String name, LocalDateTime dueDate, boolean isMarked) throws IOException {
        Task thisTask = new Deadline(name, dueDate, isMarked);
        if (detectDuplicateTask(thisTask)) {
            return ui.showDuplicateDetectedMessage();
        }
        listOfTasks.add(thisTask);
        storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
        return ui.showTaskCreationMessage(thisTask, this);
    }

    /**
     * Marks task as done when parser receives the mark input.
     * @param taskNum Determines the task number to fetch the right task to mark.
     * @throws IOException When the file cannot be written to.
     */
    public String markTaskAsDone(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            Task thisTask = listOfTasks.get(taskNum - 1);
            thisTask.markAsCompleted();
            storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
            return ui.showTaskMarkedMessage(thisTask);
            // task number is not within range
        } else {
            return ui.showTaskNonMarkableMessage();
        }
    }

    /**
     * Marks task as undone when parser receives the unmark input.
     * @param taskNum Determines the task number to fetch the right task to unmark.
     * @throws IOException When the file cannot be written to.
     */
    public String markTaskAsUndone(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            Task thisTask = listOfTasks.get(taskNum - 1);
            thisTask.markAsIncomplete();
            storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
            return ui.showTaskUnmarkedMessage(thisTask);
            // task number is not within range
        } else {
            return ui.showTaskNonUnmarkableMessage();
        }

    }

    /**
     * Deletes and removes task from taskList when parser receives the delete input.
     * @param taskNum Determines the task number to fetch the right task to delete.
     * @throws IOException When the file cannot be written to.
     */
    public String deleteTask(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            Task thisTask = listOfTasks.get(taskNum - 1);
            listOfTasks.remove(taskNum - 1);
            storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
            return ui.showDeleteMessage(thisTask);
            // task number is not within range
        } else {
            return ui.showUndeletableMessage();
        }
    }

    /**
     * Returns the number of tasks in the taskList.
     * @return The integer size of the taskList.
     */
    public int getSize() {
        return listOfTasks.size();
    }

    /**
     * Provides access to the private taskList
     * @return The taskList.
     */
    public List<Task> getTaskList() {
        return listOfTasks;
    }

    /**
     * Searches for tasks based on keyword name.
     * @param name Keyword given to find matching tasks.
     * @return List of Tasks that match keyword.
     */
    public List<Task> findMatchingTasks(String name) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : listOfTasks) {
            if (task.getName().contains(name)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Detects duplicate todo tasks based on name and marked status.
     * @param newTask The new task to be checked against
     * @return Boolean indicating if duplicate is detected
     */
    public boolean detectDuplicateTask(Task newTask) {
        for (Task iterTask : listOfTasks) {
            if (iterTask.toString().equals(newTask.toString())) {
                return true;
            }
        }
        return false;
    }
}
