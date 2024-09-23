package tars.tasks;

import tars.exceptions.TarsException;

import tars.parsers.ToDoParser;
import tars.parsers.DeadlineParser;
import tars.parsers.EventParser;

import java.util.Comparator;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Manages a list of tasks and provides operations to add, find, delete, and display tasks.
 *
 * <p>The {@code TaskList} class encapsulates a collection of tasks, allowing operations
 * such as adding new tasks of different types (e.g., ToDo, Deadline, Event), finding a
 * task by its position, deleting tasks, and generating a string representation of all tasks.</p>
 */
public class TaskList {
    List<Task> taskList;

    ToDoParser toDoParser = new ToDoParser();
    DeadlineParser deadlineParser = new DeadlineParser();
    EventParser eventParser = new EventParser();

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param taskList The initial list of {@link Task} objects to manage.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int noOfTasks() {
        return taskList.size();
    }

    /**
     * Adds a new task to the list based on the provided input.
     *
     * @param input The input string representing the task details.
     * @return The {@link Task} that was added to the list.
     * @throws TarsException if the task type is not recognized or if the input format is invalid.
     */
    public Task addTask(String input) {

        String[] taskInfo = input.split(" ", 2);
        Task t;

        assert taskInfo.length > 1;

        String taskType = taskInfo[0];

        if (taskType.contains("todo")) {
            t = toDoParser.parse(taskInfo);
            taskList.add(t);

        } else if (taskType.contains("deadline")) {
            t = deadlineParser.parse(taskInfo);
            taskList.add(t);

        } else if (taskType.contains("event")) {
            t = eventParser.parse(taskInfo);
            taskList.add(t);

        } else {

            throw new TarsException("Hmm, that one flew right over my circuits. " +
                    "Try using words in my vocabulary next time");
        }

        assert t != null;
        taskList.sort(Comparator.naturalOrder());
        return t;
    }

    /**
     * Finds a task in the list based on the provided input.
     *
     * @param input The input string containing the index of the task to find.
     * @return The {@link Task} found at the specified index.
     * @throws TarsException if the index is out of range or if the input format is invalid.
     */
    public Task findTask(String input) {

        String[] inputSplit = input.split(" ");
        if (inputSplit.length > 2) {
            throw new TarsException("Too many arguments");
        }

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            int i = Integer.parseInt(matcher.group());
            if (taskList.size() < i) {
                throw new TarsException("Task not in list. Check again");
            }

            return taskList.get(i - 1);
        }

        throw new TarsException("Provide a number at the end");
    }

    /**
     * Finds tasks in the task list that match a given description.
     *
     * <p>This method searches through the task list for tasks whose string representation
     * contains the specified search term. It returns a list of formatted strings representing
     * the matching tasks.</p>
     *
     * @param input The input string containing the search command and description. The description
     *              should follow the command (e.g., "find keyword").
     * @return A {@link List} of {@link String} objects representing the tasks that match the search term.
     * @throws TarsException if the input does not contain a search term.
     */
    public List<String> findTaskByDescription(String input) {

        String[] search = input.split(" ", 2);

        if (search.length != 2) {
            throw new TarsException("Tell me what you are searching for");
        }

        String searchString = search[1];

        return IntStream.range(0, taskList.size())
                .filter(i -> taskList.get(i).toString().contains(searchString))
                .mapToObj(i -> String.format("%s. %s", i+1, taskList.get(i)))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a task from the list based on the provided input.
     *
     * @param input The input string containing the index of the task to delete.
     * @return The {@link Task} that was removed from the list.
     * @throws TarsException if the index is out of range or if the input format is invalid.
     */
    public Task deleteTask(String input) {

        String[] inputSplit = input.split(" ");
        if (inputSplit.length > 2) {
            throw new TarsException("Too many arguments. You can only delete 1 task at a time");
        }

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        Task t;

        if (matcher.find()) {
            int i = Integer.parseInt(matcher.group());
            if (taskList.size() < i) {
                throw new TarsException("Task not in list. Check again");
            }

            t = taskList.get(i - 1);
            taskList.remove(t);

            return t;

        }

        throw new TarsException("Provide a number at the end");
    }

    /**
     * Returns the list of tasks.
     *
     * @return A list of {@link Task} objects.
     */
    public List<Task> getTasks() {
        return taskList;
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @return A formatted string representing all tasks, including their status and details.
     */
    @Override
    public String toString() {

        if (taskList.isEmpty()) {
            return "No tasks yet. Add some tasks to get started";
        }

        StringBuilder str = new StringBuilder("Here are your tasks, champ. " +
                "Let's see how many you can actually cross off.\n");

        int index = 1;
        for (Task task : taskList) {
            str.append(index).append(". ").append(task.toString()).append('\n');
            index++;
        }

        if (!str.isEmpty()) {
            str.setLength(str.length() - 1);
        }


        return str.toString();

    }
}
