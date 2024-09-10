package joe.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import joe.exceptions.InvalidIndexException;
import joe.main.Parser;
import joe.utils.Formatter;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private static final String line =
            "____________________________________________________________";
    private final List<Task> taskList;

    /**
     * Default Constructor for TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
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
        taskList = new ArrayList<>();

        for (String line : lines) {
            if (line == null) {
                break;
            }
            taskList.add(Parser.parseTask(line));
        }

        System.out.println("Data loaded successfully");
        System.out.println(line);
    }

    /**
     * Adds a task to the user's list.
     * @param t the task to be added
     * @return the message to be displayed to the user
     */
    public String addTask(Task t) {
        taskList.add(t);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("  ").append(t).append("\n");
        sb.append("Now you have ").append(taskList.size()).append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Deletes a task from the user's list.
     * @param index the index of the task to be deleted
     * @return the message to be displayed to the user
     * @throws InvalidIndexException if the index is invalid
     */
    public String deleteTask(int index) throws InvalidIndexException {
        if (index - 1 >= taskList.size() || index - 1 < 0) {
            throw new InvalidIndexException(index);
        }

        StringBuilder sb = new StringBuilder();
        Task t = taskList.remove(index - 1);
        sb.append("Noted. I've removed this task:\n");
        sb.append("  ").append(t).append("\n");
        sb.append("Now you have ").append(taskList.size()).append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Marks a task as done.
     * @param index the index of the task to be marked as done
     * @return the message to be displayed to the user
     * @throws InvalidIndexException if the index is invalid
     */
    public String markDone(int index) throws InvalidIndexException {
        if (index - 1 >= taskList.size() || index - 1 < 0) {
            throw new InvalidIndexException(index);
        }
        Task t = taskList.get(index - 1);
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
        if (index - 1 >= taskList.size() || index - 1 < 0) {
            throw new InvalidIndexException(index - 1);
        }
        Task t = taskList.get(index - 1);
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
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }

        return sb.toString();
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
            taskList.stream()
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
    public String queryTasksByDate(String date) {
        // The arbitrary 1200 time here will not affect the output
        LocalDateTime targetDate = Formatter.createLocalDateTimeWithArbitraryTime(date);
        StringBuilder sb = new StringBuilder();
        int tasksFound = 0;
        for (Task t : taskList) {
            if (t instanceof Deadline d) {
                long days = d.daysTillDeadline(targetDate);
                if (days == 0L) {
                    sb.append(String.format("%d.%s\n", ++tasksFound, d));
                }
            } else if (t instanceof Event e) {
                long days = e.daysTillEvent(targetDate);
                if (days == 0L) {
                    sb.append(String.format("%d.%s\n", ++tasksFound, e));
                }
            }
        }

        if (tasksFound == 0) {
            sb.append(String.format("There are no tasks on %s\n", targetDate.format(DateTimeFormatter.ISO_DATE)));
        }

        return sb.toString();
    }

    /**
     * Returns the number of tasks in the user's list.
     *
     * @return the number of tasks in the user's list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at a specific index.
     *
     * @param index the index of the task
     * @return the task at the index
     */
    public Task getTask(int index) {
        return taskList.get(index);
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
        for (Task t : taskList) {
            String description = t.getDescription();
            if (description.contains(keyword)) {
                sb.append(String.format("%d. %s\n", ++foundStrings, t));
            }
        }

        return sb.toString();
    }
}
