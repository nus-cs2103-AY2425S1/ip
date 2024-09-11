package joe.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import joe.exceptions.InvalidIndexException;
import joe.utils.Parser;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private static final String line =
            "____________________________________________________________";
    private final List<Task> tasks;

    /**
     * Default Constructor for TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        System.out.println("New task list created.");
        System.out.println(line);
    }

    /**
     * Constructor for TaskList that loads the tasks from a file.
     * The file is expected to be in the format of the save representation of the tasks.
     *
     * @param lines the lines of tasks to be loaded
     */
    public TaskList(String[] lines) {
        tasks = new ArrayList<>();

        for (String line : lines) {
            if (line == null) {
                break;
            }
            tasks.add(Parser.parseTask(line));
        }

        System.out.println("Data loaded successfully");
        System.out.println(line);
    }

    /**
     * Adds a task to the user's list, it checks for duplicates in the tasklist to ensure no two tasks are the same.
     * @param t the task to be added
     * @return the message to be displayed to the user
     */
    public String addTask(Task t) {
        for (Task task : tasks) {
            if (task.equals(t)) {
                return "This task already exists in the list.";
            }
        }
        tasks.add(t);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("  ").append(t).append("\n");
        sb.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Deletes a task from the user's list.
     * @param index the index of the task to be deleted
     * @return the message to be displayed to the user
     * @throws InvalidIndexException if the index is invalid
     */
    public String deleteTask(int index) throws InvalidIndexException {
        if (index - 1 >= tasks.size() || index - 1 < 0) {
            throw new InvalidIndexException(index);
        }

        StringBuilder sb = new StringBuilder();
        Task t = tasks.remove(index - 1);
        sb.append("Noted. I've removed this task:\n");
        sb.append("  ").append(t).append("\n");
        sb.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Marks a task as done.
     * @param index the index of the task to be marked as done
     * @return the message to be displayed to the user
     * @throws InvalidIndexException if the index is invalid
     */
    public String markDone(int index) throws InvalidIndexException {
        if (index - 1 >= tasks.size() || index - 1 < 0) {
            throw new InvalidIndexException(index);
        }
        Task t = tasks.get(index - 1);
        t.setDone(true);

        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  ").append(t);
        return sb.toString();
    }

    /**
     * Unmarks a task as done.
     * @param index the index of the task to be unmarked as done
     * @return the message to be displayed to the user
     * @throws InvalidIndexException if the index is invalid
     */
    public String unmark(int index) throws InvalidIndexException {
        if (index - 1 >= tasks.size() || index - 1 < 0) {
            throw new InvalidIndexException(index - 1);
        }
        Task t = tasks.get(index - 1);
        t.setDone(false);

        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as undone:\n");
        sb.append("  ").append(t);
        return sb.toString();
    }

    /**
     * Lists the tasks in the user's list.
     * @return the message to be displayed to the user
     */
    public String listTasks() {
        return "Here are the tasks in your list:\n"
                + IntStream.rangeClosed(1, tasks.size())
                           .mapToObj(i -> String.format("%d. %s\n", i, tasks.get(i - 1)))
                           .reduce("", String::concat);
    }

    /**
     * Saves the tasks in the user's list to a file.
     * @return the message to be displayed to the user
     */
    public String saveTasks() {
        StringBuilder botMessage = new StringBuilder();
        botMessage.append("Saving your tasks......\n");
        try {
            StringBuilder fileData = new StringBuilder();
            tasks.stream()
                    .map(Task::saveRepr)
                    .forEach(taskString -> {
                        fileData.append(taskString).append(System.lineSeparator());
                        botMessage.append(Parser.parseTask(taskString)).append(" (saved)\n");
                    });

            FileWriter fw = new FileWriter("data/joe.txt");
            fw.write(fileData.toString());
            fw.close();
            botMessage.append("Your tasks have been successfully saved.\n");
        } catch (IOException e) {
            botMessage.append("Something went wrong: ").append(e.getMessage());
        }

        return botMessage.toString();
    }

    /**
     * Queries the tasks in the user's list by date.
     * @param date the date to query the tasks by
     * @return the message to be displayed to the user
     */
    public String viewScheduleOnDate(String date) {
        // The arbitrary 1200 time here will not affect the output
        LocalDateTime targetDate = Parser.createLocalDateTimeWithArbitraryTime(date);
        class TaskCounter {
            static int count = 0;

            static int index() {
                return ++count;
            }
        }

        TaskCounter.count = 0;

        String message = tasks.stream()
                .filter(t -> (t instanceof Deadline d && d.daysTillDeadline(targetDate) == 0L)
                        || (t instanceof Event e && e.daysTillEvent(targetDate) == 0L))
                .sorted(Comparator.comparing(Task::getTime))
                .map(t -> String.format("%d. %s\n", TaskCounter.index(), t))
                .reduce("", String::concat);

        if (message.isEmpty()) {
            return String.format("You have no tasks on %s\n", targetDate.format(DateTimeFormatter.ISO_DATE));
        }

        return message;
    }

    /**
     * Returns the number of tasks in the user's list.
     *
     * @return the number of tasks in the user's list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at a specific index.
     *
     * @param index the index of the task
     * @return the task at the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Prints the tasks that contains the search query string provided by the user
     *
     * @param keyword the query made by the user to find tasks with matching descriptions
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in the list:\n");

        int foundStrings = 0;
        for (Task t : tasks) {
            String description = t.getDescription();
            if (description.contains(keyword)) {
                sb.append(String.format("%d. %s\n", ++foundStrings, t));
            }
        }

        return sb.toString();
    }
}
