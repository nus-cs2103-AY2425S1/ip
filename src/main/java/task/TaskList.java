package task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stores a list of tasks in an ArrayList data structure.
 */
public class TaskList {
    private static final int INITIAL_SIZE = 100;
    private final List<Task> tasks;

    /**
     * Main constructor for taskList. Takes in a Stream of strings each representing a task.
     * Invokes BabyGronk::initTasks for each string and adds them to the arraylist.
     *
     * @param tasks Steam of tasks.
     */
    public TaskList(Stream<String> tasks) {
        this.tasks = new ArrayList<>(INITIAL_SIZE);
        if (tasks != null) {
            tasks.forEach((task) -> this.tasks.add(initTasks(task)));
        }
    }

    private Task initTasks(String input) {
        String[] args = input.split("] ");
        if (args.length != 2) {
            return (invalidTask(input));
        }

        char taskType = args[0].charAt(1);
        boolean isDone = args[0].charAt(4) == 'X';

        switch (taskType) {
        case 'T':
            return (initTodoTask(args[1], isDone));
        case 'D':
            return (initDeadlineTask(args[1], isDone));
        case 'E':
            return (initEventTask(args[1], isDone));
        default:
            return (invalidTask(input));
        }
    }

    private static Task initTodoTask(String arg, boolean isDone) {
        ToDo todo = new ToDo(arg);
        todo.setDone(isDone);
        return (todo);
    }

    private static Task initDeadlineTask(String arg, boolean isDone) {
        String[] deadlineArgs = arg.split(" \\(");
        if (deadlineArgs.length != 2) {
            return (invalidTask(arg));
        }

        Deadline deadline = new Deadline(deadlineArgs[0], deadlineArgs[1].substring(4, deadlineArgs[1].length() - 1));
        deadline.setDone(isDone);
        return (deadline);
    }

    private static Task initEventTask(String arg, boolean isDone) {
        String[] eventArgs = arg.split(" \\(");
        if (eventArgs.length != 2) {
            return (invalidTask(arg));
        }

        String[] eventDetails = eventArgs[1].split(" to: ");
        if (eventDetails.length != 2) {
            return (invalidTask(arg));
        }

        Event event = new Event(eventArgs[0], eventDetails[0].substring(6),
                eventDetails[1].substring(0, eventDetails[1].length() - 1));
        event.setDone(isDone);
        return (event);
    }

    private static Task invalidTask(String input) {
        System.out.println("Line: " + input + " has invalid format type, data cannot be loaded");
        return (null);
    }

    /**
     * Deletes tasks given in input.
     *
     * @param input Array of strings, each string representing a task.
     * @return Status of deletion.
     */
    public String delete(String[] input) {
        List<Integer> deleteIndices = Arrays.stream(input)
                .map(Integer::parseInt)
                .sorted(Collections.reverseOrder())
                .toList();

        StringBuilder builder = new StringBuilder();
        for (Integer toDelete : deleteIndices) {
            if (toDelete < 1 || toDelete > tasks.size()) {
                if (tasks.isEmpty()) {
                    return ("Ohio level brainrot detected, you haven't even added anything to the list\n");
                } else {
                    return (toDelete + " is invalid, please pick within the range 1 - " + tasks.size() + "\n");
                }
            }
            Task deleted = tasks.remove(toDelete - 1);
            builder.append(deleted).append("\n has been ejected (they were the impostor)\n")
                    .append(tasks.size()).append(" tasks remain\n");
        }
        return (builder.toString());
    }

    /**
     * Adds a task to the list.
     *
     * @param task A task.
     * @return The status string.
     */
    public String add(Task task) {
        tasks.add(task);
        return ("added task: " + tasks.get(tasks.size() - 1).toString() + "\n"
                + tasks.size() + " task(s) in the list rn\n");
    }

