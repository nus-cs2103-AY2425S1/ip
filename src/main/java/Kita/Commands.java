package Kita;

import Kita.Exceptions.KitaMissingIndex;
import Kita.Exceptions.KitaOutofBounds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class Commands {
    private TaskList tasks;
    private Storage saveSystem;

    public Commands(TaskList tasks, Storage saveSystem) {
        this.tasks = tasks;
        this.saveSystem = saveSystem;
    }

    /**
     * Prints a standard divider line
     * @returns void
     */
    public void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Adds a task to the TaskList stored in this Commands instance
     * Also writes to Storage
     *
     * @param newTask Task to add
     * @returns void
     */
    public void addTask(Task newTask) throws IOException{
        this.tasks.addTask(newTask);
        saveSystem.writeTasksToFile(this.tasks.getAllTasks());
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    /**
     * Creates an "Event" Task
     * Also writes to Storage
     *
     * @param eventMatcher The Matcher object used to match the "event" creation
     * @returns void
     */
    public void createEvent(Matcher eventMatcher) throws IOException {
        String from = eventMatcher.group("from");
        String to = eventMatcher.group("to");
        if (from == null) {
            from = eventMatcher.group("from2");
            to = eventMatcher.group("to2");
        }
        Task newTask = new Event(eventMatcher.group("name"), from, to);
        this.addTask(newTask);
    }

    /**
     * Creates a "Deadline" Task
     * Also writes to Storage
     *
     * @param deadlineMatcher The Matcher object used to match the "deadline" creation
     * @returns void
     */
    public void createDeadline(Matcher deadlineMatcher) throws IOException {
        Task newTask = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
        this.addTask(newTask);
    }

    /**
     * Creates a "Todo" Task
     * Also writes to Storage
     *
     * @param todoMatcher The Matcher object used to match the "todo" creation
     * @returns void
     */
    public void createToDo(Matcher todoMatcher) throws IOException {
        Task newTask = new ToDo(todoMatcher.group(1));
        this.addTask(newTask);
    }

    /**
     * Marks a Task as "completed"
     * Also writes to Storage
     *
     * @param command The String command that was entered in the form of "mark <task_id>"
     * @returns void
     */
    public void mark(String command) throws IOException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length <= 1) {
            throw new KitaMissingIndex();
        }

        int numberToMark = Integer.parseInt(splitCommand[1]);
        Task theTask = this.tasks.setTaskCompleted(numberToMark - 1, true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + theTask);
        this.saveSystem.writeTasksToFile(this.tasks.getAllTasks());
    }

    /**
     * Marks a Task as "uncompleted"
     * Also writes to Storage
     *
     * @param command The String command that was entered in the form of "unmark <task_id>"
     * @returns void
     */
    public void unmark(String command) throws IOException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length <= 1) {
            throw new KitaMissingIndex();
        }

        int numberToMark = Integer.parseInt(splitCommand[1]);
        Task theTask = this.tasks.setTaskCompleted(numberToMark - 1, false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + theTask);
        saveSystem.writeTasksToFile(this.tasks.getAllTasks());
    }

    /**
     * Deletes a Task given an ID
     * Also writes to Storage
     *
     * @param command The String command that was entered in the form of "delete <task_id>"
     * @returns void
     */
    public void delete(String command) throws IOException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length <= 1) {
            throw new KitaMissingIndex();
        }

        int numberToDelete = Integer.parseInt(splitCommand[1]);
        Task theTask = this.tasks.removeTask(numberToDelete - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + theTask);
        saveSystem.writeTasksToFile(this.tasks.getAllTasks());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    /**
     * Lists all tasks out
     *
     * @returns void
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        System.out.println(this.tasks);
    }

    /**
     * Prints the hello message
     *
     * @returns void
     */
    public void hello() {
        this.printLine();
        System.out.println(" Hello! I'm Kita!");
        System.out.println(" What can I do for you?");
        this.printLine();
    }

    /**
     * Prints the bye message
     *
     * @returns void
     */
    public void bye() {
        System.out.println(" Bye. Hope to see you again soon!\n");
        this.printLine();
    }


    public void find(String command) {
        String[] splitCommand = command.split(" ");

        System.out.println("Here are the matching tasks in your list:");
        TaskList foundTasks = this.tasks.find(splitCommand[1]);
        System.out.println(foundTasks);
    }
}
