import java.util.Arrays;

public class TaskManager {

    Task[] storage;
    Integer numOfTasksStored;
    String[] FLAGS = new String[]{"/from", "/to", "/by"};

    public TaskManager() {
        this.storage = new Task[100];
        this.numOfTasksStored = 0;
    }

    public void addTask(String[] commands) throws IncompleteCommandException, InvalidCommandException {
        if (commands.length < 2) {
            throw new IncompleteCommandException("This Task requires a description");
        }

        String type = commands[0];

        String description = extractDescription(commands);
        String startTime = "";
        String endTime = "";

        switch (type) {
            case "todo":
                addTodoTask(description);
                break;
            case "deadline":
                endTime = extractDate(commands, "/by");
                if (endTime.isEmpty()) {
                    throw new IncompleteCommandException("Deadline Tasks must have a deadline specified");
                } else {
                    addDeadlineTask(description, endTime);
                }
                break;
            case "event":
                startTime = extractDate(commands, "/from");
                endTime = extractDate(commands, "/to");
                if (startTime.isEmpty() || endTime.isEmpty()) {
                    throw new IncompleteCommandException("Events must have a start and end specified");
                } else {
                    addEventTask(description, startTime, endTime);
                }
                break;
            default:
                throw new InvalidCommandException("This task does not exist.");
        }
    }

    private String extractDescription(String[] commands) {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < commands.length; i++) {
            boolean isDateMarker = Arrays.asList(FLAGS).contains(commands[i]);
            if (isDateMarker) {
                break;
            }
            description.append(commands[i]);
            description.append(" ");
        }
        return description.toString().trim();
    }

    private String extractDate(String[] commands, String dateMarker) {
        StringBuilder date = new StringBuilder();
        int index = Arrays.asList(commands).indexOf(dateMarker);
        if (index == -1 || index + 1 >= commands.length) {
            return "";
        }

        for (int i = index + 1; i < commands.length; i++) {
            boolean isDateMarker = Arrays.asList(FLAGS).contains(commands[i]);
            if (isDateMarker) {
                break;
            }
            date.append(commands[i]);
            date.append(" ");
        }
        return date.toString().trim();
    }

    private void addTodoTask(String description) {
        this.storage[numOfTasksStored] = new ToDoTask(description.trim());
        this.numOfTasksStored++;
        printTaskAddedMessage();
    }

    private void addDeadlineTask(String description, String deadline) {
        this.storage[numOfTasksStored] = new DeadlineTask(description.trim(), deadline.trim());
        this.numOfTasksStored++;
        printTaskAddedMessage();
    }

    private void addEventTask(String description, String start, String end) {
        this.storage[numOfTasksStored] = new EventTask(description.trim(), start.trim(), end.trim());
        this.numOfTasksStored++;
        printTaskAddedMessage();
    }

    private void printTaskAddedMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println(this.storage[numOfTasksStored - 1]);
        System.out.println(String.format("Now you have %d tasks in the list", this.numOfTasksStored));
        System.out.println(Optimus.linebreak);
    }

    private void markTask(String[] commands, boolean markComplete) throws InvalidTaskNumberException , IncompleteCommandException{
        if (commands.length != 2) {
            throw new IncompleteCommandException("The Task number is not specified");
        }

        try {
            int taskNum = Integer.parseInt(commands[1]);
            if (taskNum <= 0 || taskNum > this.numOfTasksStored) {
                throw new InvalidTaskNumberException("A Task with this number does not exist.");
            }

            Task task = this.storage[taskNum - 1];
            if (markComplete) {
                task.markAsComplete();
                System.out.println("Nice! I've marked this task as complete:");
            } else {
                task.markAsIncomplete();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(task);
            System.out.println(Optimus.linebreak);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + e.getMessage());
        }
    }

    public void markTaskAsDone(String[] commands) throws IncompleteCommandException, InvalidTaskNumberException {
        markTask(commands, true);
    }

    public void markTaskAsIncomplete(String[] commands) throws IncompleteCommandException, InvalidTaskNumberException {
        markTask(commands, false);
    }

    public void printAllTasks() {
        for (int i = 1; i <= this.numOfTasksStored; i++){
            String out = String.format("%d. %s", i, this.storage[i-1]);
            System.out.println(out);
        }
        System.out.println(Optimus.linebreak);
    }
}
