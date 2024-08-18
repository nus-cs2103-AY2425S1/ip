public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;
    private static final Task[] tasks = new Task[100];

    /**
     * Creates a new Task with the specified description.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done or not done.
     *
     * @param status the status to set (true for done, false for not done)
     */
    public void markAsDone(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task with its status
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Adds a new task to the list and prints the formatted output.
     *
     * @param task the task to add
     */
    public static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        printTaskAdded(task);
    }

    /**
     * Creates a task based on the input command.
     *
     * @param command the input command describing the task
     * @return the created Task object (Todo, Deadline, or Event)
     */
    public static Task createTask(String command) {
        if (command.startsWith("todo ")) {
            return new Todo(command.substring(5));
        } else if (command.startsWith("deadline ")) {
            String[] parts = command.substring(9).split(" /by ");
            return new Deadline(parts[0], parts[1]);
        } else if (command.startsWith("event ")) {
            String[] parts = command.substring(6).split(" /from | /to ");
            return new Event(parts[0], parts[1], parts[2]);
        } else {
            throw new IllegalArgumentException("Unknown task type.");
        }
    }

    /**
     * Lists all the tasks along with their statuses.
     */
    public static void listTasks() {
        String border = "--".repeat(30);
        System.out.println(border);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(border);
    }

    /**
     * Marks a specific task as done based on the task number.
     *
     * @param taskNumber the task number to mark as done
     */
    public static void markTaskAsDone(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < taskCount) {
            tasks[taskNumber].markAsDone(true);
            messageWrapper("Nice! I've marked this task as done:\n  " + tasks[taskNumber]);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Unmarks a specific task as not done based on the task number.
     *
     * @param taskNumber the task number to unmark as done
     */
    public static void unmarkTaskAsDone(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < taskCount) {
            tasks[taskNumber].markAsDone(false);
            messageWrapper("OK, I've marked this task as not done yet:\n  " + tasks[taskNumber]);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Prints the formatted message when a task is added.
     *
     * @param task the task that was added
     */
    private static void printTaskAdded(Task task) {
        String border = "____________________________________________________________";
        System.out.println(border);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(border);
    }

    /**
     * Prints a message within a decorative border.
     *
     * @param message the message to print
     */
    public static void messageWrapper(String message) {
        String border = "*-".repeat(30);
        System.out.println(border);
        System.out.println();
        System.out.println(message);
        System.out.println();
        System.out.println(border);
    }
}