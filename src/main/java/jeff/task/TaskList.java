package jeff.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jeff.exception.FileCorruptException;
import jeff.exception.JeffException;
import jeff.parser.Parser;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for the TaskList Class.
     * Creates an empty task list.
     */
    public TaskList() {}

    /**
     * Constructor for the TaskList Class.
     * Adds all the tasks from the given list of tasks to the task list.
     *
     * @param tasks Given list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

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

                // Split the task parts into their respective parts
                String taskType = taskParts[0];
                boolean isDone = taskParts[1].equals("1");
                String taskDescription = taskParts[2];

                // Initialise the current task
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
                            currentTask = new DeadlineTask(
                                    taskDescription,
                                    Parser.getLocalDateTime(taskParts[3]),
                                    isDone
                            );
                        }
                        break;

                    case "E":
                        // Event Task
                        if (taskParts.length >= 5) {
                            currentTask = new EventTask(
                                    taskDescription,
                                    Parser.getLocalDateTime(taskParts[3]),
                                    Parser.getLocalDateTime(taskParts[4]),
                                    isDone
                            );
                        }
                        break;

                    default:
                        break;
                    }
                } catch (DateTimeParseException e) {
                    throw new FileCorruptException();
                }


                if (currentTask == null) {
                    throw new FileCorruptException();
                }

                this.addTask(currentTask);
                assert this.tasks.contains(currentTask) : "Task list should contain the new task after adding it";
            }
            assert this.tasks != null : "Task list should not be null";

        } catch (FileCorruptException e) {
            throw new JeffException("Sorry! The task file is corrupted!");
        }
    }

    /**
     * Returns the task at the given index in the task list.
     *
     * @param index Index of the target task.
     * @return Task at the given index.
     */
    public Task getTaskByIndex(int index) {
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
    public void addTask(Task task) {
        assert task != null : "Input task should not be null";
        this.tasks.add(task);
    }

    /**
     * Removes the given task from the task list.
     *
     * @param task Task to be removed from the task list.
     */
    public void deleteTask(Task task) {
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
     * Returns a filtered task list whose tasks only takes place on the given date.
     *
     * @param date Given date.
     * @return A filtered task list.
     */
    public TaskList filterByDate(LocalDate date) {
        assert date != null : "Input date should not be null";
        return new TaskList(
                this.tasks.stream().filter(task -> task.isOnThisDate(date)).toList()
        );
    }

    /**
     * Returns a filtered task list filtered by the given name.
     *
     * @param name Given name to filter.
     * @return A filtered task list.
     */
    public TaskList filterByName(String name) {
        return new TaskList(
                this.tasks.stream().filter(task -> task.doesDescriptionContain(name)).toList()
        );
    }

    /**
     * Returns a list of the file string representation of the tasks.
     *
     * @return A list of the file string representation of the tasks.
     */
    public List<String> toListOfFileStrings() {
        return this.tasks.stream().map(Task::toFileString).toList();
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return String representation of the given task list.
     */
    @Override
    public String toString() {
        // Initialise a StringBuilder
        StringBuilder listString = new StringBuilder();

        // Loop through the inputList and add it to the StringBuilder
        for (int i = 0; i < this.size(); i++) {
            listString.append(i + 1).append(".").append(this.getTaskByIndex(i).toString());

            // Only add a new line when it is not the last task in the taskList
            if (i != this.size() - 1) {
                listString.append("\n");
            }

        }

        return listString.toString();
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
    public Task getTaskByCommand(String input, String prefix) throws JeffException {
        assert !prefix.isEmpty() : "Input prefix should not be empty";

        // Check if input is valid
        if (!input.matches(prefix + "\\d+")) {
            throw new JeffException(
                    "The format is wrong! It should be \"" + prefix + "xx\", where xx is a number."
            );
        }

        // Get the task index
        String taskNumberString = input.substring(prefix.length());
        int taskIndex = Integer.parseInt(taskNumberString) - 1;

        // Check if taskIndex exists in taskList
        if (taskIndex < 0 || taskIndex >= this.size()) {
            throw new JeffException("This task number does not exist!");
        }

        return this.getTaskByIndex(taskIndex);

    }
}
