package tasks;

import parsers.ToDoParser;
import parsers.DeadlineParser;
import parsers.EventParser;

import exceptions.TarsException;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    List<Task> taskList;

    ToDoParser toDoParser = new ToDoParser();
    DeadlineParser deadlineParser = new DeadlineParser();
    EventParser eventParser = new EventParser();

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int noOfTasks() {
        return taskList.size();
    }

    public Task addTask(String input) {

        String[] taskInfo = input.split(" ", 2);
        Task t;

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

            throw new TarsException("Hmm, that one flew right over my circuits. Try using words in my vocabulary next time");
        }

        return t;
    }


    public Task findTask(String input) {
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

        List<String> tasksFound = new ArrayList<>();
        String searchString = search[1];

        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.toString().contains(searchString)) {
                tasksFound.add(String.format("%s. %s", i+1, t));
            }
        }

        return tasksFound;
    }

    public Task deleteTask(String input) {
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

    public List<Task> getTasks() {
        return taskList;
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder("Here are your tasks, champ. Let's see how many you can actually cross off.\n");
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
