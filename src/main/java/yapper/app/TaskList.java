package yapper.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Manages a list of tasks, including adding, deleting, marking, and listing tasks.
 * Handles writing the task list to a file.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private String filePath;

    /**
     * Constructs a TaskList with the specified list of tasks and file path.
     *
     * @param taskList the initial list of tasks
     * @param filePath the path to the file where tasks will be saved
     */
    public TaskList(ArrayList<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    /**
     * Adds a new task to the task list and updates the file.
     *
     * @param task the task to be added
     * @return     a string reflecting the changes
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        String[] texts = {
            "Task has been added:",
            "  " + task,
            "A total of " + getSize() + " " + pluralise() + " are on the list."
        };
        writeToFile();
        return Ui.wrapText(texts);
    }

    /**
     * Returns the appropriate plural form of the word "task" based on the number of tasks in the list.
     *
     * @return a string representing "task" or "tasks"
     */
    public String pluralise() {
        String taskMessage = "task";
        if (getSize() != 1) {
            taskMessage += "s";
        }
        return taskMessage;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index the index of the task to retrieve (uses 1-indexed numbering)
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Lists all tasks currently in the task list, displaying their index and description.
     * @return a list of all tasks
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append(Ui.showLine());
        String header = String.format("You currently have %d %s\n", getSize(), pluralise());
        sb.append(header);
        for (int i = 1; i <= getSize(); i++) {
            String temp = i + "." + getTask(i - 1) + "\n";
            sb.append(temp);
        }
        return sb.append(Ui.showLine()).toString();
    }

    /**
     * Deletes a task from the list based on the specified task number and updates the file.
     *
     * @param taskNumber the number of the task to be deleted
     * @return           a string reflecting the changes
     */
    public String deleteTask(String taskNumber) {
        Task task = null;
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            task = this.taskList.get(taskIndex - 1);
            this.taskList.remove(taskIndex - 1);
            writeToFile();
        } catch (NumberFormatException e) {
            return Ui.wrapText("That was NOT a valid number.");
        } catch (IndexOutOfBoundsException e) {
            return Ui.wrapText("That task is not here, sorry! :(");
        }
        String[] texts = {
            "The following task has been removed form the list:",
            "  " + task,
            "A total of " + getSize() + " " + pluralise() + " are still left."
        };
        return Ui.wrapText(texts);
    }

    /**
     * Marks or unmarks a task as completed based on the command and task number, and updates the file.
     *
     * @param command     the command specifying whether to mark or unmark the task
     * @param taskNumber  the number of the task to be marked or unmarked
     * @return            a string reflecting the changes
     */
    public String markTask(String command, String taskNumber) {
        Task task = null;
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            task = getTask(taskIndex - 1);
        } catch (NumberFormatException e) {
            return Ui.wrapText("That was NOT a valid number.");
        } catch (IndexOutOfBoundsException e) {
            return Ui.wrapText("Oopsie! Couldn't find that one! :)");
        }
        String message = "";
        if (command.equals("mark")) {
            message = "This task has been marked as completed:";
            task.mark();
        } else {
            message = "This task has been reopened:";
            task.unmark();
        }
        writeToFile();
        String[] texts = {
            message,
            " " + task,
        };
        return Ui.wrapText(texts);
    }

    /**
     * Finds and prints out the tasks that contain a certain keyword
     *
     * @param keyword     the keyword used to filter out the relevant tasks
     * @return            the strings representing each task that contains the provided keyword
     */
    public String findTasks(String keyword) {
        ArrayList<Task> foundMatches = this.taskList.stream()
            .filter(task -> task.toString().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toCollection(ArrayList::new));

        if (foundMatches.isEmpty()) {
            return Ui.wrapText("Sorry, I couldn't find any tasks that had this keyword! :(");
        }
        return new TaskList(foundMatches, "").listTasks();
    }

    /**
     * Writes the current list of tasks to the file.
     * Each task is written on a new line.
     */
    public void writeToFile() {
        if (filePath.isEmpty()) {
            return;
        }
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Task t : this.taskList) {
                fileWriter.write(t.getDesc() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
