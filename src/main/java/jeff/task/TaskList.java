package jeff.task;

import jeff.exception.FileCorruptException;
import jeff.exception.JeffException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public TaskList(Scanner scanner) throws JeffException {
        try {
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
                } else {
                    throw new FileCorruptException();
                }
            }

        } catch (FileCorruptException e) {
            throw new JeffException(e.toString());
        }
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(Task task) {
        this.tasks.remove(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public List<Task> filterByDate(LocalDate date) {
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

    public List<String> toFileStrings() {
        return this.tasks.stream().map(Task::toFileString).toList();
    }

    /**
     * Gets the target task from the task list based on the user input
     *
     * @param input String that the user types into the command line interface
     * @param prefix the action that the user wants to take
     * @return the target task from the task list
     * @throws JeffException if the input is in the wrong format or if the task number specified by the user does not
     *                       exist
     */
    public Task getTask(String input, String prefix) throws JeffException {
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