    /**
     * Marks the tasks as done/not done. Stops marking tasks if one of them
     * in the list is already marked done/undone.
     *
     * @param input The list of tasks to mark as done/undone.
     * @param status True if to mark as done, and False to unmark.
     * @return The status string.
     */
    public String markTask(String[] input, boolean status) {
        StringBuilder builder = new StringBuilder();
        for (String s : input) {
            int toMark = Integer.parseInt(s);
            if (toMark > tasks.size() || toMark < 1) {
                return ("You got no rizz, try learning how to count (index " + toMark + " out of range)\n");
            }

            Task task = tasks.get(toMark - 1);
            if (status == task.isDone()) {
                return ("Task " + toMark + " is already " + (status ? "marked" : "unmarked") + "\n");
            }
            task.setDone(status);
            if (status) {
                builder.append("Let's go! +100 aura points!\n").append(task).append(" marked as done\n");
            } else {
                builder.append("Bruh, you're cooked, -500 aura points\n").append(task).append(" has been unmarked\n");
            }
        }
        return (builder.toString());
    }

    /**
     * Finds the task that matches needle string.
     *
     * @param needle The keyword of string to find.
     * @return If found, the description of every task that matches, else, "No Matches".
     */
    public String find(String needle) {
        List<String> taskList;

        if (isTaskTypeSearch(needle)) {
            taskList = getTaskTypeMatches(needle.toLowerCase());
        } else {
            taskList = getDefaultMatches(needle);
        }

        if (taskList.isEmpty()) {
            return ("No matches to " + needle + " found\n");
        }

        StringBuilder builder = new StringBuilder("I found some matches! W rizz\n");
        IntStream.range(0, taskList.size())
                .forEach(x -> builder.append(x + 1).append(".").append(taskList.get(x)).append("\n"));

        return (builder.toString());
    }

    private boolean isTaskTypeSearch(String needle) {
        String lowerCaseNeedle = needle.toLowerCase();
        return (lowerCaseNeedle.equals("todo")
                || lowerCaseNeedle.equals("deadline")
                || lowerCaseNeedle.equals("event"));
    }

    private List<String> getTaskTypeMatches(String type) {
        switch (type) {
        case "todo":
            return (tasks.stream().filter(x -> x instanceof ToDo).map(Task::toString).toList());
        case "deadline":
            return (tasks.stream().filter(x -> x instanceof Deadline).map(Task::toString).toList());
        case "event":
            return (tasks.stream().filter(x -> x instanceof Event).map(Task::toString).toList());
        default:
            return (Collections.emptyList());
        }

    }

    private List<String> getDefaultMatches(String needle) {
        return (tasks.stream()
                .map(Task::toString)
                .filter(x -> x.contains(needle) || matchExpression(x, needle) || matchForEach(x, needle))
                .toList());
    }

    private boolean matchForEach(String string, String pattern) {
        String[] splitString = string.split(" ");
        for (String s : splitString) {
            if (matchExpression(s, pattern)) {
                return (true);
            }
        }
        return (false);
    }

    private boolean matchExpression(String string, String pattern) {
        int matchIndex = 0;
        int prevStar = -1;
        int patternIndex = 0;
        int stringIndex = 0;
        while (stringIndex < string.length()) {
            if (patternIndex != pattern.length() && pattern.charAt(patternIndex) == '*') {
                matchIndex = stringIndex;
                prevStar = patternIndex;
                patternIndex++;
            } else if (patternIndex != pattern.length() && string.charAt(stringIndex) == pattern.charAt(patternIndex)) {
                patternIndex++;
                stringIndex++;
            } else if (prevStar != -1) {
                matchIndex++;
                stringIndex = matchIndex;
                patternIndex = prevStar + 1;
            } else {
                return false;
            }
        }
        while (patternIndex != pattern.length() && pattern.charAt(patternIndex) == '*') {
            patternIndex++;
        }
        return (patternIndex == pattern.length());
    }

    public Stream<Task> getTasks() {
        return (tasks.stream());
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return ("There's nobody left to mog! (Your list is empty)\n");
        }
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, tasks.size())
                .forEach(x -> builder.append(x + 1).append(".").append(tasks.get(x).toString()).append("\n"));
        return (builder.toString());
    }
}
