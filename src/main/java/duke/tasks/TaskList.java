package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;

/**
 * The TaskList class manages a list of tasks.
 * It follows the Singleton pattern to ensure only one instance exists.
 */
public class TaskList {
    private static TaskList taskList;
    private final List<Task> taskStore;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.taskStore = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of the TaskList class.
     *
     * @return The TaskList instance
     */
    public static TaskList getInstance() {
        if (taskList == null) {
            taskList = new TaskList();
        }
        return taskList;
    }

    /**
     * Loads a task from a string representation and adds it to the task list.
     *
     * @param data String representation of the task
     */
    public void loadData(String data) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String[] line = data.split("\\s*\\|\\s*");
            String taskType = line[0];
            Task task = switch (taskType) {
                case "T" -> new Todo(line[2]);
                case "D" -> new Deadline(line[2], LocalDateTime.parse(line[3].trim(), dtf));
                case "E" ->
                        new Event(line[2], LocalDateTime.parse(line[3].trim(), dtf), LocalDateTime.parse(line[4].trim(), dtf));
                default -> throw new DukeException("Invalid Task type provided.");
            };
            if (line[1].equals("1")) task.markAsDoneNonVerbose();
            this.taskStore.add(task);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints all tasks in the list.
     */
    public void printTaskList() {
        if (taskStore.isEmpty()) {
            System.out.println("List is currently empty.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskStore.size(); ++i) {
            System.out.printf("%d. %s\n", i+1, taskStore.get(i));
        }
    }

    /**
     * Returns a copy of the task list.
     *
     * @return ArrayList containing all tasks
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> resList = new ArrayList<>(taskStore);
        return resList;
    }

    /**
     * Creates a new task and adds it to the list.
     *
     * @param type Type of the task (todo, deadline, or event)
     * @param input Description of the task
     * @throws DukeException If the input is invalid
     */
    public void createTask(String type, String input) throws DukeException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            if (input.isEmpty()) throw new DukeException("Empty Task description provided.");
            System.out.println("Got it. I've added this task:");
            Task task;
            switch (type) {
                case "todo":
                    task = new Todo(input);
                    break;
                case "deadline":
                    if (!input.contains("/by") || input.indexOf("/by") == input.length() - 3) throw new DukeException("Invalid deadline description provided.");
                    String[] deadlineInput = input.split("/by", 2);
                    task = new Deadline(deadlineInput[0].trim(), LocalDateTime.parse(deadlineInput[1].trim(), dtf));
                    break;
                case "event":
                    if (
                            !input.contains("/from") ||
                                    !input.contains("/to") ||
                                    input.indexOf("/from") == input.length() - 5 ||
                                    input.indexOf("/to") == input.length() - 2
                    ) throw new DukeException("Invalid event description provided.");
                    String[] eventInput = input.split("/from", 2);
                    String[] eventTimeInput = eventInput[1].trim().split("/to", 2);
                    task = new Event(eventInput[0].trim(), LocalDateTime.parse(eventTimeInput[0].trim(), dtf), LocalDateTime.parse(eventTimeInput[1].trim(), dtf));
                    break;
                default:
                    throw new DukeException("Invalid Task type.");
            }
            this.taskStore.add(task);
            Storage.saveData();
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list.\n", this.taskStore.size());
        } catch (DateTimeParseException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param input Index of the task to delete
     * @throws DukeException If the index is invalid or the list is empty
     */
    public void deleteTask(String input) throws DukeException {
        if (this.taskStore.isEmpty()) throw new DukeException("Task list is already empty.");
        if (input.isEmpty()) throw new DukeException("No Task index provided.");
        String reg = input.replaceAll("\\D+","");
        if (reg.isEmpty()) throw new DukeException("No index provided.");
        int id = Integer.parseInt(reg);
        if (id > this.taskStore.size() || id < 1) throw new DukeException("Invalid index provided.");
        Task task = this.taskStore.get(id - 1);
        this.taskStore.remove(id - 1);
        Storage.saveData();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.taskStore.size());
    }

    /**
     * Marks a task as done.
     *
     * @param input Index of the task to mark as done
     * @throws DukeException If the index is invalid or the list is empty
     */
    public void mark(String input) throws DukeException {
        if (this.taskStore.isEmpty()) throw new DukeException("List is empty, no tasks to mark.");
        if (input == null) throw new DukeException("No input provided.");
        String reg = input.replaceAll("\\D+","");
        if (reg.isEmpty()) throw new DukeException("No index provided.");
        int id = Integer.parseInt(reg);
        if (id > this.taskStore.size() || id < 1) throw new DukeException("Invalid index provided.");
        this.taskStore.get(id - 1).markAsDone();
        Storage.saveData();
    }

    /**
     * Marks a task as not done.
     *
     * @param input Index of the task to mark as not done
     * @throws DukeException If the index is invalid or the list is empty
     */
    public void unmark(String input) throws DukeException {
        if (this.taskStore.isEmpty()) throw new DukeException("List is empty, no tasks to unmark.");
        if (input == null) throw new DukeException("No input provided.");
        String reg = input.replaceAll("\\D+","");
        if (reg.isEmpty()) throw new DukeException("No index provided.");
        int id = Integer.parseInt(reg);
        if (id > this.taskStore.size() || id < 1) throw new DukeException("Invalid index provided.");
        this.taskStore.get(id - 1).markAsNotDone();
        Storage.saveData();
    }
}
