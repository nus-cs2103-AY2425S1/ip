package meerkatpack;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private Storage storage;
    private Ui ui;
    private static List<Task> listOfTasks = new ArrayList<>();
    private final String STORAGE_FILE_PATH = "Meerkat.txt";

    public TaskList() {
        this.storage = new Storage();
        this.ui = new Ui();
    }

    public String taskListToString(List<Task> taskList) {
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append(task.toParseableString()).append("\n");
        }
        return s.toString();
    }

    /**
     * Creates a todo Task and adds it to the ArrayList of tasks. Also
     * writes to the save file in storage as there is a change in the taskList.
     * Prints UI creation message only if the file is created during the run instead
     * of when reading tasks from save file.
     * @param name The name of the task.
     * @param fromSave Check if the task is created from an input or through parsing file.
     * @throws IOException If there are any errors in reading or writing the file.
     */
    public void createTodoTask(String name, boolean fromSave) throws IOException {
        Task thisTask = new Todo(name);
        listOfTasks.add(thisTask);
        storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
        if (!fromSave) {
            ui.printTaskCreationMessage(thisTask, this);
        }
    }

    /**
     * Creates an Event Task and adds it to the ArrayList of tasks. Also
     * writes to the save file in storage as there is a change in the taskList.
     * Prints UI creation message only if the file is created during the run instead
     * of when reading tasks from save file.
     * @param name The name of the task.
     * @param fromSave Check if the task is created from an input or through parsing file.
     * @throws IOException If there are any errors in reading or writing the file.
     */
    public void createEventTask(String name, String start, String end, boolean fromSave) throws IOException {
        Task thisTask = new Event(name, start, end);
        listOfTasks.add(thisTask);
        storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
        if (!fromSave) {
            ui.printTaskCreationMessage(thisTask, this);
        }
    }

    /**
     * Creates a Deadline Task and adds it to the ArrayList of tasks. Also
     * writes to the save file in storage as there is a change in the taskList.
     * Prints UI creation message only if the file is created during the run instead
     * of when reading tasks from save file.
     * @param name The name of the task.
     * @param fromSave Check if the task is created from an input or through parsing file.
     * @throws IOException If there are any errors in reading or writing the file.
     */
    public void createDeadlineTask(String name, LocalDateTime duedate, boolean fromSave) throws IOException {
        Task thisTask = new Deadline(name, duedate);
        listOfTasks.add(thisTask);
        storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
        if (!fromSave) {
            ui.printTaskCreationMessage(thisTask, this);
        }
    }

    /**
     * Marks task as done when parser receives the mark input.
     * @param taskNum Determines the task number to fetch the right task to mark.
     * @throws IOException When the file cannot be written to.
     */
    public void markTaskAsDone(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            Task thisTask = listOfTasks.get(taskNum - 1);
            thisTask.markAsCompleted();
            ui.printTaskMarkedMessage(thisTask);
            // task number is not within range
        } else {
            ui.printTaskNonMarkableMessage();
        }
        storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
    }

    /**
     * Marks task as undone when parser receives the unmark input.
     * @param taskNum Determines the task number to fetch the right task to unmark.
     * @throws IOException When the file cannot be written to.
     */
    public void markTaskAsUndone(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            Task thisTask = listOfTasks.get(taskNum - 1);
            thisTask.markAsIncomplete();
            ui.printTaskUnmarkedMessage(thisTask);
            // task number is not within range
        } else {
            ui.printTaskNonUnmarkableMessage();
        }
        storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
    }

    /**
     * Deletes and removes task from taskList when parser receives the delete input.
     * @param taskNum Determines the task number to fetch the right task to delete.
     * @throws IOException When the file cannot be written to.
     */
    public void deleteTask(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            Task thisTask = listOfTasks.get(taskNum - 1);
            listOfTasks.remove(taskNum - 1);
            ui.printDeleteMessage(thisTask);
            // task number is not within range
        } else {
            ui.printUndeletableMessage();
        }
        storage.writeToFile(STORAGE_FILE_PATH, taskListToString(listOfTasks));
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
     * Sets the most recently created task to have either marked or unmarked status.
     * Used during creation of tasks from reading save file.
     * @param bool Determines whether to mark task as completed
     */
    public void setMostRecentTaskCompletionStatus(boolean bool) {
        int size = getSize();
        if (bool) {
            listOfTasks.get(size - 1).markAsCompleted();
        } else {
            listOfTasks.get(size - 1).markAsIncomplete();
        }
    }

    public List<Task> findMatchingTasks(String name) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : listOfTasks) {
            if (task.getName().contains(name)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
