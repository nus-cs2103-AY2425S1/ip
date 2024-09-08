package hamyo.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import hamyo.misc.HamyoException;
import hamyo.misc.Ui;

/**
 * Represents the list of users' tasks. Provides operations to add/delete etc.
 *
 * @author Han Yu
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructor for TaskList. Creates a new instance of an ArrayList.
     */
    public TaskList() {
        super();
    }

    /**
     * Adds the specified Task to the list of users' tasks.
     *
     * @param taskType Type of task (i.e. To-Do, Deadline, Event).
     * @param input Details required to create Task such as description, dates etc.
     * @throws HamyoException If the command is incomplete/invalid.
     */
    public void addTask(TaskType taskType, String... input) throws HamyoException {
        if (taskType.equals(TaskType.TODO)) {
            assert input.length == 1 : "incorrect number of ToDo inputs";
            this.add(new ToDo(input[0]));
        } else if (taskType.equals(TaskType.DEADLINE)) {
            assert input.length == 2 : "incorrect number of Deadline inputs";
            this.add(new Deadline(input[0], input[1]));
        } else if (taskType.equals(TaskType.EVENT)) {
            assert input.length == 3 : "incorrect number of Event input";
            this.add(new Event(input[0], input[1], input[2]));
        }
        Ui.printAddTask(this.get(this.size() - 1), this.size());
    }

    /**
     * Deletes the specified Task from the list of users' tasks.
     *
     * @param index Index of task to delete.
     * @throws HamyoException If the command is incomplete/invalid.
     */
    public void deleteTask(int index) throws HamyoException {
        try {
            if (index < 0 || index >= this.size()) {
                throw new HamyoException("Usage: delete [index]");
            }
            Task deletedTask = this.remove(index);

            Ui.printDeleteTask(deletedTask, this.size());
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: delete [index]");
        }
    }

    /**
     * List all the users' tasks onto the terminal.
     */
    public void listTasks() throws HamyoException {
        String tasksList = IntStream.rangeClosed(1, this.size())
            .mapToObj(i -> i + ". " + this.get(i - 1).toString())
            .collect(Collectors.joining("\n"));

        Ui.printListTasks(tasksList);
    }

    /**
     * List all the users' tasks on the specified date onto the terminal.
     *
     * @param strDate Date in String format, (e.g. "2002-09-18").
     * @throws HamyoException If the command is incomplete/invalid.
     */
    public void listTasksByDate(String strDate) throws HamyoException {
        try {
            LocalDate date = LocalDate.parse(strDate);
            AtomicInteger counter = new AtomicInteger(1);
            String tasksList = this.stream()
                .filter(task -> task instanceof Deadline || task instanceof Event) // Filter for Deadline and Event.
                .filter(task -> task.occursToday(date)) // Filter for Deadline and Event that occurs today.
                .map(task -> counter.getAndIncrement() + ". " + task.toString()) // Format as String.
                .collect(Collectors.joining("\n"));

            Ui.printListTasksByDate(tasksList, date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } catch (Exception e) {
            throw new HamyoException("Usage: listDate yyyy-MM-dd.");
        }
    }

    /**
     * List all the users' tasks with the specified keyword onto the terminal.
     *
     * @param keyword Keyword to filter list, (e.g. "apple").
     * @throws HamyoException If the command is incomplete/invalid.
     */
    public void listTasksByKeyword(String keyword) throws HamyoException {
        try {
            AtomicInteger counter = new AtomicInteger(1);
            String tasksList = this.stream()
                .filter(task -> task.toString().toLowerCase().contains(keyword.toLowerCase()))
                .map(task -> counter.getAndIncrement() + ". " + task)
                .collect(Collectors.joining("\n"));

            Ui.printListTasksByKeyword(tasksList, keyword);
        } catch (Exception e) {
            throw new HamyoException("Usage: listDate yyyy-MM-dd.");
        }
    }

    /**
     * Mark a task in the list of users' tasks.
     *
     * @param index Index of task to mark.
     * @throws HamyoException If the command is incomplete/invalid.
     */
    public void markTask(int index) throws HamyoException {
        try {
            if (index < 0 || index >= this.size()) {
                throw new HamyoException("Usage: mark [index]");
            }
            this.get(index).mark(true);
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: mark [index]");
        }
    }

    /**
     * Unmark a task in the list of users' tasks.
     *
     * @param index Index of task to unmark.
     * @throws HamyoException If the command is incomplete/invalid.
     */
    public void unmarkTask(int index) throws HamyoException {
        try {
            if (index < 0 || index >= this.size()) {
                throw new HamyoException("Usage: unmark [index]");
            }
            this.get(index).unmark(true);
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: unmark [index]");
        }
    }
}
