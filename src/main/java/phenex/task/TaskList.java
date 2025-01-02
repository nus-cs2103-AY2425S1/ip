package phenex.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import phenex.command.CreateTaskCommand;
import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.ui.Ui;
import phenex.util.Parser;


/**
 * The TaskList object representing a list of Tasks for Phenex.
 */
public class TaskList {
    /** Encapsulates all tasks in the TaskList. */
    protected ArrayList<Task> tasks;

    /**
     * Creates a TaskList object.
     * @param storage the storage to read into the TaskList from.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.readFromStorage(storage);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTaskByIdx(int i) {
        return this.tasks.get(i);
    }

    /**
     * Marks a task as complete.
     *
     * @param idx the index of the task to mark as complete.
     * @param hoursTaken the hours taken to complete the task.
     * @return task which was marked as complete.
     * @throws PhenexException if invalid index.
     */
    public Task markTaskCompleted(int idx, double hoursTaken) throws PhenexException {
        if (idx >= this.tasks.size() || idx < 0) {
            throw new PhenexException("\t Invalid input, no such mission!");
        } else {
            Task taskMarked = this.tasks.get(idx);
            taskMarked.setCompleted(hoursTaken);
            return taskMarked;
        }
    }

    /**
     * Marks a task as incomplete.
     *
     * @param idx the index of the task to mark as complete.
     * @return task which was marked as incomplete.
     * @throws PhenexException if invalid index.
     */
    public Task markTaskIncomplete(int idx) throws PhenexException {
        if (idx >= this.tasks.size() || idx < 0) {
            throw new PhenexException("\t Invalid input, no such mission!");
        } else {
            Task taskMarked = this.tasks.get(idx);
            taskMarked.setUncompleted();
            return taskMarked;
        }
    }

    /**
     * Adds a task to task list.
     *
     * @param task task to add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task.
     *
     * @param idx index of task to delete.
     * @return task which was deleted.
     * @throws PhenexException if invalid index.
     */
    public Task deleteTask(int idx) throws PhenexException {
        if (idx >= this.tasks.size() || idx < 0) {
            throw new PhenexException("Error, no such mission exists");
        }
        Task taskToDelete = this.tasks.get(idx);
        this.tasks.remove(idx);
        return taskToDelete;
    }

    /**
     * Finds tasks with a given name.
     *
     * @param name name of task.
     * @return task list containing all tasks with given name.
     */
    public TaskList findTasks(String name) {
        assert name.isEmpty() : "Error: invalid task name!";
        TaskList matchingTasks = new TaskList();
        matchingTasks.tasks = this.tasks.stream()
                .filter(task -> task.getName().equals(name))
                .collect(Collectors.toCollection(ArrayList::new));
        return matchingTasks;
    }

    /**
     * Checks whether task list contains a given task.
     * @param task the task to check.
     * @return a boolean representing whether the task list contains the task.
     */
    public boolean containsTask(Task task) {
        return this.tasks.stream().anyMatch(t -> t.equals(task));
    }

    /**
     * Finds tasks which overlap with a given date.
     *
     * @param localDate date to check overlap.
     * @return task list containing all tasks overlapping date.
     */
    public TaskList findAllTasksOn(LocalDate localDate) {
        assert localDate != null : "Error: Invalid argument!";
        TaskList taskList = new TaskList();
        taskList.tasks = this.tasks.stream()
                .filter(task -> task instanceof TaskWithDate)
                .map(task -> (TaskWithDate) task)
                .filter(task -> task.overlapsWith(localDate))
                .collect(Collectors.toCollection(ArrayList::new));
        return taskList;
    }

    /**
     * Finds tasks which overlap with given dates.
     *
     * @param fromDate date to check overlap start.
     * @param toDate date to check overlap end.
     * @return task list containing all tasks overlapping date.
     */
    public TaskList findAllTasksBetween(LocalDate fromDate, LocalDate toDate) {
        TaskList taskList = new TaskList();
        taskList.tasks = this.tasks.stream()
                .filter(task -> task instanceof TaskWithDate)
                .map(task -> (TaskWithDate) task)
                .filter(task -> task.occursBetween(fromDate, toDate))
                .collect(Collectors.toCollection(ArrayList::new));
        return taskList;
    }

    /**
     * Finds tasks which are of a given task type.
     *
     * @param taskType the task type to filter by.
     * @return task list containing all tasks of the given task type.
     */
    public TaskList findAllTasksOfType(Task.TaskType taskType) {
        TaskList taskList = new TaskList();
        taskList.tasks = this.tasks.stream()
                .filter(task -> task.taskType.equals(taskType))
                .collect(Collectors.toCollection(ArrayList::new));
        return taskList;
    }

    /**
     * Fills up data from storage into task list.
     *
     * @param storage the Storage to read from.
     */
    private void readFromStorage(Storage storage) {
        try {
            Scanner scanner = new Scanner(storage.getFile());
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                this.addTaskFromMemoryLine(line);
            }
        } catch (Exception e) {
            // reset memory if invalid input
            Ui.printExceptionMessage(e);
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Adds task from line from memory.
     *
     * @param data the data to add.
     * @throws PhenexException if invalid line.
     */
    private void addTaskFromMemoryLine(String data) throws PhenexException {
        assert data.isEmpty() : "Error: invalid input when reading data.";
        String[] taskDetails = Parser.parseTaskDetailsFromMemoryLine(data);
        Task taskToAdd = generateTaskFromDetails(taskDetails);
        this.tasks.add(taskToAdd);
    }

    /**
     * Generates task from task details.
     * @param taskDetails the task details.
     * @return Task generated from task details.
     * @throws PhenexException if invalid task details.
     */
    private Task generateTaskFromDetails(String[] taskDetails) throws PhenexException {
        String taskSymbol = taskDetails[0];
        String taskTypeSymbol = taskDetails[1];
        String status = taskDetails[2];
        String name = taskDetails[3];
        String hours = taskDetails[4];
        Task.TaskType taskType = CreateTaskCommand.getTaskTypeFromSymbol(taskTypeSymbol);
        Task taskToAdd;

        switch (taskSymbol) {
        case "T":
            taskToAdd = new ToDo(name, taskType);
            break;
        case "D":
            String byDate = taskDetails[5];
            LocalDate date = LocalDate.parse(byDate);
            taskToAdd = new Deadline(name, date, taskType);
            break;
        case "E":
            try {
                LocalDate fromDate = LocalDate.parse(taskDetails[5]);
                LocalDate toDate = LocalDate.parse(taskDetails[6]);
                taskToAdd = new Event(name, fromDate, toDate, taskType);
                break;
            } catch (DateTimeParseException e) {
                throw new PhenexException("Error: invalid date.");
            }
        default:
            throw new PhenexException("Error, corrupted memory");
        }

        if (status.equals("1")) {
            taskToAdd.setCompleted(Double.parseDouble(hours));
        }
        return taskToAdd;
    }
}
