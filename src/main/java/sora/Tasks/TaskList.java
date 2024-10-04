package sora.Tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sora.Parser;
import sora.SoraException;

/**
 * TaskList is a List of Tasks.
 * It has methods to operate the list (e.g. mark, add, delete).
 */
public class TaskList {
    /** List of Tasks */
    private final List<Task> taskList;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Getter Method to get the task list.
     *
     * @return Task List of this instance.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the number of Tasks in this TaskList instance.
     *
     * @return Size of this TaskList instance.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Marks a task as done.
     * Returns the Marked Task.
     *
     * @param value Index of the task to be marked as done. Starting from 1.
     * @return The Marked Task
     * @throws SoraException If Task is already marked or value is not an integer or out of bounds.
     */
    public Task markTask(String value) throws SoraException {
        try {
            Task task = this.taskList.get(Integer.parseInt(value) - 1);
            if (task.isDone) {
                throw new SoraException("Sora has discovered task as already marked as done!\n");
            }
            task.markAsDone();
            return task;
        } catch (NumberFormatException e) {
            throw new SoraException("Please Enter - Mark (int)\n");
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("Please Enter Integer Value within List Size!\n");
        }
    }

    /**
     * Marks a task as not done.
     * Returns the Unmarked Task.
     *
     * @param value Index of the task to be marked as not done. Starting from 1.
     * @return The Unmarked Task.
     * @throws SoraException If Task is already unmarked or value is not an integer or out of bounds.
     */
    public Task unmarkTask(String value) throws SoraException {
        try {
            Task task = this.taskList.get(Integer.parseInt(value) - 1);
            if (!task.isDone) {
                throw new SoraException("Sora has discovered task as already marked as not done!\n");
            }
            task.markAsNotDone();
            return task;
        } catch (NumberFormatException e) {
            throw new SoraException("Please Enter - Unmark (int)\n");
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("Please Enter Integer Value within List Size!\n");
        }
    }

    /**
     * Creates a new task and add it to this TaskList instance.
     * Returns the Added Task.
     *
     * @param mainCommand Type of task (Todo, Deadline, Event)
     * @param parsedCommand Details of task in parsed format.
     * @return The Added Task.
     * @throws SoraException If parsedCommand is invalid.
     */
    public Task addTask(String mainCommand, ArrayList<String> parsedCommand) throws SoraException {
        assert !mainCommand.isEmpty() : "MainCommand in TaskList::addTask should not be Empty\n";
        switch (mainCommand) {
        case "todo":
            try {
                this.taskList.add(new ToDo(parsedCommand.get(1)));
                break;
            } catch (IndexOutOfBoundsException e) {
                throw new SoraException("Please Enter - Todo (Description)\n");
            }
        case "deadline":
            try {
                // Obtain by or byDate
                String by = parsedCommand.get(2).substring(3);
                LocalDateTime byDate = Parser.parseDate(by);
                if (byDate == null) {
                    this.taskList.add(new Deadline(parsedCommand.get(1), by));
                } else {
                    this.taskList.add(new Deadline(parsedCommand.get(1), byDate));
                }
                break;
            } catch (IndexOutOfBoundsException e) {
                throw new SoraException("Please Enter - Deadline (Description) /by (dd/MM/yy HHmm)\n");
            }
        case "event":
            try {
                // Obtain from or fromDate
                String from = parsedCommand.get(2).substring(5);
                LocalDateTime fromDate = Parser.parseDate(from);
                // Obtain to or toDate
                String to = parsedCommand.get(3).substring(3);
                LocalDateTime toDate = Parser.parseDate(to);
                if (fromDate != null && toDate != null) {
                    this.taskList.add(new Event(parsedCommand.get(1), fromDate, toDate));
                } else if (fromDate != null && toDate == null) {
                    this.taskList.add(new Event(parsedCommand.get(1), fromDate, to));
                } else if (fromDate == null && toDate != null) {
                    this.taskList.add(new Event(parsedCommand.get(1), from, toDate));
                } else {
                    this.taskList.add(new Event(parsedCommand.get(1), from, to));
                }
                break;
            } catch (IndexOutOfBoundsException e) {
                throw new SoraException(
                        "Please Enter - Event (Description) /from (dd/MM/yy HHmm) /to (dd/MM/yy HHmm)\n");
            }
        default:
            // Do Nothing
        }
        return this.taskList.get(this.taskList.size() - 1);
    }

    /**
     * Deletes a task.
     * Returns the Deleted Task.
     *
     * @param value Index of the task to be deleted. Starting from 1.
     * @return The Deleted Task.
     * @throws SoraException If value is not an integer or out of bounds.
     */
    public Task deleteTask(String value) throws SoraException {
        try {
            int index = Integer.parseInt(value) - 1;
            Task deletedTask = this.taskList.remove(index);
            return deletedTask;
        } catch (NumberFormatException e) {
            throw new SoraException("Please Enter - Delete (int)\n");
        } catch (IndexOutOfBoundsException e) {
            throw new SoraException("Please Enter Integer Value within List Size\n");
        }
    }

    /**
     * Locates all tasks, containing the specified substring, of this TaskList instance.
     * Returns a Stringbuilder containing the Tasks in String Format.
     * If No Tasks are found, a Unique Statement "No Tasks Found" will be returned.
     *
     * @param s User's Substring.
     * @return Stringbuilder containing the Tasks in String Format.
     */
    public StringBuilder findTask(String s) {
        assert !s.isEmpty() : "Substring in TaskList::findTask should not be Empty!\n";
        HashMap<Integer, Task> taskListFound = new HashMap<>();
        int index = 1;
        for (Task t : this.taskList) {
            if (t.descriptionContainsString(s)) {
                taskListFound.put(index, t);
            }
            index++;
        }
        StringBuilder sb = new StringBuilder();
        if (taskListFound.isEmpty()) {
            sb.append("Sora is unable to find any Tasks!\n");
        } else {
            sb.append("Sora has found the following Tasks:\n");
        }
        taskListFound.forEach((i, t) -> sb.append(i++ + ". " + t.toString() + "\n"));
        return sb;
    }
}
