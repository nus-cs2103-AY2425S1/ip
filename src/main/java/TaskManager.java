import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaskManager {

    private static final String[] FLAGS = {"/from", "/to", "/by"};
    private static final String INVALID_TASK_NUMBER_MSG = "A Task with this number does not exist.";
    private static final String TASK_NOT_SPECIFIED_MSG = "The Task number is not specified";
    private static final String NO_DESCRIPTION_MSG = "This Task requires a description";

    private final ArrayList<Task> storage;

    public TaskManager() {
        this.storage = new ArrayList<>(100);
    }

    public void addTask(String[] commands) throws IncompleteCommandException, InvalidCommandException {
        if (commands.length < 2) {
            throw new IncompleteCommandException(NO_DESCRIPTION_MSG);
        }

        String type = commands[0];
        String description = extractDescription(commands);
        String startTime, endTime;

        switch (type) {
            case "todo" -> addTodoTask(description);
            case "deadline" -> {
                endTime = extractDateAfterFlag(commands, "/by")
                        .orElseThrow(() -> new IncompleteCommandException("Deadline Tasks must have a deadline specified"));
                addDeadlineTask(description, endTime);
            }
            case "event" -> {
                startTime = extractDateAfterFlag(commands, "/from")
                        .orElseThrow(() -> new IncompleteCommandException("Events must have a start specified"));
                endTime = extractDateAfterFlag(commands, "/to")
                        .orElseThrow(() -> new IncompleteCommandException("Events must have an end specified"));
                addEventTask(description, startTime, endTime);
            }
            default -> throw new InvalidCommandException("This task does not exist.");
        }
    }

    private String extractDescription(String[] commands) {
        int endIndex = findFirstFlagIndex(commands).orElse(commands.length);
        return String.join(" ", Arrays.copyOfRange(commands, 1, endIndex));
    }

    private Optional<Integer> findFirstFlagIndex(String[] commands) {
        for (int i = 1; i < commands.length; i++) {
            if (Arrays.asList(FLAGS).contains(commands[i])) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    private Optional<String> extractDateAfterFlag(String[] commands, String flag) {
        int index = Arrays.asList(commands).indexOf(flag);
        if (index == -1 || index + 1 >= commands.length) {
            return Optional.empty();
        }

        int endIndex = findFirstFlagIndex(Arrays.copyOfRange(commands, index + 1, commands.length))
                .map(i -> i + index + 1)
                .orElse(commands.length);

        return Optional.of(String.join(" ", Arrays.copyOfRange(commands, index + 1, endIndex)).trim());
    }

    private void addTodoTask(String description) {
        storage.add(new ToDoTask(description));
        printTaskAddedMessage();
    }

    private void addDeadlineTask(String description, String deadline) {
        storage.add(new DeadlineTask(description, deadline));
        printTaskAddedMessage();
    }

    private void addEventTask(String description, String start, String end) {
        storage.add(new EventTask(description, start, end));
        printTaskAddedMessage();
    }

    private void printTaskAddedMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println(storage.get(storage.size() - 1));
        System.out.printf("Now you have %d tasks in the list%n", storage.size());
        System.out.println(Optimus.linebreak);
    }

    private int parseTaskNumber(String[] commands) throws IncompleteCommandException, InvalidTaskNumberException {
        if (commands.length != 2) {
            throw new IncompleteCommandException(TASK_NOT_SPECIFIED_MSG);
        }
        try {
            int taskNum = Integer.parseInt(commands[1]);
            if (taskNum <= 0 || taskNum > storage.size()) {
                throw new InvalidTaskNumberException(INVALID_TASK_NUMBER_MSG);
            }
            return taskNum - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid number format: " + e.getMessage());
        }
    }

    private void markTask(String[] commands, boolean markComplete) throws IncompleteCommandException, InvalidTaskNumberException {
        int taskNum = parseTaskNumber(commands);
        Task task = storage.get(taskNum);

        if (markComplete) {
            task.markAsComplete();
            System.out.println("Nice! I've marked this task as complete:");
        } else {
            task.markAsIncomplete();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
        System.out.println(Optimus.linebreak);
    }

    public void markTaskAsDone(String[] commands) throws IncompleteCommandException, InvalidTaskNumberException {
        markTask(commands, true);
    }

    public void markTaskAsIncomplete(String[] commands) throws IncompleteCommandException, InvalidTaskNumberException {
        markTask(commands, false);
    }

    public void deleteTask(String[] commands) throws IncompleteCommandException, InvalidTaskNumberException {
        int taskNum = parseTaskNumber(commands);
        Task removed = storage.remove(taskNum);

        System.out.println("Noted. I've removed this task:");
        System.out.println(removed);
        System.out.printf("Now you have %d tasks in the list%n", storage.size());
        System.out.println(Optimus.linebreak);
    }

    public void printAllTasks() {
        for (int i = 0; i < storage.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, storage.get(i));
        }
        System.out.println(Optimus.linebreak);
    }
}