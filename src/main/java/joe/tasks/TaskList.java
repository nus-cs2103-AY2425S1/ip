package joe.tasks;

import joe.exceptions.InvalidIndexException;
import joe.utils.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents the list of tasks.
 * Methods return the String message to be displayed to the user based on
 * the success of the operation.
 */
public class TaskList {

    private static final String LINE =
            "____________________________________________________________";
    private final List<Task> tasks;

    /**
     * Default Constructor for TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        System.out.println("New task list created.");
        System.out.println(LINE);
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
        System.out.println(LINE);
    }

    /**
     * Writes the tasks to a file.
     *
     * @param fileData the data to be written to the file
     * @throws IOException if an I/O error occurs
     */
    private static void writeFileData(String fileData) throws IOException {
        String jarPath = TaskList.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String jarDir = new File(jarPath).getParentFile().getPath();

        File file = new File(jarDir, "joe.txt");

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(fileData);
        }
    }

    /**
     * Generates the message to be displayed to the user after marking a task as done.
     *
     * @param t the task that was marked as done
     * @return the message to be displayed to the user
     */
    private static String getMarkMessage(Task t) {
        return String.format("Nice! I've marked this task as done:\n  %s", t);
    }

    /**
     * Generates the message to be displayed to the user after unmarking a task as not done.
     *
     * @param t the task that was unmarked as done
     * @return the message to be displayed to the user
     */
    private static String getUnmarkMessage(Task t) {
        return String.format("OK, I've marked this task as not done:\n  %s", t);
    }

    /**
     * Adds a task to the user's list, it checks for duplicates in the tasklist to ensure no two tasks are the same.
     *
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
        return getAddMessage(t);
    }

    /**
     * Generates the message to be displayed to the user after adding a task.
     *
     * @param t the task that was added
     * @return the message to be displayed to the user
     */
    private String getAddMessage(Task t) {
        return String.format("""
                        Got it. I've added this task:
                         %s
                        Now you have %d tasks in the list.""",
                t, tasks.size());
    }

    /**
     * Deletes a task from the user's list.
     *
     * @param index the index of the task to be deleted
     * @return the message to be displayed to the user
     * @throws InvalidIndexException if the index is invalid
     */
    public String deleteTask(int index) throws InvalidIndexException {
        checkValidIndex(index);

        Task t = tasks.remove(index - 1);
        return getDeleteMessage(t);
    }

    /**
     * Checks if the index is valid.
     *
     * @param index the index to be checked
     * @throws InvalidIndexException if the index is invalid
     */
    private void checkValidIndex(int index) throws InvalidIndexException {
        if (index - 1 >= tasks.size() || index - 1 < 0) {
            throw new InvalidIndexException(index);
        }
    }

    /**
     * Generates the message to be displayed to the user after deleting a task.
     *
     * @param t the task that was deleted
     * @return the message to be displayed to the user
     */
    private String getDeleteMessage(Task t) {
        return String.format("""
                        Noted. I've removed this task:
                         %s
                        Now you have %d tasks in the list.""",
                t, tasks.size());
    }

    /**
     * Marks a task as done.
     *
     * @param index the index of the task to be marked as done
     * @return the message to be displayed to the user
     * @throws InvalidIndexException if the index is invalid
     */
    public String markDone(int index) throws InvalidIndexException {
        checkValidIndex(index);
        Task t = tasks.get(index - 1);
        t.setDone(true);

        return getMarkMessage(t);
    }

    /**
     * Unmarks a task as done.
     *
     * @param index the index of the task to be unmarked as done
     * @return the message to be displayed to the user
     * @throws InvalidIndexException if the index is invalid
     */
    public String unmark(int index) throws InvalidIndexException {
        checkValidIndex(index);
        Task t = tasks.get(index - 1);
        t.setDone(false);

        return getUnmarkMessage(t);
    }

    /**
     * Lists the tasks in the user's list.
     *
     * @return the message to be displayed to the user
     */
    public String listTasks() {
        return "Here are the tasks in your list:\n"
                + IntStream.rangeClosed(1, tasks.size())
                .mapToObj(i -> String.format("%d. %s\n",
                        i, tasks.get(i - 1)))
                .reduce("", String::concat);
    }

    /**
     * Saves the tasks in the user's list to a file.
     *
     * @return the message to be displayed to the user
     */
    public String saveTasks() {
        try {
            String fileData = generateFileDataString();
            writeFileData(fileData);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        return getSaveMessage();
    }

    /**
     * Generates the message to be displayed to the user after saving the tasks.
     *
     * @return the message to be displayed to the user
     */
    private String getSaveMessage() {
        return "Saving your tasks......\n"
                + tasks.stream()
                       .map(Task::toString)
                       .collect(Collectors.joining(" (saved)\n"))
                + " (saved)\nYour tasks have been successfully saved.";
    }

    /**
     * Generates the string representation of the tasks to be saved to the file.
     *
     * @return the string representation of the tasks
     */
    private String generateFileDataString() {
        return tasks.stream().map(Task::saveRepr).collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Queries the tasks in the user's list by date.
     *
     * @param date the date to query the tasks by
     * @return the message to be displayed to the user
     */
    public String viewScheduleOnDate(String date) {
        // The arbitrary 1200 time here will not affect the output
        LocalDateTime targetDate = Parser.createLocalDateTimeWithArbitraryTime(date);
        String message = getScheduleString(targetDate);

        if (message.isEmpty()) {
            return String.format("You have no tasks on %s\n", targetDate.format(DateTimeFormatter.ISO_DATE));
        }

        return message;
    }

    /**
     * Generates the task based on the date provided by the user.
     *
     * @param targetDate the date to query the tasks by
     * @return the message to be displayed to the user
     */
    private String getScheduleString(LocalDateTime targetDate) {
        class TaskCounter {
            private static int count = 0;

            static int index() {
                return ++count;
            }
        }

        return String.format("Here are the tasks on %s:\n", targetDate.format(DateTimeFormatter.ISO_DATE))
                + tasks.stream()
                .filter(t -> (t instanceof Deadline d && d.daysTillDeadline(targetDate) == 0L)
                        || (t instanceof Event e && e.daysTillEvent(targetDate) == 0L))
                .sorted(Comparator.comparing(Task::getTime))
                .map(t -> String.format("%d. %s\n", TaskCounter.index(), t))
                .reduce("", String::concat);
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
