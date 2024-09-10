package kita;

import java.io.IOException;
import java.util.regex.Matcher;

import kita.exceptions.KitaMissingIndex;

/**
 * Commands executor class
 */
public class Commands {
    private TaskList tasks;
    private Storage saveSystem;

    /**
     * Initialises a commands executor
     *
     * @param tasks The TaskList of tasks
     * @param saveSystem The saveSystem for saving to the file system
     * @returns void
     */
    public Commands(TaskList tasks, Storage saveSystem) {
        assert tasks != null;
        assert saveSystem != null;

        this.tasks = tasks;
        this.saveSystem = saveSystem;
    }

    /**
     * Appends to a given StringBuilder and also prints the output
     * @param sb
     * @param inputStr
     */
    private void printAndAppend(StringBuilder sb, String inputStr) {
        sb.append(inputStr + "\n");
        System.out.println(inputStr);
    }

    /**
     * Prints a standard divider line
     * @returns The output string for the GUI
     */
    public String printLine() {
        StringBuilder output = new StringBuilder();
        printAndAppend(output, "____________________________________________________________");
        return output.toString();
    }

    /**
     * Adds a task to the TaskList stored in this Commands instance
     * Also writes to Storage
     *
     * @param newTask Task to add
     * @returns The output string for the GUI
     */
    public String addTask(Task newTask) throws IOException {
        this.tasks.addTask(newTask);
        saveSystem.writeTasksToFile(this.tasks.getAllTasks());
        StringBuilder output = new StringBuilder();
        printAndAppend(output, "Got it. I've added this task: ");
        printAndAppend(output, "  " + newTask);
        printAndAppend(output, "Now you have " + this.tasks.size() + " tasks in the list.");
        return output.toString();
    }

    /**
     * Creates an "Event" Task
     * Also writes to Storage
     *
     * @param eventMatcher The Matcher object used to match the "event" creation
     * @returns The output string for the GUI
     */
    public String createEvent(Matcher eventMatcher) throws IOException {
        String from = eventMatcher.group("from");
        String to = eventMatcher.group("to");
        if (from == null) {
            from = eventMatcher.group("from2");
            to = eventMatcher.group("to2");
        }
        Task newTask = new Event(eventMatcher.group("name"), from, to);
        return this.addTask(newTask);
    }

    /**
     * Creates a "Deadline" Task
     * Also writes to Storage
     *
     * @param deadlineMatcher The Matcher object used to match the "deadline" creation
     * @returns The output string for the GUI
     */
    public String createDeadline(Matcher deadlineMatcher) throws IOException {
        Task newTask = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
        return this.addTask(newTask);
    }

    /**
     * Creates a "Todo" Task
     * Also writes to Storage
     *
     * @param todoMatcher The Matcher object used to match the "todo" creation
     * @returns The output string for the GUI
     */
    public String createToDo(Matcher todoMatcher) throws IOException {
        Task newTask = new ToDo(todoMatcher.group(1));
        return this.addTask(newTask);
    }

    /**
     * Marks a Task as "completed"
     * Also writes to Storage
     *
     * @param command The String command that was entered in the form of "mark `task_id`"
     * @returns The output string for the GUI
     */
    public String mark(String command) throws IOException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length <= 1) {
            throw new KitaMissingIndex();
        }

        int numberToMark = Integer.parseInt(splitCommand[1]);
        Task theTask = this.tasks.setTaskCompleted(numberToMark - 1, true);
        StringBuilder output = new StringBuilder();
        printAndAppend(output, "Nice! I've marked this task as done:");
        printAndAppend(output, "  " + theTask);
        this.saveSystem.writeTasksToFile(this.tasks.getAllTasks());
        return output.toString();
    }

    /**
     * Marks a Task as "uncompleted"
     * Also writes to Storage
     *
     * @param command The String command that was entered in the form of "unmark `task_id`"
     * @returns The output string for the GUI
     */
    public String unmark(String command) throws IOException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length <= 1) {
            throw new KitaMissingIndex();
        }

        int numberToMark = Integer.parseInt(splitCommand[1]);
        Task theTask = this.tasks.setTaskCompleted(numberToMark - 1, false);
        StringBuilder output = new StringBuilder();
        printAndAppend(output, "OK, I've marked this task as not done yet:");
        printAndAppend(output, "  " + theTask);
        saveSystem.writeTasksToFile(this.tasks.getAllTasks());
        return output.toString();
    }

    /**
     * Deletes a Task given an ID
     * Also writes to Storage
     *
     * @param command The String command that was entered in the form of "delete `task_id`"
     * @returns The output string for the GUI
     */
    public String delete(String command) throws IOException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length <= 1) {
            throw new KitaMissingIndex();
        }

        int numberToDelete = Integer.parseInt(splitCommand[1]);
        Task theTask = this.tasks.removeTask(numberToDelete - 1);
        StringBuilder output = new StringBuilder();
        printAndAppend(output, "Noted. I've removed this task:");
        printAndAppend(output, "  " + theTask);
        printAndAppend(output, "Now you have " + this.tasks.size() + " tasks in the list.");
        saveSystem.writeTasksToFile(this.tasks.getAllTasks());
        return output.toString();
    }

    /**
     * Lists all tasks out
     *
     * @returns The output string for the GUI
     */
    public String list() {
        StringBuilder output = new StringBuilder();
        printAndAppend(output, "Here are the tasks in your list:");
        printAndAppend(output, this.tasks.toString());
        return output.toString();
    }

    /**
     * Prints the hello message
     *
     * @returns The output string for the GUI
     */
    public String hello() {
        StringBuilder output = new StringBuilder();
        this.printLine();
        printAndAppend(output, " Hello! I'm Kita!");
        printAndAppend(output, " What can I do for you?");
        this.printLine();
        return output.toString();
    }

    /**
     * Prints the bye message
     *
     * @returns The output string for the GUI
     */
    public String bye() {
        StringBuilder output = new StringBuilder();
        printAndAppend(output, " Bye. Hope to see you again soon!\n");
        this.printLine();
        return output.toString();
    }

    /**
     * Prints the list of tasks that match the string query
     *
     * @param command The "find" command in the form of "find `query`"
     * @returns The output string for the GUI
     */
    public String find(String command) {
        StringBuilder output = new StringBuilder();
        String[] splitCommand = command.split(" ");

        printAndAppend(output, "Here are the matching tasks in your list:");
        TaskList foundTasks = this.tasks.find(splitCommand[1]);
        printAndAppend(output, foundTasks.toString());
        return output.toString();
    }
}
