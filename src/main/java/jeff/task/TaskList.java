package jeff.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jeff.exception.FileCorruptException;
import jeff.exception.JeffException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    /**
     * Constructor for the TaskList Class.
     * Takes in a scanner, categorises the strings into their task types and adds the tasks to the task list.
     *
     * @param scanner Scanner with task strings.
     * @throws JeffException if the file is corrupt.
     */
    public TaskList(Scanner scanner) throws JeffException {
        assert scanner != null : "Scanner should not be null";
        try {
            // Read every line in the scanner
            while (scanner.hasNext()) {
                // Get the task information
                String[] taskParts = scanner.nextLine().split(" \\| ");

                // Check if the data file is corrupted
                if (taskParts.length < 3) {
                    throw new FileCorruptException();
                }

                String taskType = taskParts[0];
                boolean isDone = taskParts[1].equals("1");
                String taskDescription = taskParts[2];
                Task currentTask = null;

                // Categorise and initialise the task based on its task type
                try {
                    switch (taskType) {
                    case "T":
                        // To Do Task
                        currentTask = new ToDoTask(taskDescription, isDone);
                        break;

                    case "D":
                        // Deadline Task
                        if (taskParts.length >= 4) {
                            currentTask = new DeadlineTask(taskDescription,
                                    LocalDateTime.parse(taskParts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                    isDone);
                        }
                        break;

                    case "E":
                        // Event Task
                        if (taskParts.length >= 5) {
                            currentTask = new EventTask(taskDescription,
                                    LocalDateTime.parse(taskParts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                    LocalDateTime.parse(taskParts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                                    isDone);
                        }
                        break;

                    default:
                        break;
                    }
                } catch (DateTimeParseException e) {
                    throw new FileCorruptException();
                }

                // Add task to task list if it exists
                if (currentTask != null) {
                    this.tasks.add(currentTask);
                    assert this.tasks.contains(currentTask) : "Task list should contain the new task after adding it";
                } else {
                    throw new FileCorruptException();
                }
            }
            assert this.tasks != null : "Task list should not be null";

        } catch (FileCorruptException e) {
            throw new JeffException(e.toString());
        }
    }

    /**
     * Returns the task at the given index in the task list.
     *
     * @param index Index of the target task.
     * @return Task at the given index.
     */
    public Task get(int index) {
        assert index >= 0 && index < this.tasks.size() : "Task index should not be out of bounds";
        return this.tasks.get(index);
    }

    /**
     * Checks if the given task is in the task list.
     * 
     * @param task Given task. 
     * @return true if the given task is in the task list, and false otherwise.
     */
    public boolean contains(Task task) {
        return this.tasks.contains(task);
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task Task to be added to the task list.
     */
    public void add(Task task) {
        assert task != null : "Input task should not be null";
        this.tasks.add(task);
    }

    /**
     * Removes the given task from the task list.
     *
     * @param task Task to be removed from the task list.
     */
    public void remove(Task task) {
        assert task != null : "Input task should not be null";
        this.tasks.remove(task);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Checks if the task list is empty or not.
     *
     * @return true if the task list is empty and false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns a list of tasks that only takes place on the given date.
     *
     * @param date Given date.
     * @return A list of filtered tasks.
     */
    public List<Task> filterByDate(LocalDate date) {
        assert date != null : "Input date should not be null";
        return this.tasks.stream().filter(task -> task.isOnThisDate(date)).toList();
    }

    /**
     * Returns a list of tasks filtered by the given name.
     *
     * @param name Given name to filter.
     * @return List of filtered tasks.
     */
    public List<Task> filterByName(String name) {
        return this.tasks.stream().filter(task -> task.contains(name)).toList();
    }

    /**
     * Returns a list of the file string representation of the tasks.
     *
     * @return A list of the file string representation of the tasks.
     */
    public List<String> toFileStrings() {
        return this.tasks.stream().map(Task::toFileString).toList();
    }

    /**
     * Returns the target task from the task list based on the user input.
     *
     * @param input User's input.
     * @param prefix Action that the user wants to take.
     * @return The target task from the task list.
     * @throws JeffException if the input is in the wrong format or if the task number specified by the user does not
     *                       exist.
     */
    public Task getTask(String input, String prefix) throws JeffException {
        assert !prefix.isEmpty() : "Input prefix should not be empty";
        
        // Check if input is valid
        if (!input.matches(prefix + "\\d+")) {
            throw new JeffException("The format is wrong! It should be \"" + prefix + "xx\", where xx is a number.");
        }

        // Get the taskIndex
        int taskIndex = Integer.parseInt(input.substring(prefix.length())) - 1;

        // Check if taskIndex exists in taskList
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new JeffException("This task number does not exist!");
        }

        // Get the task from taskList and return it
        return this.tasks.get(taskIndex);

    }
}
