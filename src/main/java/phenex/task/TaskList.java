package phenex.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.ui.Ui;



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
     * @return task which was marked as complete.
     * @throws PhenexException if invalid index.
     */
    public Task markTaskCompleted(int idx) throws PhenexException {
        if (idx >= this.tasks.size() || idx < 0) {
            throw new PhenexException("\t Invalid input, no such mission!");
        } else {
            Task taskMarked = this.tasks.get(idx);
            taskMarked.setCompleted();
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
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.getName().equals(name)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Finds tasks which overlap with a given date.
     *
     * @param localDate date to check overlap.
     * @return task list containing all tasks overlapping date.
     */
    public TaskList findAllTasksOn(LocalDate localDate) {
        TaskList taskList = new TaskList();

        for (Task task : this.tasks) {
            if (task instanceof TaskWithDate) {
                TaskWithDate taskWithDate = (TaskWithDate) task;
                if (taskWithDate.overlapsWith(localDate)) {
                    taskList.addTask(taskWithDate);
                }
            }
        }

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
        String[] taskDetails = data.split(", ");
        if (taskDetails.length <= 1) {
            throw new PhenexException("Error, corrupted memory.");
        }

        String symbol = taskDetails[0];
        String status = taskDetails[1];
        String name;
        Task taskToAdd;

        switch (symbol) {
        case "T":
            if (taskDetails.length != 3) {
                throw new PhenexException("Error, corrupted memory.");
            }
            name = taskDetails[2];
            taskToAdd = new ToDo(name);
            break;
        case "D":
            if (taskDetails.length != 4) {
                throw new PhenexException("Error, corrupted memory.");
            }
            name = taskDetails[2];
            String byDate = taskDetails[3];
            LocalDate date = LocalDate.parse(byDate);
            taskToAdd = new Deadline(name, date);
            break;
        case "E":
            if (taskDetails.length != 5) {
                throw new PhenexException("Error, corrupted memory.");
            }
            name = taskDetails[2];
            try {
                LocalDate fromDate = LocalDate.parse(taskDetails[3]);
                LocalDate toDate = LocalDate.parse(taskDetails[4]);
                taskToAdd = new Event(name, fromDate, toDate);
                break;
            } catch (DateTimeParseException e) {
                throw new PhenexException(e.getMessage());
            }
        default:
            throw new PhenexException("Error, corrupted memory");
        }

        if (status.equals("1")) {
            taskToAdd.setCompleted();
        }
        this.tasks.add(taskToAdd);
    }
}
